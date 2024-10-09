package Service;

import Mapper.EmailMapper;
import Util.BaseUtils;
import com.sun.mail.pop3.POP3SSLStore;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.jsoup.Jsoup;
import pojo.Email;
import java.sql.Timestamp;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;

import java.util.Date;
import java.util.Properties;
import java.util.UUID;

public class ReceiveService {
    private final EmailMapper emailMapper;

    public ReceiveService() {
        // 加载MyBatis配置文件
        InputStream inputStream = UserService.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        this.emailMapper = sqlSession.getMapper(EmailMapper.class);
    }

    public Message[] receiveEmail() throws Exception {
        String host = "pop.qq.com";
        int port = 995;
        String username = "2755004257@qq.com";
        String password = "uuaprtfpedcnddje";

        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "pop"); // 协议名称
        props.setProperty("mail.pop.host", host);// POP3主机名
        props.setProperty("mail.pop.port", String.valueOf(port)); // 端口号
// 启动SSL:
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", String.valueOf(port));

// 连接到Store:
        URLName url = new URLName("pop", host, port, "", username, password);
        Session session = Session.getInstance(props, null);
        session.setDebug(true); // 显示调试信息
        Store store = new POP3SSLStore(session, url);
        try {
            store.connect();
        } catch (MessagingException e) {
            throw new RuntimeException("没连接上");
        }
        Folder folder = store.getFolder("INBOX");
// 以读写方式打开:
        folder.open(Folder.READ_WRITE);
// 打印邮件总数/新邮件数量/未读数量/已删除数量:
        System.out.println("Total messages: " + folder.getMessageCount());
        System.out.println("New messages: " + folder.getNewMessageCount());
        System.out.println("Unread messages: " + folder.getUnreadMessageCount());
        System.out.println("Deleted messages: " + folder.getDeletedMessageCount());
// 获取每一封邮件:
        Message[] messages = folder.getMessages();
        parseFileMessage(messages);
        return messages;
    }

    public void parseFileMessage(Message... messages) throws Exception {
        if (messages == null || messages.length < 1){
            System.out.println("没有可读取邮件");
            return;
        }
        // 解析读取到的邮件
        for (Message message : messages) {
            MimeMessage msg = (MimeMessage) message;
            System.out.println("------------------解析第" + msg.getMessageNumber() + "封邮件-------------------- ");
            System.out.println("主题: " + MimeUtility.decodeText(msg.getSubject()));
            System.out.println("发件人: " + getFrom(msg));
            //TODO: 时间
            System.out.println("收件人：" + getReceiveAddress(msg, null));
            System.out.println("发送时间：" + getSentDate(msg, null));
            StringBuffer content = new StringBuffer(30);
            getMailTextContent(msg, content);
            System.out.println("邮件正文：" + (content.length() > 100 ? content.substring(0,100) + "..." : content));
            UUID uuid = UUID.randomUUID();

            Email email = new Email(uuid.toString(), getFrom(msg), getReceiveAddress(msg, null), (new Timestamp(getSentDate(msg, null).getTime())), MimeUtility.decodeText(msg.getSubject()), content.toString());
            System.out.println(BaseUtils.getCurrentId());
            this.emailMapper.addEmail(email.getId(), email.getSendUser(),  email.getReceiveUser(), email.getSubject(), email.getSentTime(), email.getContent(), "root");
            System.out.println("------------------第" + msg.getMessageNumber() + "封邮件解析结束-------------------- ");
        }
    }

    public String getFrom(MimeMessage msg) throws MessagingException, UnsupportedEncodingException {
        String from = "";
        Address[] froms = msg.getFrom();
        if (froms.length < 1)
            throw new MessagingException("没有发件人!");
        InternetAddress address = (InternetAddress) froms[0];
        String person = address.getPersonal();
        if (person != null) {
            person = MimeUtility.decodeText(person) + " ";
        } else {
            person = "";
        }
        from = person + "<" + address.getAddress() + ">";

        return from;
    }
    /**
     * 获得邮件发送时间
     * @param msg 邮件内容
     * @return yyyy年mm月dd日 星期X HH:mm
     * @throws MessagingException
     */
    public Date getSentDate(MimeMessage msg, String pattern) throws MessagingException {
        Date receivedDate = msg.getSentDate();
        if (receivedDate == null)
            return null;
        return receivedDate;
    }
    /**
     * 根据收件人类型，获取邮件收件人、抄送和密送地址。如果收件人类型为空，则获得所有的收件人
     * @param msg 邮件内容
     * @param type 收件人类型
     * @return 收件人1 <邮件地址1>, 收件人2 <邮件地址2>, ...
     * @throws MessagingException
     */
    public String getReceiveAddress(MimeMessage msg, Message.RecipientType type) throws MessagingException {
        StringBuilder receiveAddress = new StringBuilder();
        Address[] addresss;
        if (type == null) {
            addresss = msg.getAllRecipients();
        } else {
            addresss = msg.getRecipients(type);
        }

        if (addresss == null || addresss.length < 1)
            throw new MessagingException("没有收件人!");
        for (Address address : addresss) {
            InternetAddress internetAddress = (InternetAddress)address;
            receiveAddress.append(internetAddress.toUnicodeString()).append(",");
        }
        receiveAddress.deleteCharAt(receiveAddress.length()-1); //删除最后一个逗号
        return receiveAddress.toString();
    }

    /**
     * 获得邮件文本内容
     * @param part 邮件体
     * @param content 存储邮件文本内容的字符串
     * @throws MessagingException
     * @throws IOException
     */
    public void getMailTextContent(Part part, StringBuffer content) throws MessagingException, IOException {
        if (part.isMimeType("text/*")) {
            String text = part.getContent().toString();
            if (part.isMimeType("text/html")) {
                text = Jsoup.parse(text).text(); // 提取纯文本内容
            }
            content.append(text);
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            for (int i = 0; i < multipart.getCount(); i++) {
                getMailTextContent(multipart.getBodyPart(i), content);
            }
        } else if (part.isMimeType("message/rfc822")) {
            getMailTextContent((Part) part.getContent(), content);
        }
    }

    public pojo.Message[] getMessages() {
        pojo.Message[] messages =  emailMapper.getEmails("root");
        return messages;
    }

    public static void main(String[] args) {
        ReceiveService receiveService = new ReceiveService();
        try {
            receiveService.receiveEmail();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}







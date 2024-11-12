package Service.email;

import Service.PasswordManage.EmailPasswordService;
import Util.BaseUtils;
import org.jsoup.Connection;
import pojo.EmailPassword;
import pojo.RoundBorder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SendEmailFrame extends JFrame {

    SendService sendService = new SendService();
    EmailPasswordService emailPasswordService = new EmailPasswordService();
    Container c; // 声明添加组件的容器
    JLabel toUserLabel;
    JLabel subjectLabel;
    JLabel contentLabel;


    public SendEmailFrame() {
        c = getContentPane(); // 获取默认的内容面板
        c.setLayout(null); // 关闭默认的布局
        setTitle("发送邮件");
        setBounds(100, 100, 700, 500);


        String currentId = BaseUtils.getCurrentId();
        List<EmailPassword> allEmailPasswords = emailPasswordService.getAllEmailPasswords(currentId);
        ArrayList<String> options = new ArrayList<>();
        for (EmailPassword emailPassword : allEmailPasswords) {
            options.add(emailPassword.getEmailaccount());
        }
        JComboBox<String> comboBox = new JComboBox<>(options.toArray(new String[0]));
        comboBox.setBounds(80, 50, 200, 30);
//        comboBox.setBorder(new RoundBorder(Color.GRAY)); // 设置按钮的边框
        c.add(comboBox);

        JLabel sentUserLabel = new JLabel();
        sentUserLabel.setBounds(10, 50, 60, 15);
        sentUserLabel.setText("发送邮箱：");
        c.add(sentUserLabel);


        JLabel toUserLabel = new JLabel();
        toUserLabel.setBounds(10, 100, 60, 15);
        toUserLabel.setText("收件人：");
        c.add(toUserLabel);

        JTextField toUserTextField = new JTextField();
        toUserTextField.setBounds(80, 100, 200, 30);
        c.add(toUserTextField);

        JLabel subjectLabel = new JLabel();
        subjectLabel.setBounds(10, 150, 60, 15);
        subjectLabel.setText("邮件主题：");
        c.add(subjectLabel);

        JTextField subjectTextField = new JTextField();
        subjectTextField.setBounds(80, 150, 200, 30);
        c.add(subjectTextField);

        JLabel contentLabel = new JLabel();
        contentLabel.setBounds(10, 200, 60, 15);
        contentLabel.setText("邮件内容：");
        c.add(contentLabel);

        JTextArea contentTextArea = new JTextArea();
        contentTextArea.setBounds(80, 200, 400, 150);
        contentTextArea.setLineWrap(true);
        contentTextArea.setWrapStyleWord(true);
        contentTextArea.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
        c.add(contentTextArea);


        JButton sendButton = new JButton("发送邮件");
        sendButton.setBounds(80, 370, 100, 50);


        sendButton.addActionListener(e -> {
            // 发送邮件
            try {
                String to = toUserTextField.getText();
                String subject = subjectTextField.getText();
                String content = contentTextArea.getText();
                sendService.sendEmail(to, subject, content);
                System.out.println(to);
                System.out.println(subject);
                System.out.println(content);
                JOptionPane.showMessageDialog(null, "发送成功", "提示", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "发送失败", "提示", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
        c.add(sendButton);


        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 只关闭当前窗口
        this.setVisible(true); // 设置窗体可见，放在最后一条

    }
}

package Service.email;

import Service.SendService;

import javax.swing.*;
import java.awt.*;

public class SendEmailFrame extends JFrame {

    SendService sendService = new SendService();
    Container c; // 声明添加组件的容器
    JLabel toUserLabel;
    JLabel subjectLabel;
    JLabel contentLabel;


    public SendEmailFrame() {
        c = getContentPane(); // 获取默认的内容面板
        c.setLayout(null); // 关闭默认的布局
        setTitle("发送邮件");
        setBounds(100, 100, 700, 500);

        JLabel toUserLabel = new JLabel();
        toUserLabel.setBounds(10, 50, 60, 15);
        toUserLabel.setText("收件人：");
        c.add(toUserLabel);

        JTextField toUserTextField = new JTextField();
        toUserTextField.setBounds(80, 50, 200, 30);
        c.add(toUserTextField);

        JLabel subjectLabel = new JLabel();
        subjectLabel.setBounds(10, 100, 65, 15);
        subjectLabel.setText("邮件主题：");
        c.add(subjectLabel);

        JTextField subjectTextField = new JTextField();
        subjectTextField.setBounds(80, 100, 200, 30);
        c.add(subjectTextField);

        JLabel contentLabel = new JLabel();
        contentLabel.setBounds(10, 150, 65, 15);
        contentLabel.setText("邮件内容：");
        c.add(contentLabel);

        JTextField contentTextField = new JTextField();
        contentTextField.setBounds(80, 150, 200, 50);
        c.add(contentTextField);


        JButton sendButton = new JButton("发送邮件");
        sendButton.setBounds(50, 200, 100, 50);


        sendButton.addActionListener(e -> {
            // 发送邮件
            try {
                String to = toUserTextField.getText();
                String subject = subjectTextField.getText();
                String content = contentTextField.getText();
                sendService.sendEmail(to, subject, content);
                System.out.println(to);
                System.out.println(subject);
                System.out.println(content);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        c.add(sendButton);


        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 只关闭当前窗口
        this.setVisible(true); // 设置窗体可见，放在最后一条

    }
}

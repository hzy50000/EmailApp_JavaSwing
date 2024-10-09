package Service.PasswordManage;

import Service.EmailPasswordService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPasswordFrame extends JFrame {
    private JTextField emailAccountField;
    private JTextField passwordField;
    private EmailPasswordService emailPasswordService = new EmailPasswordService();

    public AddPasswordFrame() {
        setTitle("添加Email账户");
        setBounds(100, 100, 300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // 创建表单
        add(new JLabel("Email账户:"));
        emailAccountField = new JTextField();
        add(emailAccountField);

        add(new JLabel("密码:"));
        passwordField = new JTextField();
        add(passwordField);

        // 创建提交按钮
        JButton submitButton = new JButton("提交");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailAccount = emailAccountField.getText();
                String password = passwordField.getText();
                emailPasswordService.addEmailPassword(emailAccount, password);
                dispose();
            }
        });
        add(submitButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 只关闭当前窗口
        setVisible(true);
    }
}
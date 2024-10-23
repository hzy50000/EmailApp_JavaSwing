package Service.PasswordManage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeletePasswordFrame extends JFrame {
    private JTextField emailAccountField;
    private EmailPasswordService emailPasswordService = new EmailPasswordService();

    public DeletePasswordFrame() {
        setTitle("删除Email账户");
        setBounds(100, 100, 300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(2, 2));

        // 创建表单
        add(new JLabel("Email账户:"));
        emailAccountField = new JTextField();
        add(emailAccountField);

        // 创建提交按钮
        JButton submitButton = new JButton("提交");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String emailAccount = emailAccountField.getText();
                emailPasswordService.deleteEmailPassword(emailAccount);
                dispose();
            }
        });
        add(submitButton);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 只关闭当前窗口
        setVisible(true);
    }
}
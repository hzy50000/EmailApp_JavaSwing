package PasswordManage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPasswordFrame extends JFrame implements ActionListener {
    public AddPasswordFrame() {
        setTitle("添加Email账户");
        setBounds(100, 100, 300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email账户");
        emailLabel.setBounds(50, 30, 60, 30);
        add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(120, 30, 120, 30);
        add(emailField);

        JLabel passwordLabel = new JLabel("密码");
        passwordLabel.setBounds(50, 70, 60, 30);
        add(passwordLabel);

        JTextField passwordField = new JTextField();
        passwordField.setBounds(120, 70, 120, 30);
        add(passwordField);

        JButton addButton = new JButton("添加");
        addButton.setBounds(100, 120, 80, 30);

        add(addButton);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

package PasswordManage;

import Service.EmailPasswordService;
import pojo.EmailPassword;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class PasswordManageFrame extends JFrame {
    private DefaultTableModel model;
    private EmailPasswordService emailPasswordService = new EmailPasswordService();

    public PasswordManageFrame() {
        setTitle("密码管理");
        setBounds(100, 100, 360, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 创建 JTable
        model = new DefaultTableModel();
        JTable table = new JTable(model);

        // 添加列名
        model.addColumn("Email账户");
        model.addColumn("密码");

        // 添加 JTable 到 JFrame
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // 创建添加按钮
        JButton addButton = new JButton("添加Email账户");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddPasswordFrame();
            }
        });
        buttonPanel.add(addButton);

        // 创建删除按钮
        JButton deleteButton = new JButton("删除Email账户");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DeletePasswordFrame();
            }
        });
        buttonPanel.add(deleteButton);

        // 创建刷新按钮
        JButton refreshButton = new JButton("刷新");
        refreshButton.addActionListener(e -> refreshTable());
        buttonPanel.add(refreshButton);
        this.add(buttonPanel, BorderLayout.SOUTH);
        refreshTable();
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 只关闭当前窗口
    }

        private void refreshTable() {
            model.setRowCount(0);
            List<EmailPassword> emailPasswords = emailPasswordService.getAllEmailPasswords();
            for (EmailPassword emailPassword : emailPasswords) {
                model.addRow(new Object[]{emailPassword.getEmailaccount(), emailPassword.getEmailpassword()});
            }
    }

    public static void main(String[] args) {
        new PasswordManageFrame();
    }

}
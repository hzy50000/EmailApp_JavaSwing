package Service.Setting;

import Service.Login.UserService;
import Util.ApplicationUtils;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SettingFrame extends JFrame implements ActionListener {
    JLabel lblAccount;
    JLabel OriginalPassword;
    JLabel NewPassword;
    JLabel ConfirmPassword;
    JTextField txtAccount;
    JPasswordField txtOriginalPassword;
    JPasswordField txtNewPassword;
    JPasswordField txtConfirmPassword;
    JButton btnConfirm;
    Container c;

    public SettingFrame() throws HeadlessException {
        c = getContentPane();
        c.setLayout(null);
        setTitle("个人资料设置");
        setBounds(100, 100, 360, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        lblAccount = new JLabel();
        lblAccount.setBounds(100, 23, 60, 15);
        lblAccount.setText("账号：");
        c.add(lblAccount);
        txtAccount = new JTextField();
        txtAccount.setBounds(180, 20, 96, 21);
        txtAccount.setColumns(15);
        c.add(txtAccount);

        OriginalPassword = new JLabel();
        OriginalPassword.setBounds(100, 65, 60, 15);
        OriginalPassword.setText("原密码：");
        c.add(OriginalPassword);
        txtOriginalPassword = new JPasswordField();
        txtOriginalPassword.setBounds(180, 62, 96, 21);
        txtOriginalPassword.setColumns(15);
        txtOriginalPassword.setEchoChar('*');
        c.add(txtOriginalPassword);

        NewPassword = new JLabel();
        NewPassword.setBounds(100, 107, 60, 15);
        NewPassword.setText("新密码：");
        c.add(NewPassword);
        txtNewPassword = new JPasswordField();
        txtNewPassword.setBounds(180, 104, 96, 21);
        txtNewPassword.setColumns(15);
        txtNewPassword.setEchoChar('*');
        c.add(txtNewPassword);

        ConfirmPassword = new JLabel();
        ConfirmPassword.setBounds(100, 149, 75, 15);
        ConfirmPassword.setText("确认新密码：");
        c.add(ConfirmPassword);
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(180, 146, 96, 21);
        txtConfirmPassword.setColumns(15);
        txtConfirmPassword.setEchoChar('*');
        c.add(txtConfirmPassword);

        btnConfirm = new JButton();
        btnConfirm.setBounds(150, 180, 70, 23);
        btnConfirm.setText("确定");
        c.add(btnConfirm);

        btnConfirm.addActionListener(this);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 只关闭当前窗口
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConfirm) {
            String username = txtAccount.getText();
            String originalPassword = new String(txtOriginalPassword.getPassword());
            String newPassword = new String(txtNewPassword.getPassword());
            String confirmPassword = new String(txtConfirmPassword.getPassword());
            if (newPassword.equals(confirmPassword)) {
                UserService userService = new UserService();
                userService.updatePassword(username, originalPassword, newPassword);
                JOptionPane.showMessageDialog(null, "密码修改成功, 请重新启动程序并登录");
                this.setVisible(false);
                try {
                    ApplicationUtils.restartApplication();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (username.isEmpty() || originalPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(null, "用户名或密码不能为空");
            }
            if (!newPassword.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(null, "两次输入的密码不一致");
            }
        }
    }
}

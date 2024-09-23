package Login;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ForgetPasswordFrame extends JFrame implements ActionListener {
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

    public ForgetPasswordFrame() throws HeadlessException {
        c = getContentPane();
        c.setLayout(null);
        setTitle("忘记密码");
        setBounds(100, 100, 360, 179);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        lblAccount = new JLabel();
        lblAccount.setBounds(100, 23, 42, 15);
        lblAccount.setText("账号：");
        c.add(lblAccount);
        txtAccount = new JTextField();
        txtAccount.setBounds(145, 20, 96, 21);
        txtAccount.setColumns(15);
        c.add(txtAccount);

        OriginalPassword = new JLabel();
        OriginalPassword.setBounds(100, 65, 42, 15);
        OriginalPassword.setText("原密码：");
        c.add(OriginalPassword);
        txtOriginalPassword = new JPasswordField();
        txtOriginalPassword.setBounds(145, 62, 96, 21);
        txtOriginalPassword.setColumns(15);
        txtOriginalPassword.setEchoChar('*');
        c.add(txtOriginalPassword);

        NewPassword = new JLabel();
        NewPassword.setBounds(100, 107, 42, 15);
        NewPassword.setText("新密码：");
        c.add(NewPassword);
        txtNewPassword = new JPasswordField();
        txtNewPassword.setBounds(145, 104, 96, 21);
        txtNewPassword.setColumns(15);
        txtNewPassword.setEchoChar('*');
        c.add(txtNewPassword);

        ConfirmPassword = new JLabel();
        ConfirmPassword.setBounds(100, 149, 42, 15);
        ConfirmPassword.setText("确认新密码：");
        c.add(ConfirmPassword);
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(145, 146, 96, 21);
        txtConfirmPassword.setColumns(15);
        txtConfirmPassword.setEchoChar('*');
        c.add(txtConfirmPassword);

        btnConfirm = new JButton();
        btnConfirm.setBounds(150, 180, 70, 23);
        btnConfirm.setText("确定");
        c.add(btnConfirm);

        btnConfirm.addActionListener(this);
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
                JOptionPane.showMessageDialog(null, "密码修改成功");
                this.setVisible(false);
                new LoginFrame(); //返回登录界面
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

package Login;

import Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame implements ActionListener {
    JLabel Username;//用户名
    JLabel Password;//密码
    JLabel ConfirmPassword;//确认密码
    JTextField txtUsername;//用户名文本框
    JPasswordField txtPassword;//密码文本框
    JPasswordField txtConfirmPassword;//确认密码文本框
    JButton btnRegister;//注册按钮
    Container c;//声明添加组件的容器

    public RegisterFrame() throws HeadlessException {
        c = getContentPane();
        c.setLayout(null);
        setTitle("注册");
        setBounds(100, 100, 360, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        Username = new JLabel();
        Username.setBounds(100, 23, 60, 15);
        Username.setText("用户名：");
        c.add(Username);
        txtUsername = new JTextField();
        txtUsername.setBounds(160, 20, 96, 21);
        txtUsername.setColumns(15);
        c.add(txtUsername);

        Password = new JLabel();
        Password.setBounds(100, 65, 60, 15);
        Password.setText("密码：");
        c.add(Password);
        txtPassword = new JPasswordField();
        txtPassword.setBounds(160, 62, 96, 21);
        txtPassword.setColumns(15);
        txtPassword.setEchoChar('*');
        c.add(txtPassword);

        ConfirmPassword = new JLabel();
        ConfirmPassword.setBounds(100, 107, 65, 15);
        ConfirmPassword.setText("确认密码：");
        c.add(ConfirmPassword);
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(160, 104, 96, 21);
        txtConfirmPassword.setColumns(15);
        txtConfirmPassword.setEchoChar('*');
        c.add(txtConfirmPassword);

        btnRegister = new JButton();
        btnRegister.setBounds(150, 130, 70, 23);
        btnRegister.setText("注册");
        c.add(btnRegister);

        btnRegister.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            try {
                String username = txtUsername.getText().trim();
                String password = new String(txtPassword.getPassword());
                String confirmPassword = new String(txtConfirmPassword.getPassword());
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "用户名或密码不能为空", "错误", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "两次输入的密码不一致", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    UserService userService = new UserService();
                    userService.addUser(username, password);
                    JOptionPane.showMessageDialog(null, "注册成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                    this.setVisible(false);//隐藏当前窗体
                    new LoginFrame();//返回登录界面
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}

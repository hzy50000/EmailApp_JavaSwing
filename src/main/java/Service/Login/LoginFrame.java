package Service.Login;

import Service.email.EmailFrame;
import Util.BaseUtils;
import org.jdesktop.swingx.prompt.PromptSupport;
import pojo.User;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class LoginFrame extends JFrame implements ActionListener {
    JLabel lblPassword ; //密码标签
    JPasswordField pswPassword; //密码文本框
    JLabel lblAccount; //账号标签
    JTextField txtAccount; //账号文本框
    JButton btnLogin; //确定按钮
    JButton btnRegister; //注册按钮
    JButton btnChangePassword; //忘记密码按钮
    //用同样的方法声明其他界面上的组件
    Container c;//声明添加组件的容器
    public LoginFrame() throws HeadlessException, UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException//构造方法
    {
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        c = getContentPane();//获取默认的内容面板
        c.setLayout(null);//关闭默认的布局
        setTitle("登录");//设置窗体的标题
        setBounds(100, 100, 400, 179); //设置大小：窗体左上角坐标，宽度和高度
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //设置缺省的关闭动作
        this.setLocationRelativeTo(null); //设置窗体居中
//根据界面的布局，自行设计每个组件的位置和大小
        lblAccount = new JLabel();
        lblAccount.setBounds(100, 23, 42, 15);		//位置、大小
        lblAccount.setText("账号：");//设置标签上的文字
        c.add(lblAccount); //添加标签
        txtAccount = new JTextField();//创建文本框
        txtAccount.setBounds(145, 20, 96, 21); //设置文本框大小
        txtAccount.setColumns(15); //设置文本框的可输入的文字个数
        c.add(txtAccount); //往窗体中添加该文本框
        PromptSupport.setPrompt("请输入账号", txtAccount);
        lblPassword = new JLabel();
        lblPassword.setBounds(100, 65, 42, 15);
        lblPassword.setText("密码：");
        c.add(lblPassword);
        pswPassword = new JPasswordField();
        pswPassword.setBounds(145, 62, 96, 21);
        pswPassword.setColumns(15);
        pswPassword.setEchoChar('*'); //设置密码框的缺省显示字符
        c.add(pswPassword);
        PromptSupport.setPrompt("请输入密码", pswPassword);
        btnLogin = new JButton();
        btnLogin.setBounds(120, 106, 70, 23);
        btnLogin.setText("确定");
        btnRegister = new JButton();
        btnRegister.setBounds(190, 106, 70, 23);
        btnRegister.setText("注册");


        c.add(btnLogin);
        c.add(btnRegister);
//依照上述步骤设置和添加其他组件
        btnLogin.addActionListener(this);	//依次在其他按钮上添加动作事件监听器
        btnRegister.addActionListener(e -> {
            // 创建并显示 RegisterFrame 实例
            new RegisterFrame();
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 只关闭当前窗口
        this.setVisible(true);//设置窗体可见，放在最后一条

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==btnLogin) //判别是哪个按钮触发了该动作事件
        {
            try
            {
                String name=txtAccount.getText().trim();
                String pass=new String(pswPassword.getPassword()); //获取界面上的文本框内容
                UserService users=new UserService();//调用后端代码进行验证
                boolean f=users.verifyUser(new User(name,pass));
                if(f)
                {
                    //允许登录
                    JOptionPane.showMessageDialog(null,"登陆成功");
                    BaseUtils.setCurrentId(name);
                    this.setVisible(false);//隐藏当前窗体
                    //邮件客户端主页...
                    new EmailFrame();
                }
                else
                    JOptionPane.showMessageDialog(null,"本用户不存在，请先注册");
            }
            catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
    }
}

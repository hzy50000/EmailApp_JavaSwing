package Service.email;

import Service.PasswordManage.PasswordManageFrame;
import pojo.RoundBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

public class EmailFrame extends JFrame implements ActionListener {

    Container c; // 声明添加组件的容器
    JButton userSetButton;
    JButton PasswordManagerButton;

    public EmailFrame() throws HeadlessException, MalformedURLException { // 构造方法
        c = getContentPane(); // 获取默认的内容面板
        c.setLayout(null); // 关闭默认的布局
        setTitle("Service/email"); // 设置窗体的标题
        setBounds(100, 100, 900, 700); // 设置大小：窗体左上角坐标，宽度和高度
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置缺省的关闭动作
        this.setLocationRelativeTo(null); // 设置窗体居中



        // 用户设置按钮
        userSetButton = new JButton();
        userSetButton.setBounds(750, 30, 80, 60);
        userSetButton.setBorder(new RoundBorder(Color.GRAY)); // 设置按钮的边框
        userSetButton.addActionListener(e -> {
            new SendEmailFrame();
        });
        URL url = new URL("https://cdn.acwing.com/media/user/profile/photo/387899_lg_d52d98998e.jpg");
        if (url != null) {
            ImageIcon icon = new ImageIcon(url);
            userSetButton.setIcon(icon);
        } else {
            System.err.println("图像文件未找到");
        }
        JButton newEmailButton = new JButton();
        newEmailButton.setText("新建邮件");
        newEmailButton.setBounds(50, 30, 200, 60);
        newEmailButton.setBorder(new RoundBorder(Color.GRAY)); // 设置按钮的边框
        newEmailButton.setBackground(Color.white);
        newEmailButton.addActionListener(e -> {
            new SendEmailFrame();
        });
        c.add(newEmailButton);

        PasswordManagerButton = new JButton();
        PasswordManagerButton.setText("密码管理");
        PasswordManagerButton.setBounds(500, 30, 200, 60);
        PasswordManagerButton.setBorder(new RoundBorder(Color.GRAY)); // 设置按钮的边框
        PasswordManagerButton.setBackground(Color.white);
        PasswordManagerButton.addActionListener(e -> {
            new PasswordManageFrame();
        });
        c.add(PasswordManagerButton);




        JButton draftBoxButton = new JButton();
        draftBoxButton.setText("草稿箱");
        draftBoxButton.setBounds(270, 30, 200, 60);
        draftBoxButton.setBorder(new RoundBorder(Color.GRAY)); // 设置按钮的边框
        draftBoxButton.setBackground(Color.white);
        c.add(draftBoxButton);


        // 初始化 c2
        JPanel c2 = new JPanel();
//        c2.setBackground(Color.LIGHT_GRAY);
        c2.setLayout(new GridLayout(50, 1, 0, 0)); // 使用 GridLayout 确保组件垂直排列

        for (int i = 0; i < 50; i++) {
            JButton button = new JButton("button" + i);
            button.setPreferredSize(new Dimension(200, 50)); // 设置按钮的首选尺寸
            button.setBackground(Color.white);
            button.setBorder(new RoundBorder(Color.GRAY)); // 设置按钮的边框
            c2.add(button);
        }

        JScrollPane scrollPane = new JScrollPane(c2);
        scrollPane.setBorder(new RoundBorder(Color.GRAY)); // 设置滚动面板的边框
        scrollPane.setBounds(50, 100, 300, 500); // 设置滚动面板的位置和大小

        JTextField jTextField = new JTextField();
        jTextField.setBounds(380, 100, 450, 500);
        jTextField.setBorder(new RoundBorder(Color.GRAY)); // 设置文本框的边框
        c.add(jTextField);
        c.add(scrollPane);
        c.add(userSetButton);

        this.setVisible(true); // 设置窗体可见，放在最后一条
    }

    public void actionUserSetButton() {

    }

    public static void main(String[] args) throws MalformedURLException {
        new EmailFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
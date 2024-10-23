import javax.swing.*;

public class TestStyle {
    public static void main(String[] args) {
        UIManager.LookAndFeelInfo []info = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo lookAndFeelInfo : info) {
            System.out.println(lookAndFeelInfo.getClassName());
        }
    }
}

package Login;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;

public class UserService {
    public UserService() {
    }

    public void updatePassword(String username, String originalPassword, String newPassword) {
        String sql = "UPDATE user SET password = ? WHERE username = ? AND password = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, originalPassword);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public class DBUtil {
        private static final String URL = "jdbc:mysql://localhost:3306/email?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "87651361";

        static {  // 加载一下驱动
            try {
                Class.forName(DRIVER);  // 执行com.mysql.cj.jdbc.Driver 里面的静态代码块
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        // 获取数据库连接
        public static @Nullable Connection getConnection() throws SQLException {
            try {
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        // 关闭连接
        public static void closeConnection(Connection connection) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void closePS(PreparedStatement preparedStatement) {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void closeRS(ResultSet resultSet) {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addUser(String username, String password) {
        String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除用户
    public void deleteUser(String username) {
        String sql = "DELETE FROM user WHERE username = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 更新用户密码
    public void updateUser(String username, String newPassword) {
        String sql = "UPDATE user SET password = ? WHERE username = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 查询用户
    public boolean findUser(String username, String password) {
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (Connection connection = DBUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //验证用户
    public boolean verifyUser(@NotNull User user) {
        return findUser(user.getUsername(), user.getPassword());
    }


}

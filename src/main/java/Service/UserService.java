package Service;

import pojo.User;
import Mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public class UserService {
    private final UserMapper userMapper;

    public UserService() {
        // 加载MyBatis配置文件
        InputStream inputStream = UserService.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        this.userMapper = sqlSession.getMapper(UserMapper.class);
    }

    public void addUser(String username, String password) {
        userMapper.addUser(username, password);
    }

    public void deleteUser(String username) {
        userMapper.deleteUser(username);
    }

    public void updateUser(String username, String newPassword) {
        userMapper.updateUser(username, newPassword);
    }

    public boolean findUser(String username, String password) {
        User user = userMapper.findUser(username, password);
        return user != null;
    }

    public boolean verifyUser(User user) {
        return findUser(user.getUsername(), user.getPassword());
    }

    public void updatePassword(String username, String originalPassword, String newPassword) {
        if (findUser(username, originalPassword)) {
            updateUser(username, newPassword);
        }
    }
}
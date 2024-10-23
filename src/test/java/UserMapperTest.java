import Service.Login.LoginFrame;
import Mapper.UserMapper;
import Util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;

import javax.swing.*;
import java.util.List;
import java.util.Scanner;

public class UserMapperTest {
    //第一步：获得sqlSession对象
    SqlSession sqlSession;
    @Test
    public void test(){
        try {
            sqlSession = MybatisUtils.getSqlSession();
            //方式一：getMapper
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
            mapper.deleteUser("hzy");
            sqlSession.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //关闭sqlSession
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void test1() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        LoginFrame loginFrame = new LoginFrame();
        Scanner input = new Scanner(System.in);
        int a = input.nextInt();
    }
}

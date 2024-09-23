import Mapper.UserMapper;
import Util.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import pojo.User;

import java.util.List;

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
}

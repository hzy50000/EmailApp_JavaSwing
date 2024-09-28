package Service;

import Mapper.EmailPasswordMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pojo.EmailPassword;

import java.io.InputStream;
import java.util.List;

public class EmailPasswordService {
    private final EmailPasswordMapper emailPasswordMapper;

    public EmailPasswordService(EmailPasswordMapper emailPasswordMapper) {
        // 加载MyBatis配置文件
        InputStream inputStream = UserService.class.getClassLoader().getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        this.emailPasswordMapper = sqlSession.getMapper(EmailPasswordMapper.class);
    }

    public void addEmailPassword(String emailaccount, String emailpassword) {
        emailPasswordMapper.addEmailPassword(emailaccount, emailpassword);
    }

    public void deleteEmailPassword(String emailaccount) {
        emailPasswordMapper.deleteEmailPassword(emailaccount);
    }

    public void updateEmailPassword(String emailaccount, String emailpassword) {
        emailPasswordMapper.updateEmailPassword(emailaccount, emailpassword);
    }

    public String getEmailPassword(String emailaccount) {
        return emailPasswordMapper.getEmailPassword(emailaccount);
    }

    public List<EmailPassword> getallEmailPassword() {
        return emailPasswordMapper.getAllEmailPassword();
    }
}

package Mapper;

import org.apache.ibatis.annotations.Param;
import pojo.EmailPassword;

import java.util.List;
import java.util.Map;

public interface EmailPasswordMapper {
    void addEmailPassword(@Param("emailaccount") String emailaccount, @Param("emailpassword") String emailpassword);
    void deleteEmailPassword(@Param("emailaccount") String emailaccount);
    void updateEmailPassword(@Param("emailaccount") String emailaccount, @Param("emailpassword") String emailpassword);
    String getEmailPassword(@Param("emailaccount") String emailaccount);

    List<EmailPassword> getAllEmailPasswords();
}

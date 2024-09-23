package Mapper;

import org.apache.ibatis.annotations.*;
import pojo.User;
import java.util.List;

public interface UserMapper {
    List<User> getUserList();
    void addUser(@Param("username") String username, @Param("password") String password);
    void deleteUser(@Param("username") String username);
    void updateUser(@Param("username") String username, @Param("password") String password);
    User findUser(@Param("username") String username, @Param("password") String password);
}
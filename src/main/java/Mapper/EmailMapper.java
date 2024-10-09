package Mapper;

import org.apache.ibatis.annotations.*;
import pojo.Message;


import java.sql.Timestamp;

public interface EmailMapper {
    void addEmail(@Param("id") String id, @Param("sendUser") String sendUser, @Param("receiveUser") String receiveUser, @Param("subject") String subject, @Param("sendTime") Timestamp sentTime, @Param("content") String content, @Param("username") String username);

    Message[] getEmails(@Param("username") String username);
}
package Mapper;

import org.apache.ibatis.annotations.*;

import java.time.LocalDateTime;

public interface EmailMapper {
    void addEmail(@Param("id") String id, @Param("sendUser") String sendUser, @Param("receiveUser") String receiveUser, @Param("subject") String subject, @Param("content") String content);
}
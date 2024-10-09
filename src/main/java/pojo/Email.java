package pojo;


import java.sql.Timestamp;

public class Email {
    private String id;
    private String sendUser;
    private String receiveUser;
    private Timestamp sentTime;
    private String subject;
    private String content;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Email() {

    }
    public Email(String id, String sendUser, String receiveUser, Timestamp sentTime, String subject, String content) {
        this.id = id;
        this.sendUser = sendUser;
        this.receiveUser = receiveUser;
        this.sentTime = sentTime;
        this.subject = subject;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendUser() {
        return sendUser;
    }

    public void setSendUser(String sendUser) {
        this.sendUser = sendUser;
    }

    public String getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }

    public Timestamp getSentTime() {
        return sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

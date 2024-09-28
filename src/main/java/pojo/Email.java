package pojo;


public class Email {
    private String id;
    private String sendUser;
    private String receiveUser;
//    private String sentTime;
    private String subject;
    private String content;

    public Email() {

    }
    public Email(String id, String sendUser, String receiveUser, String sentTime, String subject, String content) {
        this.id = id;
        this.sendUser = sendUser;
        this.receiveUser = receiveUser;
//        this.sentTime = sentTime;
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

//    public String getSentTime() {
//        return sentTime;
//    }
//
//    public void setSentTime(String sentTime) {
//        this.sentTime = sentTime;
//    }

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

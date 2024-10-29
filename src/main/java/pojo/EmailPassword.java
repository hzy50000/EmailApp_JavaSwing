package pojo;

public class EmailPassword {
    private String emailaccount;
    private String emailpassword;
    private Integer userId;

    public EmailPassword(String emailaccount, String emailpassword) {
        this.emailaccount = emailaccount;
        this.emailpassword = emailpassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmailaccount() {
        return emailaccount;
    }

    public void setEmailaccount(String emailaccount) {
        this.emailaccount = emailaccount;
    }

    public String getEmailpassword() {
        return emailpassword;
    }

    public void setEmailpassword(String emailpassword) {
        this.emailpassword = emailpassword;
    }
}

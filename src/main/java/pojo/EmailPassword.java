package pojo;

public class EmailPassword {
    private String emailaccount;
    private String emailpassword;

    public EmailPassword(String emailaccount, String emailpassword) {
        this.emailaccount = emailaccount;
        this.emailpassword = emailpassword;
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

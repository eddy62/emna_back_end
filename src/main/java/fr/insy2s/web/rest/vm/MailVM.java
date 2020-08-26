package fr.insy2s.web.rest.vm;

public class MailVM {

    private String email;

    public MailVM() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "MailVM{" + email + "} ";
    }
}

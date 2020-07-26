package javafxsolo.Modele;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String password;
    private int userQuestion;
    private String userAnswer;
    public User(String name,String password,int Question,String answer){
        this.userName = name;
        this.password  = password;
        this.userQuestion =Question;
        this.userAnswer= answer;

    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getUserQuestion() {
        return userQuestion;
    }

    public String getUserAnswer() {
        return userAnswer;
    }
}

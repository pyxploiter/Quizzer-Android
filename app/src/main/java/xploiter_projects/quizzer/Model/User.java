package xploiter_projects.quizzer.Model;

import java.io.Serializable;

/**
 * Created by Asad on 12/24/2017.
 */

public class User implements Serializable {
    public String userName;
    public String type;
    protected String email;
    protected String password;

    public User(){}

    public User(String name, String t, String mail, String pwd){
        this.userName = name;
        this.type = t;
        this.email = mail;
        this.password = pwd;
    }

    public String getUserName(){
        return this.userName;
    }

    public String getType(){
        return this.type;
    }

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public void setUserName(String name){
        this.userName = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public void setEmail(String mail){
        this.email = mail;
    }

    public void setPassword(String pwd){
        this.password = pwd;
    }
}

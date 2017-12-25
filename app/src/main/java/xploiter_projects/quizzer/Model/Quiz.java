package xploiter_projects.quizzer.Model;

import java.io.Serializable;

/**
 * Created by Asad on 12/24/2017.
 */

public class Quiz{
    protected int id;
    protected String title;
    protected String description;

    public Quiz() {
    }

    public Quiz(int id, String title, String description){
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setDescription(String desc){
        this.description = desc;
    }
}

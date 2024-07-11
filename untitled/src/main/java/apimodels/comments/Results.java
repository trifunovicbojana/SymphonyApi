package apimodels.comments;

import apimodels.User;

public class Results {

    private Integer id;
    private User user;
    private String createdOn;
    private String text;
    public Results(Integer id, User user, String createdOn, String text) {
        this.id = id;
        this.user = user;
        this.createdOn = createdOn;
        this.text = text;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}

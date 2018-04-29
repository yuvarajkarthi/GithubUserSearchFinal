package com.android.githubuser.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by delaroy on 3/22/17.
 */
public class Item {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;
    @SerializedName("html_url")
    @Expose
    private String htmlUrl;

    @SerializedName("score")
    @Expose
    private String score;

    public Item(String login, String avatarUrl, String htmlUrl, String score){
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.htmlUrl = htmlUrl;
        this.score=score;
    }

    public String getLogin(){
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl){
        this.avatarUrl = avatarUrl;
    }

    public String getHtmlUrl(){
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl){
        this.htmlUrl = htmlUrl;
    }

    public String getscore() {
        return  score;
    }
    public void setScore(String score){
        this.score=score;
    }
}

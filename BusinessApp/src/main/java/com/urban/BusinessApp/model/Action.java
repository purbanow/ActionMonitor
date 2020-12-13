package com.urban.BusinessApp.model;

import org.springframework.data.annotation.Id;

public class Action {

    @Id
    private String id;

    private String issuer;
    private String content;

    public Action() {}

    public Action(String issuer, String content) {
        this.issuer = issuer;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Action{" +
                "id='" + id + '\'' +
                ", issuer='" + issuer + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

package com.example.website.models.bindingModels;

import javax.validation.constraints.NotNull;

public class PostDto {

    @NotNull
    private String title;
    @NotNull
    private String content;

    public PostDto() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.mathheals.meajuda.model;

public class Topic {

    private int topicId;
    private int categoryId;
    private String title;
    private String description;
    private String nameOwner;

    public Topic(String title, String description, String nameOwner) {
        setTitle(title);
        setDescription(description);
        setNameOwner(nameOwner);
    }

    public int getTopicId() {
        return topicId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    private void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    private void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private void setTitle(String title){
        this.title = title;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }
}

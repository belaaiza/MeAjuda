package com.mathheals.meajuda.model;

public class Topic {

    private int categoryId;
    private String title;
    private String description;

    public Topic(int categoryId, String title, String description) {
        setCategoryId(categoryId);
        setTitle(title);
        setDescription(description);
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

    private void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    private void setTitle(String title){
        this.title = title;
    }

    private void setDescription(String description) {
        this.description = description;
    }

}

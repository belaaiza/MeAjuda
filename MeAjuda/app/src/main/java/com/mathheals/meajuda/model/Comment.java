package com.mathheals.meajuda.model;

public class Comment {
    private Integer idComment;
    private Integer idParent;
    private Integer idTopic;
    private Integer idCategory;
    private String description;

    public Comment(Integer idParent, Integer idTopic, Integer idCategory, String description) {
        setIdParent(idParent);
        setIdTopic(idTopic);
        setIdCategory(idCategory);
        setDescription(description);
    }

    //Provisory constructor
    public Comment(Integer idParent, String description) {
        setIdParent(idParent);
        setDescription(description);
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    public void setIdParent(Integer idParent) {
        this.idParent = idParent;
    }

    public void setIdTopic(Integer idTopic) {
        this.idTopic = idTopic;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIdComment() {
        return idComment;
    }

    public Integer getIdParent() {
        return idParent;
    }

    public Integer getIdTopic() {
        return idTopic;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public String getDescription() {
        return description;
    }
}

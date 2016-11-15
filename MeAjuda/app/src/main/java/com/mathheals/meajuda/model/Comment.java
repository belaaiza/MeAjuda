package com.mathheals.meajuda.model;

public class Comment {
    private Integer idComment;
    private Integer idTopic;
    private Integer idCategory;
    private Integer idUser;
    private String description;
    private String imageURL;
    private String audioURL;
    private String nameUser;

    public Comment(Integer idComment, Integer idTopic, Integer idCategory, Integer idUser,
                   String description, String imageURL, String audioURL, String nameUser) {
        setIdComment(idComment);
        setIdTopic(idTopic);
        setIdCategory(idCategory);
        setIdUser(idUser);
        setDescription(description);
        setImageURL(imageURL);
        setAudioURL(audioURL);
        setNameUser(nameUser);
    }

    //Provisory constructor
    public Comment(String description) {
        setDescription(description);
    }

    public void setIdComment(Integer idComment) {
        this.idComment = idComment;
    }

    private void setIdTopic(Integer idTopic) {
        this.idTopic = idTopic;
    }

    private void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    private void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    private void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    private void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public Integer getIdComment() {
        return idComment;
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

    public String getImageURL() {
        return imageURL;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getNameUser() {
        return nameUser;
    }
}

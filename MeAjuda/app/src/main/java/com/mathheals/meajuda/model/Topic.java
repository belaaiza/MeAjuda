package com.mathheals.meajuda.model;

public class Topic {

    private int idTopic;
    private int idCategory;
    private String title;
    private String description;
    private String nameOwner;
    private String imageURL;
    private String audioURL;

    public Topic(Integer idTopic, Integer idCategory, String title, String description,
                 String nameOwner, String imageURL, String audioURL) {
        setIdTopic(idTopic);
        setIdCategory(idCategory);
        setTitle(title);
        setDescription(description);
        setNameOwner(nameOwner);
        setImageURL(imageURL);
        setAudioURL(audioURL);
    }

    public Topic(int idTopic, String title, String description, String nameOwner) {
        setIdTopic(idTopic);
        setTitle(title);
        setDescription(description);
        setNameOwner(nameOwner);
    }

    public int getIdTopic() {
        return idTopic;
    }

    public int getIdCategory() {
        return idCategory;
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

    public String getImageURL() {
        return imageURL;
    }

    public String getAudioURL() { return audioURL; }

    private void setIdTopic(int idTopic) {
        this.idTopic = idTopic;
    }

    private void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    private void setTitle(String title){
        this.title = title;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    private void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    private void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }
}

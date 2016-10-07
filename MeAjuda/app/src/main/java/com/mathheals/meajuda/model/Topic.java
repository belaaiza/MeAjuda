package com.mathheals.meajuda.model;

public class Topic {

    private int idTopic;
    private int idCategory;
    private String title;
    private String description;
    private String nameOwner;

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

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }
}

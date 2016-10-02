/**
 * File: Category.java
 * Purpose: Represents the post category entity
 */

package com.mathheals.meajuda.model;

public class Category {

    private int idCategory;
    private String name;
    private String color;
    private Integer idIcon;

    public Category(int idCategory, String name, String color, Integer idIcon){
        setIdCategory(idCategory);
        setName(name);
        setColor(color);
        setIdIcon(idIcon);
    }

    public String getColor(){
        return color;
    }

    public Integer getIdIcon(){
        return idIcon;
    }

    public String getName(){
        return name;
    }

    public int getIdCategory(){
        return idCategory;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setIdIcon(Integer idIcon){
        this.idIcon = idIcon;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setIdCategory(int idCategory){
        this.idCategory = idCategory;
    }
}

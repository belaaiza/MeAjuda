/**
 * File: Category.java
 * Purpose: Represents the post category entity
 */

package com.mathheals.meajuda.model;

public class Category {

    private int idCategory;
    private String name;
    private String color;
    private String iconName;

    public Category(int idCategory, String name, String color, String iconName){
        setIdCategory(idCategory);
        setName(name);
        setColor(color);
        setIconName(iconName);
    }

    public String getColor(){
        return color;
    }

    public String getIconName(){
        return iconName;
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

    public void setIconName(String iconName){
        this.iconName = iconName;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setIdCategory(int idCategory){
        this.idCategory = idCategory;
    }
}

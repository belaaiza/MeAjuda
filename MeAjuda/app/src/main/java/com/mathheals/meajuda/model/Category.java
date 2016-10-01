/**
 * File: Category.java
 * Purpose: Represents the post category entity
 */

package com.mathheals.meajuda.model;

public class Category {

    private int idCategory;
    private String name;

    public Category(int idCategory, String name){
        setIdCategory(idCategory);
        setName(name);
    }

    public String getName(){
        return name;
    }

    public int getIdCategory(){
        return idCategory;
    }

    private void setName(String name){
        this.name = name;
    }

    private void setIdCategory(int idCategory){
        this.idCategory = idCategory;
    }
}

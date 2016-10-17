package com.mathheals.meajuda.model;

public class Group {
    private Integer idGroup;
    private Integer idOwner;
    private String name;

    public Group(Integer idOwner, String name) {
        setIdOwner(idOwner);
        setName(name);
    }

    private void setIdGroup(Integer idGroup) {
        this.idGroup = idGroup;
    }

    private void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Integer getIdGroup() {
        return idGroup;
    }

    public Integer getIdOwner() {
        return idOwner;
    }

    public String getName() {
        return name;
    }
}

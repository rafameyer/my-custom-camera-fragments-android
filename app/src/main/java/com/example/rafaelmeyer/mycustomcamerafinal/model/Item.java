package com.example.rafaelmeyer.mycustomcamerafinal.model;

/**
 * Created by rafael.meyer on 11/4/16.
 */
public class Item {

    private String file;
    private Boolean isSelected;

    public Item(String file, Boolean isSelected) {
        this.file = file;
        this.isSelected = isSelected;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

}

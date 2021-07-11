/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author TamDV
 */
public class Description {

    private String text;
    private String italicText;
    private String picture;

    public Description(String text, String italicText, String picture) {
        this.text = text;
        this.italicText = italicText;
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getItalicText() {
        return italicText;
    }

    public void setItalicText(String italicText) {
        this.italicText = italicText;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}

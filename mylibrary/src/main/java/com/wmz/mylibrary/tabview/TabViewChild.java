package com.wmz.mylibrary.tabview;



public class TabViewChild {
    private int imageViewSelIcon;
    private int imageViewUnSelIcon;
    private String textViewText;


    private TabViewChild(){

    }
    public TabViewChild(int imageViewSelIcon,int imageViewUnSelIcon,String textViewText) {
        this.imageViewSelIcon = imageViewSelIcon;
        this.imageViewUnSelIcon=imageViewUnSelIcon;
        this.textViewText = textViewText;
    }


    public int getImageViewSelIcon() {
        return imageViewSelIcon;
    }


    public void setImageViewSelIcon(int imageViewSelIcon) {
        this.imageViewSelIcon = imageViewSelIcon;
    }


    public int getImageViewUnSelIcon() {
        return imageViewUnSelIcon;
    }


    public void setImageViewUnSelIcon(int imageViewUnSelIcon) {
        this.imageViewUnSelIcon = imageViewUnSelIcon;
    }


    public String getTextViewText() {
        return textViewText;
    }


    public void setTextViewText(String textViewText) {
        this.textViewText = textViewText;
    }

}

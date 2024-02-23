package com.example.tpleboncoin;


public class AdModel {
    private String title;
    private String address;
    private String image;

    private String telephone;

    // Constructor
    public AdModel(String title, String address, String image, String telephone) {
        this.title = title;
        this.address = address;
        this.image = image;
        this.telephone = telephone;
    }

    // Getter and Setter
    public String getTitle() {return title;}
    public String getAddress(){return address;}
    public String getImage(){return image;}
    public String getTelephone(){return telephone;}

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAddress(String address) {this.address = address;}
    public void setImage(String image) {this.image = image;}
    public void setTelephone(String telephone){this.telephone = telephone;}
}

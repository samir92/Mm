package com.samer.zamzam;



public class AppList {
    public String Title,name;
    public int Img,Id;




    public AppList(String name, int img){
        this.name = name;
        this.Img = img;
    }

    public AppList(int id,String title) {
        Id=id;
        Title =title;}


    public int getId() {
        return Id;
    }
    public void setid(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String Name) {
        Title = Name;
    }

    public String getTitle() {
        return Title;
    }
    public void setTitle(String title) {
        Title = title;
    }



    public int getImg() {
        return Img;
    }
    public void setImg(int img) {
        Img = img;
    }
}

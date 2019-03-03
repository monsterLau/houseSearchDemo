package com.lau.houseSearchDemo.domain;

import java.util.List;

public class House {
    private Integer houseId;
    private int price;
    private int room;
    private int area;
    private int kitchen;
    private int bathroom;
    private String subway;
    private String tittle;

    private List<User> users;

    private String img;
    private Integer userId;

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getKitchen() {
        return kitchen;
    }

    public void setKitchen(int kitchen) {
        this.kitchen = kitchen;
    }

    public int getBathroom() {
        return bathroom;
    }

    public void setBathroom(int bathroom) {
        this.bathroom = bathroom;
    }

    public String getSubway() {
        return subway;
    }

    public void setSubway(String subway) {
        this.subway = subway;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public Integer getuserId() {
        return userId;
    }

    public void setuserId(Integer userId) {
        this.userId = userId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public House(Integer houseId, int price, int room, int area, int kitchen, int bathroom, String subway, String tittle, List<User> users, String img, Integer userId) {
        this.houseId = houseId;
        this.price = price;
        this.room = room;
        this.area = area;
        this.kitchen = kitchen;
        this.bathroom = bathroom;
        this.subway = subway;
        this.tittle = tittle;
        this.users = users;
        this.img = img;
        this.userId = userId;
    }

    public House() {
    }

    @Override
    public String toString() {
        return "House{" +
                "houseId=" + houseId +
                ", price=" + price +
                ", room=" + room +
                ", area=" + area +
                ", kitchen=" + kitchen +
                ", bathroom=" + bathroom +
                ", subway='" + subway + '\'' +
                ", tittle='" + tittle + '\'' +
                ", users=" + users +
                ", img='" + img + '\'' +
                ", userId=" + userId +
                '}';
    }
}

package com.lau.houseSearchDemo.domain;

public class Order {

    private Integer orderId;

//    用户名
    private String username;

//    房屋id
    private Integer houseId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Order(Integer orderId, String username, Integer houseId) {
        this.orderId = orderId;
        this.username = username;
        this.houseId = houseId;
    }

    public Order() {

    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", username='" + username + '\'' +
                ", houseId=" + houseId +
                '}';
    }
}

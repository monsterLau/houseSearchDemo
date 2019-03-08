package com.lau.houseSearchDemo;

import com.lau.houseSearchDemo.domain.House;
import com.lau.houseSearchDemo.domain.User;
import com.lau.houseSearchDemo.service.UserService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HouseSearchDemoApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    public void test() {
        List<House> houseList = userService.findHouseByConditions(0, 10000, "1号线", 4, 4, 1, 50);
        if (houseList.size() > 0) {
            for (House house : houseList) {
                System.out.println(house);
            }
        }
    }

    //	测试新增房屋信息
    @Test
    public void testInsertHouse() {
        House house = new House();
        int i;
        for (i = 10; i < 30; i++) {
            house.setSubway(i + "号线");
            int j = i - 3000;
            house.setTittle("广州富力金港城  精美" + j + "房  电梯中层");
            house.setPrice(i + 1500);
            house.setKitchen(i);
            house.setBathroom(i);
            house.setArea(i);
            house.setRoom(i);
            house.setImg("/houseImg/3.jpg");
            userService.insertHouse(house);
        }
    }

    @Test
//    deleteByHouseId
    public void deleteByHouseId() {
        userService.deleteHouseById(4);
        userService.deleteHouseById(2);
        userService.deleteHouseById(3);
    }

    @Test
/**
 * 查询用户信息byUsername
 */
    public void findUserByusername() {
        User user = userService.findUserByuserName("liujunjie123");
        System.out.println(user);
    }

    /**
     * 根据houseId查询房屋信息
     */
    @Test
    public void findHouseByhouseId() {
        House house = userService.findHouseByhouseId(19);
        System.out.println(house);
    }


    @Test
    public void allHouseByPrice() {
        List<House> houseList = userService.allHouseByPriceFromHighToLow();
        for (House house : houseList) {
            System.out.println(house);
        }
    }

    @Test
    public void adminFindUserOrder() {
        String houseUsername="";
        List<House> houseList = userService.adminFindUserOrder(houseUsername);
        for (House house : houseList) {
            System.out.println(house);
        }
        System.out.println(houseList.size());
    }

    @Test
    public void updateIsOrder(){
        userService.updateIsOrder(1,17);
    }

    @Test
    public void findHouseOrderByUsername(){
        List<House> house=userService.findHouseOrderByUsername("liujunjie123");
        System.out.println(house);
    }
}



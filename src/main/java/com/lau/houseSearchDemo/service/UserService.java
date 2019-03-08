package com.lau.houseSearchDemo.service;

import com.lau.houseSearchDemo.domain.House;
import com.lau.houseSearchDemo.domain.User;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    List<User> getAll();

    //    用户注册
    public void registerUser(User user);

    //    用户注册
    public void registerAdmin(User user);

    //    查询用户名重复
    Long sameUsername(String username);

    //    查找用户信息byUsername
    User findUserByuserName(String username);

    //    更新用户信息
    void updateUserByusername(User user);

    //    查询所有房间信息
    List<House> allHouse();

    //    查询所有房间信息
    List<House> allHouseByHouseUsername(String houseUsername);

    //    根据房价排列房屋信息
    List<House> allHouseByPriceFromHighToLow();

    //    价格从高到低排列房屋信息
    List<House> allHouseByPriceFromLowToHigh();

    //    关键字查询房屋信息
    List<House> findHouseBykeyWord(String keyWord);

    //    条件查询房屋信息
    List<House> findHouseByConditions(Integer lowPrice, Integer topPrice, String subway, Integer room, Integer bathroom,
                                      Integer lowArea, Integer topArea);

    //    添加房屋信息
    void insertHouse(House house);

    //    根据houseId查找房屋信息
    House findHouseByhouseId(Integer houseId);

    //    删除房屋信息ByhouseId
    void deleteHouseById(Integer id);

    //    根据houseId更新房屋信息
    void updateHouseByHouseId(House house);

    //    添加收藏信息
    void addHouseOrder(String username, Integer houseId);

    /**
     * 删除房屋收藏信息
     *
     * @param username
     * @param houseId
     */
    void deleteHouseOrderByUsernameAndHouseId(String username, Integer houseId);

    /**
     * 查询单个用户所有房屋预约信息
     *
     * @param username
     * @return
     */
    List<House> findHouseOrderByUsername(@Param("username") String username);

    /**
     * 后台用户查看用户预约信息
     *
     * @return
     */
    List<House> adminFindUserOrder(String houseUsername);

    //    修改房屋状态————》是否被预约  0：未被预约  1：已被预约
    void updateIsOrder(Integer i,Integer houseId);
}

package com.lau.houseSearchDemo.service;

import com.lau.houseSearchDemo.domain.House;
import com.lau.houseSearchDemo.domain.User;
import com.lau.houseSearchDemo.mapper.UserMapper;
import com.lau.houseSearchDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userMapper.getAll();
    }

    @Override
    public void registerUser(User user) {

//        添加用户
        userMapper.registerUser(user);
//        username查询用户ID
        String id = userMapper.getUserIdByUsername(user.getUsername());
//        添加权限->1
        userMapper.addRole(Integer.valueOf(id), Integer.valueOf("1"));
    }

    //    用户名是否重复
    @Override
    public Long sameUsername(String username) {
        return userMapper.sameUsername(username);
    }

    //    查询所有房屋信息
    @Override
    public List<House> allHouse() {
        return userMapper.allHouse();
    }

    @Override
    public List<House> allHouseByPriceFromHighToLow() {
        return userMapper.allHouseByPriceFromHighToLow();
    }

    @Override
    public List<House> allHouseByPriceFromLowToHigh() {
        return userMapper.allHouseByPriceFromLowToHigh();
    }

    //    关键字查询房屋信息
    @Override
    public List<House> findHouseBykeyWord(String keyWord) {
        return userMapper.findHouseBykeyWord(keyWord);
    }

    //    条件查询房屋信息
    @Override
    public List<House> findHouseByConditions(Integer lowPrice, Integer topPrice, String subway, Integer room, Integer bathroom, Integer lowArea, Integer topArea) {
        return userMapper.findHouseByConditions(lowPrice, topPrice, subway, room, bathroom, lowArea, topArea);
    }

    @Override
    public void insertHouse(House house) {
        userMapper.insertHouse(house);
    }

    @Override
    public House findHouseByhouseId(Integer houseId) {

        return userMapper.findHouseByhouseId(houseId);
    }

    @Override
    public void deleteHouseById(Integer id) {
        userMapper.deleteByHouseId(id);
    }

    @Override
    public void updateHouseByHouseId(House house) {
        userMapper.updateHouseByHouseId(house);
    }

    @Override
    public void addHouseOrder(String username, Integer houseId) {
        userMapper.addHouseOrder(username, houseId);
    }

    @Override
    public void deleteHouseOrderByUsernameAndHouseId(String username, Integer houseId) {
        userMapper.deleteHouseOrderByUsernameAndHouseId(username, houseId);
    }

    @Override
    public List<House> findHouseOrderByUsername(String username) {

        return userRepository.findHouseOrderByUsername(username);
    }

    @Override
    public List<House> adminFindUserOrder() {
        return userRepository.adminFindUserOrder();
    }

    @Override
    public User findUserByuserName(String username) {
        User user = userMapper.findUserByUsername(username);
        return user;
    }

    @Override
    public void updateUserByusername(User user) {
        userMapper.updateUser(user);
    }


//

}

package com.lau.houseSearchDemo.repository;

import com.lau.houseSearchDemo.domain.House;
import com.lau.houseSearchDemo.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {

    User findByUsername(@Param("username") String username);

    List<House> findHouseOrderByUsername(@Param("username")String username);

    List<House> adminFindUserOrder(@Param("houseUsername")String houseUsername);

}

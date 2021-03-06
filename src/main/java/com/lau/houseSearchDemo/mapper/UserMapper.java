package com.lau.houseSearchDemo.mapper;

import com.lau.houseSearchDemo.domain.House;
import com.lau.houseSearchDemo.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.websocket.server.PathParam;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user")
    List<User> getAll();

    //    用户注册
    //    添加用户信息
    @Insert("insert into user (username,password,phone,email,realName) values (#{username},#{password},#{phone},#{email},#{realName})")
    public void registerUser(User user);

    //    根据username查找用户id
    @Select("select id from user where username=#{username}")
    String getUserIdByUsername(@Param("username") String username);

    //    添加用户权限->1
    @Insert("insert into user_role  values (#{user_id},#{role_id})")
    void addRole(@Param("user_id") Integer user_id, @Param("role_id") Integer role_id);

    //    查询重复用户名
    @Select("SELECT COUNT(username) from user where username=#{username}")
    Long sameUsername(@Param("username") String username);

    //    获取用户信息byUsername
    @Select("select * from user where username=#{username}")
    User findUserByUsername(@Param("username") String username);

    //    修改用户信息
    @Update("UPDATE user set realName=#{realName},email=#{email},phone=#{phone} where username=#{username}")
    void updateUser(User user);

    //    查询所有房屋信息
    @Select("select * from house where isOrder=0 and isSell=0 Order by houseId")
    List<House> allHouse();

    //    查询所有房屋信息
    @Select("select * from house where houseUsername=#{houseUsername} Order by houseId")
    List<House> allHouseByHouseUsername(@Param("houseUsername") String houseUsername);

    //查询是否已租房屋信息
    @Select("select * from house where isSell=#{isSell} and houseUsername=#{houseUsername}")
    List<House> findHouseIsSell(@Param("isSell")Integer isSell,@Param("houseUsername") String houseUsername);


    //设置房屋是否放租
    @Update("update house set isSell=#{isSell} where houseId=#{houseId}")
    void isSell(@Param("isSell") Integer isSell, @Param("houseId") Integer houseId);


    //    价格从高到低排列房屋信息
    @Select("SELECT * FROM HOUSE ORDER BY price")
    List<House> allHouseByPriceFromHighToLow();

    //    价格从高到低排列房屋信息
    @Select("SELECT * FROM HOUSE ORDER BY price DESC")
    List<House> allHouseByPriceFromLowToHigh();

    //    查询所有的预约信息
    @Select("select * from Order")
    List<Order> listOrder();

    //    查询用户预约的房屋
//    先查user的username再查房屋的id
    @Select("select * from Order where username = #{username}")
    List<House> findUserOrdeByusernamer();

    //    修改房屋预约状态
    @Update("UPDATE house set isOrder=#{isOrder} where houseId=#{houseId}")
    void updateIsOrder(@Param("isOrder") Integer i, @Param("houseId") Integer houseId);

    //    关键字查询房屋信息
    @Select("select * from house where tittle like " + "'%" + "${keyWord}" + "%' and isOrder=0")
//    @Select("select * from house where tittle like '${keyWord}'")
    List<House> findHouseBykeyWord(@Param("keyWord") String keyWord);

    //    条件查询房屋信息
    @Select("select * from house where subway like '%${subway}%' and price between #{lowPrice} and #{topPrice} " +
            "and room = #{room} and bathroom= #{bathroom} and area between #{lowArea} and #{topArea} and isOrder=0")
    List<House> findHouseByConditions(@Param("lowPrice") Integer lowPrice, @Param("topPrice") Integer topPrice,
                                      @Param("subway") String subway, @Param("bathroom") Integer bathroom,
                                      @Param("room") Integer room, @Param("lowArea") Integer lowArea,
                                      @Param("topArea") Integer topArea);

    /**
     * 通过houseId删除房屋信息
     *
     * @param houseId
     */
    @Delete("delete from house where houseId=#{houseId}")
    void deleteByHouseId(@PathParam("houseId") Integer houseId);

    /**
     * 新增房屋信息
     *
     * @param house
     */
    @Insert("insert into house(price,room,bathroom,subway,area,tittle,img,isOrder,housePhone,name,houseUsername) values (#{price},#{room},#{bathroom},#{subway},#{area},#{tittle},#{img},0,#{housePhone},#{name},#{houseUsername})")
    void insertHouse(House house);

    /**
     * 根据id查询房屋信息
     *
     * @param houseId
     * @return
     */
    @Select("select * from house where houseId=#{houseId}")
    House findHouseByhouseId(Integer houseId);

    @Update("update house set img=#{img}, price=#{price},room=#{room},bathroom=#{bathroom},subway=#{subway},area=#{area},tittle=#{tittle},housePhone=#{housePhone},name=#{name} where houseId=#{houseId}")
    void updateHouseByHouseId(House house);

    //    添加房屋收藏
    @Insert("insert into `order`(username,houseId) values(#{username},#{houseId})")
    void addHouseOrder(@Param("username") String username, @Param("houseId") Integer houseId);

    @Delete("delete from `order` where username=#{username} and houseId=#{houseId}")
    void deleteHouseOrderByUsernameAndHouseId(@Param("username") String username, @Param("houseId") Integer houseId);

    /**
     * 用户是否重复预约房屋
     *
     * @param username
     * @param houseId
     * @return
     */
    int isSameOrder(@Param("username") String username, @Param("houseId") Integer houseId);



}

package com.lau.houseSearchDemo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lau.houseSearchDemo.domain.House;
import com.lau.houseSearchDemo.domain.Msg;
import com.lau.houseSearchDemo.domain.User;
import com.lau.houseSearchDemo.entity.UserPageEntity;
import com.lau.houseSearchDemo.repository.UserRepository;
import com.lau.houseSearchDemo.service.UserService;
import com.lau.houseSearchDemo.utils.MD5Util;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String toLoginPage() {
        return "login";
    }

    //    去注册页面
    @GetMapping("/register")
    public String toRegisterPage() {
        return "register";
    }


    //    用户注册
    @ResponseBody
    @PostMapping("/register")
    public Msg registerUser(User user) {

//        查看是否有重复的用户名
        Long countName = userService.sameUsername(user.getUsername());
//        System.out.println(countName + "   " + user.getUsername());
        if (countName > 0) {
//            0->用户名不重复    1->用户名重复
            return Msg.fail().add("sameName", "1");

        }
//        将密码进行MD5加密
        user.setPassword(MD5Util.encode(user.getPassword()));
        userService.registerUser(user);

        return Msg.success().add("success", "0");
    }


    //    去到修改用户信息页面，并获取用户信息
    //    findUserByusername
    @ResponseBody
    @RequestMapping(value = "/user/getUserDetails", method = RequestMethod.GET)
    public UserPageEntity getUser(@RequestParam("username") String username) {
        System.out.println(username);
        User user = userService.findUserByuserName(username);
        UserPageEntity userPageEntity = new UserPageEntity();
        userPageEntity.setUid(user.getId());
        userPageEntity.setRealName(user.getRealName());
        userPageEntity.setEmail(user.getEmail());
        userPageEntity.setPhone(user.getPhone());
//        model.addAttribute("user", user);
        return userPageEntity;
    }

    //    修改用户信息
    @PostMapping("/user/updateUser")
    public String updateUserByusername(@RequestParam("username") String username,
                                       @RequestParam("realName") String realName,
                                       @RequestParam("email") String email,
                                       @RequestParam("phone") String phone) {
        System.out.println(username);
        User user = userService.findUserByuserName(username);
        user.setRealName(realName);
        user.setEmail(email);
        user.setPhone(phone);
        userService.updateUserByusername(user);
        return "redirect:/index";
    }


    @GetMapping("/user/userDetails")
    public String details() {
        return "/user/userDetails";
    }


    //    跳转到查找页面
    @GetMapping("/searchHouse")
    public String toSeasrchPage() {
        return "searchHouse";
    }

    //    列出所有房屋信息
    @ResponseBody
    @GetMapping("/allHouse")
    public Msg allHouse(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
//        System.out.println(pn);
//        PageHelper.startPage(2, 10);
//        List<House> allHouse = userService.allHouse();
//        PageInfo page = new PageInfo(allHouse, 10);
//        for(House houses:allHouse){
//            System.out.println(houses);
//        }
//        return Msg.success().add("success", page);
        PageHelper.startPage(pn, 10);
        List<House> allHouse = userService.allHouse();
        PageInfo page = new PageInfo(allHouse, 5);
        return Msg.success().add("success", page);
    }

    //    价格从高到低排列房屋信息
    @ResponseBody
    @GetMapping("/allHouseByPriceFromHighToLow")
    public Msg allHouseByPriceFromHighToLow(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<House> allHouse = userService.allHouseByPriceFromHighToLow();
        PageInfo page = new PageInfo(allHouse, 5);
        return Msg.success().add("success", page);
    }

    //    价格从高到低排列房屋信息
    @ResponseBody
    @GetMapping("/allHouseByPriceFromLowToHigh")
    public Msg allHouseByPriceFromLowToHigh(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<House> allHouse = userService.allHouseByPriceFromLowToHigh();
        PageInfo page = new PageInfo(allHouse, 5);
        return Msg.success().add("success", page);
    }


    //    关键字查询房屋信息
    @ResponseBody
    @PostMapping("/searchHouseByKeyWord")
//    public Msg searchHouseByKeyWord(@Param("keyWord") String keyWord, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
    public Msg searchHouseByKeyWord(@RequestParam("keyWord") String keyWord) {
        System.out.println(keyWord);
//        PageHelper.startPage(1, 5);
        List<House> houseList = userService.findHouseBykeyWord(keyWord);
//        for (int i = 0; i < houseList.size(); i++) {
//            System.out.println(houseList.get(i));
//        }
        PageInfo pageInfo = new PageInfo(houseList, 5);
//        System.out.println(pageInfo.getPageNum());
        return Msg.success().add("success", pageInfo);
    }


    @GetMapping("/searchResult")
    public String toSearchResultPage() {
        return "searchResult";
    }

    //    条件查询房屋信息
    @ResponseBody
    @PostMapping("/searchHouse")
    public Msg findHouseByConditions(@RequestParam("lowPrice") Integer lowPrice, @RequestParam("topPrice") Integer topPrice,
                                     @RequestParam("lowArea") Integer lowArea, @RequestParam("topArea") Integer topArea,
                                     @RequestParam("subway") String subway, @RequestParam("room") Integer room,
                                     @RequestParam("bathroom") Integer bathroom
    ) {
//        PageHelper.startPage(pageNum, 5);
        List<House> houseList = userService.findHouseByConditions(lowPrice, topPrice, subway, room, bathroom, lowArea, topArea);
        PageInfo pageInfo = new PageInfo(houseList, 5);
        for (int i = 0; i < houseList.size(); i++) {
            System.out.println(houseList.get(i));
        }
        System.out.println(houseList.size());
        return Msg.success().add("success", pageInfo);
    }

    //跳转到详细信息页面
    @GetMapping("/checkHouseDetailsById/{houseId}")
    public String checkHouseDetailsById(@PathVariable("houseId") Integer houseId, Model model) {
        House house = userService.findHouseByhouseId(houseId);
        model.addAttribute("houseDetails", house);
        System.out.println(house);
        return "/houseDetails";
    }


    /**
     * 添加房屋预约信息
     *
     * @param username
     * @param houseId
     * @return
     */
    @PostMapping("/user/userOrder")
    public Msg addHouseOrder(@RequestParam("username") String username, @RequestParam("houseId")Integer houseId) {
//        if(userService.isSameOrder(username,houseId))
        System.out.println(username+"-------"+houseId);
        userService.addHouseOrder(username, houseId);
        return Msg.success();
    }

    /**
     * 删除房屋预约信息
     *
     * @param username
     * @param houseId
     * @return
     */
    @ResponseBody
    @PostMapping("/user/delUserOrder")
    public Msg deleteHouseOrderByUsernameAndHouseId(@RequestParam("username") String username, @RequestParam("houseId") Integer houseId) {
        System.out.printf(username + "-----" + houseId);
        userService.deleteHouseOrderByUsernameAndHouseId(username, houseId);
        return Msg.success();
    }

    @ResponseBody
    @GetMapping("/user/userHouseOrder")
    public Msg findHouseOrderByUsername(@RequestParam("username") String username, @RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        PageHelper.startPage(pn, 5);
        List<House> houseList = userService.findHouseOrderByUsername(username);
        PageInfo pageInfo = new PageInfo(houseList, 5);
        return Msg.success().add("success", pageInfo);
    }

    @GetMapping("/user/userOrder")
    public String touserOrderPage() {
        return "/user/userOrder";
    }

}

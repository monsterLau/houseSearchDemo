package com.lau.houseSearchDemo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lau.houseSearchDemo.domain.House;
import com.lau.houseSearchDemo.domain.Msg;
import com.lau.houseSearchDemo.service.UserService;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;

    //    去房屋信息列页面
    @GetMapping("/admin/listHouse")
    public String toListHousePage() {
        return "/admin/listHouse";
    }

    //去预约信息列页面
    @GetMapping("/admin/listOrder")
    public String tolistOrderPage() {
        return "/admin/listOrder";
    }

    //    根据houseId删除房屋信息
    @PostMapping("/admin/deleteHouse/{houseId}")
    public String deleteHouse(@PathVariable("houseId") Integer houseId) {
        userService.deleteHouseById(houseId);
        return "redirect:/admin/listHouse";
    }

    //    根据HouseId查询房屋信息（回显）
//    根据houseId查找house信息
//    @GetMapping("/admin/findHouseByHouseId")
//    @ResponseBody
//    public Msg findHouseByHouseId(@RequestParam("houseId") Integer houseId) {
//        House house = userService.findHouseByhouseId(houseId);
//        return Msg.success().add("houseDetails", house);
//    }


    //    根据houseId查找house信息
//    1.根据HouseId查询房屋信息（回显）
    @GetMapping("/admin/updateHouse/{houseId}")
    public String updateHouse(@PathVariable("houseId") Integer houseId, Model model) {

        House house = userService.findHouseByhouseId(houseId);
        model.addAttribute("houseDetails", house);
        return "/admin/updateHouse";
    }

    //    2.post房屋信息完成更新，重定向到首页
    @PostMapping("/admin/updateHouse")
    public String updateHouseSuccess(@RequestParam("houseId") Integer houseId,
                                     @RequestParam("img") MultipartFile newImg,
                                     @RequestParam("tittle") String tittle,
                                     @RequestParam("subway") String subway,
                                     @RequestParam("price") Integer price,
                                     @RequestParam("bathroom") Integer bathroom,
                                     @RequestParam("area") Integer area) {
        House house = userService.findHouseByhouseId(houseId);

        house.setTittle(tittle);
        house.setSubway(subway);
        house.setPrice(price);
        house.setBathroom(bathroom);
        house.setArea(area);

        if (null != newImg && newImg.getSize() > 0) {
//            上传时的文件名
            String imgName = newImg.getOriginalFilename();

            try {
                File file = new File("E:\\Projects\\houseSearchDemo\\target\\classes\\static\\houseImg", imgName);
                System.out.println(file.getPath());

                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();//创建父级文件路径
                    file.createNewFile();//创建文件

                }
                house.setImg("/houseImg/" + imgName);
                newImg.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        userService.updateHouseByHouseId(house);
        System.out.println(house);
        return "redirect:/admin/listHouse";
    }


    //    前往房屋新增页面
    @GetMapping("/admin/addHouse")
    public String toAddHousePage() {
        return "/admin/addHouse";
    }

    //    添加添加房屋信息
    @PostMapping("/admin/addHouse")
    public String addHouse(@RequestParam("img") MultipartFile img,
                           @RequestParam("tittle") String tittle,
                           @RequestParam("subway") String subway,
                           @RequestParam("price") Integer price,
                           @RequestParam("bathroom") Integer bathroom,
                           @RequestParam("area") Integer area,
                           HttpServletRequest httpServletRequest) {
        House house = new House();
        house.setTittle(tittle);
        house.setSubway(subway);
        house.setPrice(price);
        house.setBathroom(bathroom);
        house.setArea(area);

        if (null != img && img.getSize() > 0) {
//            上传时的文件名
            String imgName = img.getOriginalFilename();

            try {
                File file = new File("E:\\Projects\\houseSearchDemo\\target\\classes\\static\\houseImg", imgName);
                System.out.println(file.getPath());

                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();//创建父级文件路径
                    file.createNewFile();//创建文件
                    System.out.println(file.exists());
                }
                house.setImg("/houseImg/" + imgName);
                img.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        userService.insertHouse(house);
        return "index";
    }

    /**
     * 后台列出用户预约信息
     *
     * @param pn
     * @return
     */
    @ResponseBody
    @GetMapping("/admin/adminFindUserOrder")
    public Msg adminFindUserOrder(@RequestParam(defaultValue = "1", value = "pn") Integer pn) {
        PageHelper.startPage(pn, 10);
        List<House> houseList = userService.adminFindUserOrder();
        PageInfo pages = new PageInfo(houseList, 5);
        return Msg.success().add("success", pages);
    }

    /**
     * 处理用户预约信息
     *
     * @param realName
     * @param houseId
     * @return
     */
    @ResponseBody
    @PostMapping("/admin/delUserOrder")
    public Msg deleteHouseOrderByUsernameAndHouseId(@RequestParam("username") String username,
                                                       @RequestParam("houseId") Integer houseId) {
        userService.deleteHouseOrderByUsernameAndHouseId(username, houseId);
        return Msg.success();
    }
}

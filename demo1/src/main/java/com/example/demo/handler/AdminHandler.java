package com.example.demo.handler;

import com.example.demo.constant.CrowdConstant;
import com.example.demo.entity.Admin;
import com.example.demo.service.api.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/do/login/html")
    public String dologin(
            @RequestParam("loginAcct") String loginAcct,
            @RequestParam("userPswd") String userPswd,
            HttpSession session) {

        Admin admin = adminService.getAdminByLoginAcct(loginAcct, userPswd);

        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);

        return "admin-main";
    }

}

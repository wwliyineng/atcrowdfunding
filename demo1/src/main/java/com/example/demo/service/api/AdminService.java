package com.example.demo.service.api;
//import java.util.List;
//
//import com.atguigu.crowd.entity.Admin;
//import com.github.pagehelper.PageInfo;


import com.example.demo.entity.Admin;

import java.util.List;

public interface AdminService {

    void saveAdmin(Admin admin);

    List<Admin> getAll();

    Admin getAdminByLoginAcct(String loginAcct, String userPswd);

    //PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);
}

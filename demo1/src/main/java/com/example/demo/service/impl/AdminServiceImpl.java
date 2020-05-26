package com.example.demo.service.impl;

import com.example.demo.constant.CrowdConstant;
import com.example.demo.entity.Admin;
import com.example.demo.entity.AdminExample;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.service.api.AdminService;
import com.example.demo.util.CrowdUtil;
import org.apache.logging.log4j.LoggingException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

/**/
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insert(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        // 1. 根据登录账号查询Admin对象
        AdminExample adminExample = new AdminExample();

        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andLoginAcctEqualTo(loginAcct);

        //调用AdminMapper的方法执行查询
        List<Admin> list = adminMapper.selectByExample(adminExample);
        // 2. 判断Admin 对象是否为null
        if (list == null || list.size() == 0){
            throw new LoggingException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (list.size() > 1){
            throw  new RuntimeException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        Admin admin = list.get(0);
        // 3. 如果Admin 对象为null侧抛出异常
        if (admin == null){
            throw  new LoggingException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 4.如果Admin对象不为null侧将数据库 密码从Admin对象中取出
        String suerPswdFromDB = admin.getUserPswd();
        // 5.将表单提交的明文密码进行加密
        String userPaswdForm = CrowdUtil.md5(userPswd);

        // 6.对密码进行表决
        if (Objects.equals(suerPswdFromDB,userPaswdForm)){
            // 7.如果比较结果是不一致侧抛出异常
            throw  new LoggingException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        //8.如果一致侧返回Admin对象
        return admin;
    }
}

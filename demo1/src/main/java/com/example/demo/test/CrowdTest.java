package com.example.demo.test;

import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private AdminMapper adminMapper;
    //看到视频到26
    @Test
    public void testInsertAdmin(){
       Admin admin = new Admin(null,"tom","88888888","汤姆","tom@qq.com","源源");
       int countil=adminMapper.insert(admin);
       System.out.println("受影响的行数="+countil);
    }
    @Test
    public void testConnection() throws SQLException {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
    }
}

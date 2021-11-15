package com.robot.study.controller;

import com.robot.study.common.Result;
import com.robot.study.exception.MyException;
import com.robot.study.mapper.UserMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Wuph
 * @Date: create in 2021/11/11/ 9:51
 * @Description
 */
@RestController
public class LoginController {

    private final UserMapper userMapper;

    public LoginController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,@RequestParam("password") String password){
        // 从SecurityUtils里边创建一个 subject
        Subject subject = SecurityUtils.getSubject();
        // 在认证提交前准备 token（令牌）
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 执行认证登陆
        subject.login(token);
        //根据权限，指定返回数据
        String role = userMapper.getRole(username);
        if ("用户".equals(role)) {
            return Result.success("欢迎登陆");
        }
        if ("管理员".equals(role)) {
            return Result.success("欢迎管理员");
        }
        return Result.error(new MyException(999, "权限错误！"));
    }

    @RequestMapping(value = "/notLogin", method = RequestMethod.GET)
    public Result notLogin() {
        return Result.success("您尚未登陆！");
    }

    @RequestMapping(value = "/notRole", method = RequestMethod.GET)
    public Result notRole() {
        return Result.success("您没有权限！");
    }
}

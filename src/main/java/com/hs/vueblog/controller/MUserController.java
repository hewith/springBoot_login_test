package com.hs.vueblog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hs.vueblog.common.Result.Result;
import com.hs.vueblog.common.Result.ResultCode;
import com.hs.vueblog.common.exception.ComException;
import com.hs.vueblog.entity.LoginDto;
import com.hs.vueblog.entity.MUser;
import com.hs.vueblog.service.MUserService;
import com.hs.vueblog.util.JwtUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hs
 * @since 2020-06-18
 */
@RestController
@RequestMapping("/vueblog/user")
public class MUserController {
    @Autowired
    MUserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @ApiOperation(value = "昵称密码登录")
    @PostMapping("login")
    public Result loginUser(@RequestBody LoginDto loginDto) {
        MUser user = userService.login(loginDto);
        return Result.success(user);
    }

    //注册
    @ApiOperation(value = "注册")
    @PostMapping("register")
    public Result register(@RequestBody LoginDto loginDto) {
        userService.register(loginDto);
        return Result.success();
    }

    //根据token获取用户信息
    @ApiOperation(value = "获取用户信息")
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        //调用jwt工具类的方法。根据request对象获取头信息，返回用户id
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        if (userId == null) {
            return Result.fail("请重新登陆");
        }
        //查询数据库根据用户id获取用户信息
        MUser user = userService.getById(userId);
        return Result.success(user);
    }

    //根据用户id获取用户信息
    @PostMapping("getUserInfo/{id}")
    public Result getUserInfoOrder(@PathVariable String id) {
        MUser user = userService.getById(id);
        return Result.success(user);
    }

    // 退出
    @ApiOperation(value = "退出")
    @GetMapping("/logout")
    @RequiresAuthentication
    public Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.success(null);
    }
}


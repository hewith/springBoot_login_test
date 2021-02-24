package com.hs.vueblog.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hs.vueblog.common.Result.Result;
import com.hs.vueblog.entity.LoginDto;
import com.hs.vueblog.entity.MUser;
import com.hs.vueblog.service.MUserService;
import com.hs.vueblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

//@RestController
//@RequestMapping("/vueblog")
public class AccountController {

}

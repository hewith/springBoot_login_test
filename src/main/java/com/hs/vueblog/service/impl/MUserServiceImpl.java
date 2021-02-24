package com.hs.vueblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hs.vueblog.common.Result.Result;
import com.hs.vueblog.common.Result.ResultCode;
import com.hs.vueblog.common.exception.ComException;
import com.hs.vueblog.entity.LoginDto;
import com.hs.vueblog.entity.MUser;
import com.hs.vueblog.mapper.MUserMapper;
import com.hs.vueblog.service.MUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hs.vueblog.util.JwtUtils;
import com.hs.vueblog.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hs
 * @since 2020-06-18
 */
@Service
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public MUser login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ComException(ResultCode.ERROR, "账号或用户名为空");
        }
        QueryWrapper<MUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        MUser user = baseMapper.selectOne(wrapper);
        if (user == null) {
            throw new ComException(ResultCode.ERROR, "用户不存在");
        }
        if (!MD5.encrypt(password).equals(user.getPassword())) {
            throw new ComException(ResultCode.ERROR, "密码错误");
        }
        String token = jwtUtils.generateToken(user.getId());
        user.setToken(token);
        baseMapper.updateById(user);
        return user;
    }

    @Override
    public void register(LoginDto loginDto) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new ComException(ResultCode.ERROR, "账号或用户名不能为空");
        }

        QueryWrapper<MUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        Integer count = baseMapper.selectCount(wrapper);
        if (count > 0) {
            throw new ComException(ResultCode.ERROR, "用户已存在");
        }

        //数据添加数据库中
        MUser user = new MUser();
        user.setUsername(username);
        user.setPassword(MD5.encrypt(password));
        user.setAvatar("https://hs-jxm.oss-cn-beijing.aliyuncs.com/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20200606201224.jpg");
        baseMapper.insert(user);
    }

}

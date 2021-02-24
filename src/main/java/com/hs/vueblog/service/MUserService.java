package com.hs.vueblog.service;

import com.hs.vueblog.common.Result.Result;
import com.hs.vueblog.entity.LoginDto;
import com.hs.vueblog.entity.MUser;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hs
 * @since 2020-06-18
 */
public interface MUserService extends IService<MUser> {
    //登录的方法
    MUser login(LoginDto loginVo);

    //注册
    void register(LoginDto loginDto);
}

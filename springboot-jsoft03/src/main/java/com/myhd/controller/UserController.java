package com.myhd.controller;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.myhd.dto.LoginFormDTO;
import com.myhd.entity.User;
import com.myhd.service.IUserService;
import com.myhd.util.Code;
import com.myhd.util.RegexUtils;
import com.myhd.util.Result;
import com.myhd.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JoneElmo
 * @since 2023-10-22
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/get")
    public Result selectUser(@RequestParam("phone") String phone){
        Result user = userService.selectUserByPhone(phone);
        return user;
    }

    @PostMapping
    public Result login(@RequestBody LoginFormDTO loginFormDTO){
        String phone = loginFormDTO.getPhone();
        /*1, 校验手机号*/
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.failure("手机号格式有误");
        }
        /*符合从redis中获取验证码*/
        String key = "login:code:" + phone;
        String cacheCode = stringRedisTemplate.opsForValue().get(key)
                ;
        if (cacheCode == null) {
            return Result.failure("验证码过期或手机号不一致");
        }

        /*验证码判断*/
        if (!cacheCode.equals(loginFormDTO.getCode())) {
            /*不一致错误*/
            return Result.failure("验证码错误");
        }

        User user = (User) userService.selectUserByPhone(phone).getObject();
        if (user == null) {
            return Result.failure("用户不存在");
        }
        String password = loginFormDTO.getPassword();
        if (StrUtil.isNotBlank(password) && user.getPassword().equals(password)) {
            /*生成token存储并返回给前端*/
            loginFormDTO.setPhone(user.getPhone());
            String token = TokenUtil.sign(loginFormDTO);
            String tokenKey = "login:token:" + token;
            stringRedisTemplate.opsForValue().set(tokenKey, token, Duration.ofDays(2));
            return Result.ok(Code.POST_OK, token, "登录成功");
        } else {
            return Result.failure("用户名或者密码错误");
        }
    }

    @GetMapping("{phone}")
    public Result sendCode(@PathVariable String phone){
        /*1, 校验手机号*/
        if (RegexUtils.isPhoneInvalid(phone)) {
            return Result.failure("手机号格式有误");
        }
        /*3. 符合验证码的生成*/
        String code = RandomUtil.randomNumbers(6);
        log.info("验证码: {}", code);

        /*4. 保存Redis, 5分钟过期*/
        /*5. user:key*/
        /*6. login:code:phone*/
        String key = "login:code:" + phone;

        stringRedisTemplate.opsForValue().set(key, code, Duration.ofMinutes(5));

        /*发送验证码到手机号*/
        /*留着(集成alibaba的短信服务)*/
        System.out.println("已发送");
        return Result.ok("短信已发送");
    }
}




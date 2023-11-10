package com.myhd;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.myhd.dto.LoginFormDTO;
import com.myhd.entity.User;
import com.myhd.mapper.UserMapper;
import com.myhd.util.Code;
import com.myhd.util.RegexUtils;
import com.myhd.util.Result;
import com.myhd.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;

import lombok.val;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;


import java.time.Duration;


@SpringBootTest
@Slf4j
class SpringbootJsoft03ApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    User user;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void testRedis() {
        /* set字符串类型 */
        /* sadd 添加set集合类型 */
//        System.out.println(stringRedisTemplate.opsForValue().append("ename", ",lisi"));
//        user.setId(1L);
//        user.setPhone("1312312");
        val o = stringRedisTemplate.opsForValue().get("测试key");
        System.out.println(o);
    }

    public Result sendCode(String phone) {
        /* 1.校验手机号 */
        if (!RegexUtils.isPhoneInvalid(phone)) {
            return Result.failure(" 手机号格式错误");
        }
        /* 2.符合就生成验证码 */
        String s = RandomUtil.randomNumbers(6);
        log.info("验证码:" + s);
        /* 3.保存到redis中,5分钟过期 */
        /* key设置  login:code:phone  */
        String key = "login:code:" + phone;
        stringRedisTemplate.opsForValue().set(key, s, Duration.ofMinutes(5));
        /* 发送验证码到手机号 */
        System.out.println("已发送");
        return Result.ok("短信已发送");
    }

    /* code:输入的验证码 */
    public Result login(LoginFormDTO loginFormDTO) {
        val phone = loginFormDTO.getPhone();
        /* 1.校验手机号 */
        if (!RegexUtils.isPhoneInvalid(phone)) {
            return Result.failure(" 手机号格式错误");
        }
        /* 手机号正确则从redis中获取验证码 */
        String key = "login:code:" + phone;
        val cacheCode = stringRedisTemplate.opsForValue().get(key);
        if (cacheCode == null) {
            return Result.failure(" 验证码过期或手机号错误");
        }
        /* 验证码判断 */
        if (!cacheCode.equals(loginFormDTO.getCode())) {
            return Result.failure(" 验证码错误");
        }
        val user = userMapper.selectByPhone(phone);
        if (user == null) {
            return Result.failure("用户不存在");
        }
        val password = loginFormDTO.getPassword();
        if (StrUtil.isBlank(password) || !user.getPassword().equals(password)) {
            /* 生成token存储并返回给前端 */
            loginFormDTO.setPhone(user.getPhone());
            val token = TokenUtil.sign(loginFormDTO);
            String tokenKey = "login:token:" + user.getPhone();
            stringRedisTemplate.opsForValue().set(key,tokenKey,Duration.ofDays(2));
            return Result.ok(Code.POST_OK,tokenKey,"登陆成功");
        }else {
            return Result.failure("用户名或密码错误");
        }
    }

    @Test
    public void test() {

    }

}

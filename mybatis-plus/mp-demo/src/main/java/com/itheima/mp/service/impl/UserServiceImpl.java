package com.itheima.mp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.itheima.mp.domain.dto.PageDTO;
import com.itheima.mp.domain.po.Address;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.vo.AddressVO;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.enums.UserStatus;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.query.UserQuery;
import com.itheima.mp.service.IUserService;
import org.springframework.stereotype.Service;

import javax.sound.midi.MidiFileFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Classname UserServiceImpl
 * @Description
 * @Date 2023/12/8 下午11:05
 * @Created by joneelmo
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public void deductBalance(Long id, Integer money) {
        //1. 查询用户
        User user = getById(id);
        //2. 校验用户状态
        if (user == null || user.getStatus() == UserStatus.FROZEN) {
            throw new RuntimeException("用户状态异常");
        }
        //3.校验余额是否充足
        if (user.getBalance() < money) {
            throw new RuntimeException("用户余额不足");
        }
        //4. 扣减余额 update tb_user set balance = balance - ? where ...
        baseMapper.deductBalance(id, money);
    }

    @Override
    public List<User> queryUsers(String name, Integer status, Integer minBalance, Integer maxBalance) {
        return lambdaQuery()
                .like(name != null, User::getUsername, name)
                .eq(status != null, User::getStatus, status)
                .between(minBalance != null || maxBalance != null, User::getBalance, minBalance, maxBalance)
                .list();
    }

    @Override
    public UserVO queryUserAndAddressById(Long id) {
        //1. 查用户
        User user = getById(id);
        if (user == null || user.getStatus() == UserStatus.FROZEN) {
            throw new RuntimeException("用户状态异常");
        }
        //2.查地址
        List<Address> addresses = Db.lambdaQuery(Address.class).eq(Address::getUserId, id).list();
        //3.封装vo
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        if (CollUtil.isNotEmpty(addresses)) {
            userVO.setAddresses(BeanUtil.copyToList(addresses, AddressVO.class));
        }
        return userVO;
    }

    @Override
    public List<UserVO> queryUserAndAddressByIds(List<Long> ids) {
        //1.查询用户
        List<User> users = listByIds(ids);
        if (CollUtil.isEmpty(users)) {
            return Collections.emptyList();
        }
        //2.查询地址
        //2.1获取用户id集合
        List<Long> userIds = users.stream().map(User::getId).collect(Collectors.toList());
        //2.2根据用户id获取地址
        List<Address> addresses = Db.lambdaQuery(Address.class).in(Address::getUserId, userIds).list();
        //2.3转换地址vo
        List<AddressVO> addressVOList = BeanUtil.copyToList(addresses, AddressVO.class);
        Map<Long, List<AddressVO>> addressMap = new HashMap<>(0);
        //2.4梳理地址集合，分类整理，相同用户放在一个集合中
        if (CollUtil.isNotEmpty(addressVOList)) {
            addressMap = addressVOList.stream().collect(Collectors.groupingBy(AddressVO::getUserId));
        }
        //3.转vo返回
        List<UserVO> list = new ArrayList<>(users.size());
        for (User user : users) {
            //3.1转换user的po为vo
            UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
            list.add(userVO);
            //3.2转换地址vo
            userVO.setAddresses(addressMap.get(user.getId()));
        }
        return list;
    }

    @Override
    public PageDTO<UserVO> queryUsersPage(UserQuery query) {
        String name = query.getName();
        Integer status = query.getStatus();
        //1. 构建分页条件
//        //1.1 分页条件
//        Page<User> page = Page.of(query.getPageNo(), query.getPageSize());
//        //1.2 排序条件
//        if (StrUtil.isNotBlank(query.getSortBy())){
//            //不为空，按照排序字段排
//            page.addOrder(new OrderItem(query.getSortBy(), query.getIsAsc()));
//        }else {
//            //为空，默认按照更新时间排序
//            page.addOrder(new OrderItem("update_time",false));
//        }
        Page<User> page = query.toMpPageDefaultSortByUpdateTime();
        //2.1 构建查询条件
        //2.2 分页查询   --使用lambdaQuery一次完成两步操作
        Page<User> p = lambdaQuery()
                .like(name != null, User::getUsername, name)
                .eq(status != null, User::getStatus, status)
                .page(page);
//        //3. 封装vo结果
//        PageDTO<UserVO> dto = new PageDTO<>();
//        // 总条数
//        dto.setTotal(p.getTotal());
//        // 总页数
//        dto.setPages(p.getPages());
//        // 获取分页结果集
//        List<User> records = p.getRecords();
//        if (CollUtil.isEmpty(records)){
//            dto.setList(Collections.emptyList());
//            return dto;
//        }
//        // copy  user  到 userVo
//        // 放置结果集
//        dto.setList(BeanUtil.copyToList(records, UserVO.class));
//        //4. 返回
//        return dto;
        return PageDTO.of(p, user -> {
            //1.copy 基础属性
            UserVO vo = BeanUtil.copyProperties(user, UserVO.class);
            //2.处理特殊数据
            vo.setUsername(vo.getUsername().substring(0,vo.getUsername().length()-2)+"**");
            return vo;
        } );
    }
}

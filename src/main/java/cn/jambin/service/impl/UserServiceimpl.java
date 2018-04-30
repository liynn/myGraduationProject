package cn.jambin.service.impl;

import cn.jambin.base.BaseServiceImpl;
import cn.jambin.base.annotation.BaseService;
import cn.jambin.dto.BookDto;
import cn.jambin.dto.UserBookDto;
import cn.jambin.entity.User;
import cn.jambin.entity.UserExample;
import cn.jambin.mapper.UserMapper;
import cn.jambin.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional
@BaseService
public class UserServiceimpl extends BaseServiceImpl<UserMapper, User, UserExample> implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public List<UserBookDto> getUserReadedBook(long userId){
        return userMapper.getUserReadedBook(userId);
    }

    @Override
    public List<UserBookDto> getAlsRecdByUserId(long userId){
        return userMapper.getAlsRecdByUserId(userId);
    }

    @Override
    public List<UserBookDto> getItemCFRecdByUserId(long userId){
        return userMapper.getItemCFRecdByUserId(userId);
    }

    @Override
    public List<UserBookDto> getUserCFRecdByUserId(long userId){
        return userMapper.getUserCFRecdByUserId(userId);
    }

}

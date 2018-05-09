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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @Override
    public List<UserBookDto> recommend(long userId){
        List<UserBookDto> alsList = userMapper.getAlsRecdByUserId(userId);
        List<UserBookDto> userCFList = userMapper.getUserCFRecdByUserId(userId);
        List<UserBookDto> itemCFList = userMapper.getItemCFRecdByUserId(userId);
        List<UserBookDto> list = new ArrayList<>();
        Set<Long> set = new HashSet<>();

        int alsSize = alsList.size();
        int userCFSize = userCFList.size();
        int itemCFSize = itemCFList.size();
        for (int a=0; a<10 && set.size()<10; a++){

            if (a<itemCFSize && set.add(itemCFList.get(a).getBookId()))
                list.add(itemCFList.get(a));

            if (set.size()<10 && a<userCFSize && set.add(userCFList.get(a).getBookId()))
                list.add(userCFList.get(a));

            if (set.size()<10 && a<alsSize && set.add(alsList.get(a).getBookId()))
                list.add(alsList.get(a));
        }
        return list;
    }

}

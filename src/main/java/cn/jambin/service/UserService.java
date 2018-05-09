package cn.jambin.service;

import cn.jambin.base.BaseService;
import cn.jambin.dto.BookDto;
import cn.jambin.dto.UserBookDto;
import cn.jambin.entity.User;
import cn.jambin.entity.UserExample;

import java.util.List;

public interface UserService extends BaseService<User,UserExample> {

    List<UserBookDto> getUserReadedBook(long userId);

    List<UserBookDto> getAlsRecdByUserId(long userId);

    List<UserBookDto> getItemCFRecdByUserId(long userId);

    List<UserBookDto> getUserCFRecdByUserId(long userId);

    List<UserBookDto> recommend(long userId);

}

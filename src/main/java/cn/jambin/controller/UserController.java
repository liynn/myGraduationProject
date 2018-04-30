package cn.jambin.controller;

import cn.jambin.base.BaseController;
import cn.jambin.base.BaseResult;
import cn.jambin.base.Constant;
import cn.jambin.entity.User;
import cn.jambin.entity.UserExample;
import cn.jambin.service.UserService;
import cn.jambin.util.MD5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;


    @RequestMapping(value = "/login")
    @ResponseBody
    public Object login(String userName, String password, HttpServletRequest request){
        _log.info("用户登录:{}，密码：{}", userName, password);
        if(StringUtils.isEmpty(userName)||StringUtils.isEmpty(password)){
            return BaseResult.simpleErrorResult(0,"账号密码不能为空");
        }
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserNameEqualTo(userName);
        User user = userService.selectFirstByExample(userExample);
        if (user==null)
            return BaseResult.simpleErrorResult(0,"该用户名未注册!");
        else if (!StringUtils.equals(MD5Util.MD5(password), user.getPassword()))
            return BaseResult.simpleErrorResult(0,"密码错误!");
        request.getSession().setAttribute(Constant.SESSION_USER, user);
        return BaseResult.simpleSuccessResult(null);
    }

    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public Object regist(User user, HttpServletRequest request){
        if(StringUtils.isEmpty(user.getUserName())||StringUtils.isEmpty(user.getPassword())||StringUtils.isEmpty(user.getEmail())){
            return BaseResult.simpleErrorResult(0,"账号密码邮箱不能为空");
        }
        UserExample userExample = new UserExample();
        userExample.or().andUserNameEqualTo(user.getUserName());
        userExample.or().andEmailEqualTo(user.getEmail());

        User user_2 = userService.selectFirstByExample(userExample);
        if (user_2!=null){
            if (StringUtils.equals(user.getUserName(), user_2.getUserName()))
                return BaseResult.simpleErrorResult(0,"用户名已存在");
            else if (StringUtils.equals(user.getEmail(), user_2.getEmail()))
                return BaseResult.simpleErrorResult(0,"邮箱已被注册");
        }
        user.setPassword(MD5Util.MD5(user.getPassword()));
        int count = userService.insert(user);
        if (count>0){
            request.getSession().setAttribute(Constant.SESSION_USER, user);
            return BaseResult.simpleSuccessResult;
        }else {
            _log.warn("添加用户失败，用户名{}，邮箱：{}", user.getUserName(), user.getEmail());
            return BaseResult.simpleErrorResult(0,"注册失败");
        }
    }

    @RequestMapping(value = "/{userId}")
    public ModelAndView page(HttpServletRequest request){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/pages/user.html");
        return mav;
    }

    @RequestMapping(value = "/{userId}/readed")
    @ResponseBody
    public Object readed(@PathVariable(value = "userId") long userId, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("list", userService.getUserReadedBook(userId));
        return map;
    }

    @RequestMapping(value = "/{userId}/als")
    @ResponseBody
    public Object als(@PathVariable(value = "userId") long userId, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("list", userService.getAlsRecdByUserId(userId));
        return map;
    }

    @RequestMapping(value = "/{userId}/itemCF")
    @ResponseBody
    public Object itemCF(@PathVariable(value = "userId") long userId, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("list", userService.getItemCFRecdByUserId(userId));
        return map;
    }

    @RequestMapping(value = "/{userId}/userCF")
    @ResponseBody
    public Object userCF(@PathVariable(value = "userId") long userId, HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("list", userService.getUserCFRecdByUserId(userId));
        return map;
    }

}

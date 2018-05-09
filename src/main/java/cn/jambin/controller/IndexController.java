package cn.jambin.controller;

import cn.jambin.base.BaseController;
import cn.jambin.base.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/")
public class IndexController extends BaseController {




    @RequestMapping(value = "login", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login/login.html");
        return mav;
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView register() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login/register.html");
        return mav;
    }

    @RequestMapping(value = "logout")
    @ResponseBody
    public ModelAndView logout(HttpServletRequest request){
        request.getSession().removeAttribute(Constant.SESSION_USER);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/login/login.html");
        return mav;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/pages/index.html");
        return mav;
    }
    @RequestMapping(value = "search", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView search() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/pages/page.html");
        return mav;
    }

    /**
     * 获取标签
     */
    @RequestMapping(value = "/tag/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getTagPage(@PathVariable("name") String name) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/pages/tagPage.html");
        return mav;
    }

    /**
     * 获取标签
     */
    @RequestMapping(value = "/tag", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getTagIndexPage(String str) {
        ModelAndView mav = new ModelAndView();
        if (!StringUtils.isBlank(str))
            mav.setViewName("redirect:/tag/"+str);
        else
            mav.setViewName("/pages/tagPage.html");
        return mav;
    }

}

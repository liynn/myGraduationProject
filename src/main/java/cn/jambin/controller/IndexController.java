package cn.jambin.controller;

import cn.jambin.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


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
        mav.setViewName("/pages/page.html");
        return mav;
    }

}

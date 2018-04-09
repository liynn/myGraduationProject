package cn.jambin.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.HashMap;

public class BaseController {

    private final static Logger _log = LoggerFactory.getLogger(BaseController.class);

    /**
     * 统一异常处理
     * @param request
     * @param response
     * @param exception
     */
    @ExceptionHandler
    public Object exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception exception) {
        _log.error("统一异常处理：", exception);
        request.setAttribute("ex", exception);
        System.out.println(request.getMethod());
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            request.setAttribute("requestHeader", "ajax");
        }
        if ("POST".equals(request.getMethod())){
            ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
            mav.addObject("success", false);
            mav.addObject("code", 0);
            mav.addObject("info", "failed");
            //由于前期写的
            HashMap<String,String> map =  new HashMap<String,String>();
            map.put("errorMsg","请求失败");
            mav.addObject("data", map);
            return mav;
        }
        return "/error.jsp";
    }

    /**
     * 返回jsp视图
     * @param path
     * @return
     */
    public static String jsp(String path) {
        return path.concat(".jsp");
    }


}

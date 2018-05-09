package cn.jambin.controller;

import cn.jambin.base.BaseController;
import cn.jambin.base.BaseResult;
import cn.jambin.base.Constant;
import cn.jambin.dto.BookDto;
import cn.jambin.dto.UserRatingVo;
import cn.jambin.entity.*;
import cn.jambin.service.BookService;
import cn.jambin.service.RatingService;
import com.github.pagehelper.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/book")
public class BookController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(BookController.class);

    @Autowired
    BookService bookService;

    @Autowired
    RatingService ratingService;

    /**
     * 获取评分最高的100本书
     */
    @RequestMapping(value = "/topRead", method = RequestMethod.GET)
    public ModelAndView topReadBookPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/pages/page.html");
        return mav;
    }

    /**
     * 获取被阅读数最多的100本书
     */
    @RequestMapping(value = "/topRead", method = RequestMethod.POST)
    @ResponseBody
    public Object getTopReadBook(
            @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize) {
        List<BookDto> rows = bookService.getTopReadBook(pageNum, pageSize);
        int pageCount = ((Page)rows).getPages();
        Map<String,Object> map = new HashMap<>();
        map.put("pageCount", pageCount);
        map.put("list", rows);
        return map;
    }

    /**
     * 获取评分最高的100本书
     */
    @RequestMapping(value = "/topRating", method = RequestMethod.GET)
    public ModelAndView topRatingBookPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/pages/page.html");
        return mav;
    }

    /**
     * 获取评分最高的100本书
     */
    @RequestMapping(value = "/topRating", method = RequestMethod.POST)
    @ResponseBody
    public Object getTopRatingBook(
            @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize) {
        List<BookDto> rows = bookService.getTopRatingBook(pageNum, pageSize);
        int pageCount = ((Page)rows).getPages();
        Map<String,Object> map = new HashMap<>();
        map.put("pageCount", pageCount);
        map.put("list", rows);
        return map;
    }

    /**
     * 获取书籍信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object getBookInfo(@PathVariable("id") Long id) {
        BookWithBLOBs book = bookService.getBookById(id);
        if (book == null)
            return BaseResult.simpleErrorResult(0, "书籍不存在");
        return book;
    }

    /**
     * 获取书籍信息
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getBookPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/pages/bookInfo.html");
        return mav;
    }

    /**
     * 获取评分最高的100本书
     */
    @RequestMapping(value = "/search", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object searchByBookName(
            @RequestParam(value = "str") String str,
            @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,
            @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize) throws Exception{

        str = new String(str.getBytes("iso8859-1"),"utf-8");
        List<BookDto> rows = bookService.searchByBookName(str, pageNum, pageSize);
        int pageCount = ((Page)rows).getPages();

        Map<String,Object> map = new HashMap<>();
        map.put("pageCount", pageCount);
        map.put("list", rows);
        map.put("str", str);
        return map;
    }

    @RequestMapping(value = "/getUserRatingByBookId", method = RequestMethod.POST)
    @ResponseBody
    public Object getUserRatingByBookId( @RequestParam(value = "bookId") long bookId,
                                         @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,
                                         @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize){

        List<UserRatingVo> rows = bookService.getUserRatingByBookId(bookId, pageNum, pageSize);
        int pageCount = ((Page)rows).getPages();

        Map<String,Object> map = new HashMap<>();
        map.put("pageCount", pageCount);
        map.put("rowCount", ((Page)rows).getTotal());
        map.put("list", rows);
        return map;
    }

    /**
     * 获取书籍信息
     */
    @RequestMapping(value = "/tag/{str}", method = RequestMethod.POST)
    @ResponseBody
    public Object getTagList(@PathVariable(value = "str") String str,
                             @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,
                             @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize) throws Exception{
        List<BookDto> rows = bookService.getBookByTag(str, pageNum, pageSize);
        int pageCount = ((Page)rows).getPages();
        Map<String,Object> map = new HashMap<>();
        map.put("pageCount", pageCount);
        map.put("list", rows);
        map.put("str", str);
        return map;
    }

    /**
     * 获取相似图书s
     */
    @RequestMapping(value = "/similarity/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Object getSimilarityBookByBookId(@PathVariable(value = "id") long str) throws Exception{
        List<BookDto> rows = bookService.getSimilarityBookByBookId(str);
        Map<String,Object> map = new HashMap<>();
        map.put("list", rows);
        return map;
    }

}

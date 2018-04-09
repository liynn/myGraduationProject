package cn.jambin.service.impl;

import cn.jambin.base.BaseServiceImpl;
import cn.jambin.base.annotation.BaseService;
import cn.jambin.dto.BookDto;
import cn.jambin.dto.UserRatingVo;
import cn.jambin.entity.Book;
import cn.jambin.entity.BookExample;
import cn.jambin.mapper.BookMapper;
import cn.jambin.service.BookService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@BaseService
public class BookServiceimpl extends BaseServiceImpl<BookMapper, Book, BookExample> implements BookService{

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<BookDto> getTopReadBook(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        return bookMapper.getTopReadBook();
    }

    @Override
    public List<BookDto> getTopRatingBook(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize, true);
        return bookMapper.getTopRatingBook();
    }

    @Override
    public BookDto getBookById(Long id) {
        BookDto bookDto = bookMapper.getBookById(id);
        if (bookDto != null)
            bookDto.initData();
        return bookDto;
    }

    @Override
    public List<BookDto> searchByBookName(String name, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize, true);
        List<BookDto> list = bookMapper.searchByBookName(name);
        for (BookDto dto: list)
            dto.initData();
        return list;
    }

    @Override
    public List<UserRatingVo> getUserRatingByBookId(long bookId, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize, true);
        return bookMapper.getUserRatingByBookId(bookId);
    }

    @Override
    public List<BookDto> getBookByTag(String name, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum, pageSize, true);
        return bookMapper.getBookByTag(name);
    }


}

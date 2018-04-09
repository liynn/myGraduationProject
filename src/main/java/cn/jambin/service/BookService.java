package cn.jambin.service;

import cn.jambin.base.BaseService;
import cn.jambin.dto.BookDto;
import cn.jambin.dto.UserRatingVo;
import cn.jambin.entity.Book;
import cn.jambin.entity.BookExample;

import java.util.List;

public interface BookService extends BaseService<Book,BookExample> {

    List<BookDto> getTopReadBook(Integer pageNum, Integer pageSize);

    List<BookDto> getTopRatingBook(Integer pageNum, Integer pageSize);

    BookDto getBookById(Long id);

    List<BookDto> searchByBookName(String name, Integer pageNum, Integer pageSize);

    List<UserRatingVo> getUserRatingByBookId(long bookId, Integer pageNum, Integer pageSize);

    List<BookDto> getBookByTag(String name, Integer pageNum, Integer pageSize);


}

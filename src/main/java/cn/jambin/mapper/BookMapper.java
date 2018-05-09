package cn.jambin.mapper;

import cn.jambin.dto.BookDto;
import cn.jambin.dto.UserRatingVo;
import cn.jambin.entity.Book;
import cn.jambin.entity.BookExample;
import cn.jambin.entity.BookWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookMapper {
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Long bookId);

    int insert(BookWithBLOBs record);

    int insertSelective(BookWithBLOBs record);

    List<BookWithBLOBs> selectByExampleWithBLOBs(BookExample example);

    List<Book> selectByExample(BookExample example);

    BookWithBLOBs selectByPrimaryKey(Long bookId);

    int updateByExampleSelective(@Param("record") BookWithBLOBs record, @Param("example") BookExample example);

    int updateByExampleWithBLOBs(@Param("record") BookWithBLOBs record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(BookWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BookWithBLOBs record);

    int updateByPrimaryKey(Book record);

    List<BookDto> getTopReadBook();

    List<BookDto> getTopRatingBook();

    BookDto getBookById(Long id);

    List<BookDto> searchByBookName(String name);

    List<UserRatingVo> getUserRatingByBookId(long bookId);

    List<BookDto> getBookByTag(String name);

    List<BookDto> getSimilarityBookByBookId(long bookId);

}
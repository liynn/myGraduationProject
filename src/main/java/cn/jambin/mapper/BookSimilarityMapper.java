package cn.jambin.mapper;

import cn.jambin.entity.BookSimilarity;
import cn.jambin.entity.BookSimilarityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookSimilarityMapper {
    int countByExample(BookSimilarityExample example);

    int deleteByExample(BookSimilarityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookSimilarity record);

    int insertSelective(BookSimilarity record);

    List<BookSimilarity> selectByExample(BookSimilarityExample example);

    BookSimilarity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookSimilarity record, @Param("example") BookSimilarityExample example);

    int updateByExample(@Param("record") BookSimilarity record, @Param("example") BookSimilarityExample example);

    int updateByPrimaryKeySelective(BookSimilarity record);

    int updateByPrimaryKey(BookSimilarity record);
}
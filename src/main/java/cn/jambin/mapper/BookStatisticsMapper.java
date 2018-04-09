package cn.jambin.mapper;

import cn.jambin.entity.BookStatistics;
import cn.jambin.entity.BookStatisticsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookStatisticsMapper {
    int countByExample(BookStatisticsExample example);

    int deleteByExample(BookStatisticsExample example);

    int deleteByPrimaryKey(Integer bookId);

    int insert(BookStatistics record);

    int insertSelective(BookStatistics record);

    List<BookStatistics> selectByExample(BookStatisticsExample example);

    BookStatistics selectByPrimaryKey(Integer bookId);

    int updateByExampleSelective(@Param("record") BookStatistics record, @Param("example") BookStatisticsExample example);

    int updateByExample(@Param("record") BookStatistics record, @Param("example") BookStatisticsExample example);

    int updateByPrimaryKeySelective(BookStatistics record);

    int updateByPrimaryKey(BookStatistics record);
}
package cn.jambin.mapper;

import cn.jambin.entity.BookTagExample;
import cn.jambin.entity.BookTagKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BookTagMapper {
    int countByExample(BookTagExample example);

    int deleteByExample(BookTagExample example);

    int deleteByPrimaryKey(BookTagKey key);

    int insert(BookTagKey record);

    int insertSelective(BookTagKey record);

    List<BookTagKey> selectByExample(BookTagExample example);

    int updateByExampleSelective(@Param("record") BookTagKey record, @Param("example") BookTagExample example);

    int updateByExample(@Param("record") BookTagKey record, @Param("example") BookTagExample example);
}
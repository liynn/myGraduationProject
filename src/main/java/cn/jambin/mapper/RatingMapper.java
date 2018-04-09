package cn.jambin.mapper;

import cn.jambin.entity.Rating;
import cn.jambin.entity.RatingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RatingMapper {
    int countByExample(RatingExample example);

    int deleteByExample(RatingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Rating record);

    int insertSelective(Rating record);

    List<Rating> selectByExample(RatingExample example);

    Rating selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Rating record, @Param("example") RatingExample example);

    int updateByExample(@Param("record") Rating record, @Param("example") RatingExample example);

    int updateByPrimaryKeySelective(Rating record);

    int updateByPrimaryKey(Rating record);
}
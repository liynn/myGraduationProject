package cn.jambin.mapper;

import cn.jambin.entity.AlsRating;
import cn.jambin.entity.AlsRatingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AlsRatingMapper {
    int countByExample(AlsRatingExample example);

    int deleteByExample(AlsRatingExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AlsRating record);

    int insertSelective(AlsRating record);

    List<AlsRating> selectByExample(AlsRatingExample example);

    AlsRating selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AlsRating record, @Param("example") AlsRatingExample example);

    int updateByExample(@Param("record") AlsRating record, @Param("example") AlsRatingExample example);

    int updateByPrimaryKeySelective(AlsRating record);

    int updateByPrimaryKey(AlsRating record);
}
package cn.jambin.mapper;

import cn.jambin.entity.Pred;
import cn.jambin.entity.PredExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PredMapper {
    int countByExample(PredExample example);

    int deleteByExample(PredExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Pred record);

    int insertSelective(Pred record);

    List<Pred> selectByExample(PredExample example);

    Pred selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Pred record, @Param("example") PredExample example);

    int updateByExample(@Param("record") Pred record, @Param("example") PredExample example);

    int updateByPrimaryKeySelective(Pred record);

    int updateByPrimaryKey(Pred record);
}
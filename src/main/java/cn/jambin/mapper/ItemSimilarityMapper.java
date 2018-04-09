package cn.jambin.mapper;

import cn.jambin.entity.ItemSimilarity;
import cn.jambin.entity.ItemSimilarityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemSimilarityMapper {
    int countByExample(ItemSimilarityExample example);

    int deleteByExample(ItemSimilarityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemSimilarity record);

    int insertSelective(ItemSimilarity record);

    List<ItemSimilarity> selectByExample(ItemSimilarityExample example);

    ItemSimilarity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemSimilarity record, @Param("example") ItemSimilarityExample example);

    int updateByExample(@Param("record") ItemSimilarity record, @Param("example") ItemSimilarityExample example);

    int updateByPrimaryKeySelective(ItemSimilarity record);

    int updateByPrimaryKey(ItemSimilarity record);
}
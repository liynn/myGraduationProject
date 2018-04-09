package cn.jambin.mapper;

import cn.jambin.entity.ProxyAdd;
import cn.jambin.entity.ProxyAddExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProxyAddMapper {
    int countByExample(ProxyAddExample example);

    int deleteByExample(ProxyAddExample example);

    int deleteByPrimaryKey(String host);

    int insert(ProxyAdd record);

    int insertSelective(ProxyAdd record);

    List<ProxyAdd> selectByExampleWithBLOBs(ProxyAddExample example);

    List<ProxyAdd> selectByExample(ProxyAddExample example);

    ProxyAdd selectByPrimaryKey(String host);

    int updateByExampleSelective(@Param("record") ProxyAdd record, @Param("example") ProxyAddExample example);

    int updateByExampleWithBLOBs(@Param("record") ProxyAdd record, @Param("example") ProxyAddExample example);

    int updateByExample(@Param("record") ProxyAdd record, @Param("example") ProxyAddExample example);

    int updateByPrimaryKeySelective(ProxyAdd record);

    int updateByPrimaryKeyWithBLOBs(ProxyAdd record);

    int updateByPrimaryKey(ProxyAdd record);
}
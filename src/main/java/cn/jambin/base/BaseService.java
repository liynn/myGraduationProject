package cn.jambin.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BaseService接口
 * Created by jambin on 2017/12/08
 */
public interface BaseService<Record, Example> {

	int countByExample(Example example);

	int deleteByExample(Example example);

	int deleteByPrimaryKey(Integer id);

	int insert(Record record);

	int insertSelective(Record record);

	List<Record> selectByExampleWithBLOBs(Example example);

	List<Record> selectByExample(Example example);

	List<Record> selectByExampleWithBLOBsForStartPage(Example example, Integer pageNum, Integer pageSize);

	List<Record> selectByExampleForStartPage(Example example, Integer pageNum, Integer pageSize);

	List<Record> selectByExampleWithBLOBsForOffsetPage(Example example, Integer offset, Integer limit);

	List<Record> selectByExampleForOffsetPage(Example example, Integer offset, Integer limit);

	Record selectFirstByExample(Example example);

	Record selectFirstByExampleWithBLOBs(Example example);

	Record selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Record record, @Param("example") Example example);

	int updateByExampleWithBLOBs(@Param("record") Record record, @Param("example") Example example);

	int updateByExample(@Param("record") Record record, @Param("example") Example example);

	int updateByPrimaryKeySelective(Record record);

	int updateByPrimaryKeyWithBLOBs(Record record);

	int updateByPrimaryKey(Record record);

	/**
	 *
	 * @param ids
	 * @return 删除的的记录个数
	 */
	int deleteByPrimaryKeys(String ids);

	/**
	 * 锁定记录（设置state为0）
	 * @param ids
	 * @param entity_class 实体类Class
	 * @param setId_Method 由于每个实体类的setId方法名不一样，所以通过灵活传入的方法
	 * @param isIdInt 由于有个别的id主键是Long类型。故true代表Integer false代表Long类型
	 * @param isStateByte 由于一些旧的表state是Integer类型。 true代表为Byte类型 为false则代表是Integer
	 * @return 锁定的记录个数
	 */
	int lockByPrimaryKeys(String ids, Class entity_class, String setId_Method, boolean isIdInt, boolean isStateByte);
	int lockByPrimaryKeys(String ids, Class entity_class, String setId_Method);
	void initMapper();

}
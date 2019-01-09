package dao.hainan;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.hainan.DistrictConfig;
import pojo.hainan.DistrictConfigExample;

public interface DistrictConfigMapper {
    int countByExample(DistrictConfigExample example);

    int deleteByExample(DistrictConfigExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DistrictConfig record);

    int insertSelective(DistrictConfig record);

    List<DistrictConfig> selectByExample(DistrictConfigExample example);

    DistrictConfig selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DistrictConfig record, @Param("example") DistrictConfigExample example);

    int updateByExample(@Param("record") DistrictConfig record, @Param("example") DistrictConfigExample example);

    int updateByPrimaryKeySelective(DistrictConfig record);

    int updateByPrimaryKey(DistrictConfig record);
}
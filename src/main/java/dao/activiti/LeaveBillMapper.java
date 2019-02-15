package dao.activiti;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pojo.activiti.LeaveBill;
import pojo.activiti.LeaveBillExample;

public interface LeaveBillMapper {
    long countByExample(LeaveBillExample example);

    int deleteByExample(LeaveBillExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LeaveBill record);

    int insertSelective(LeaveBill record);

    List<LeaveBill> selectByExampleWithBLOBs(LeaveBillExample example);

    List<LeaveBill> selectByExample(LeaveBillExample example);

    LeaveBill selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LeaveBill record, @Param("example") LeaveBillExample example);

    int updateByExampleWithBLOBs(@Param("record") LeaveBill record, @Param("example") LeaveBillExample example);

    int updateByExample(@Param("record") LeaveBill record, @Param("example") LeaveBillExample example);

    int updateByPrimaryKeySelective(LeaveBill record);

    int updateByPrimaryKeyWithBLOBs(LeaveBill record);

    int updateByPrimaryKey(LeaveBill record);
}
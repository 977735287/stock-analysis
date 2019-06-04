package per.san.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import per.san.example.domain.UpDownPercent;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * description: 
 *
 * @author sanchar
 * @date 2019-03-31 10:13
 * lastUpdateBy: sanchar
 * lastUpdateDate: 2019-03-31 10:13
 */
public interface UpDownPercentMapper extends BaseMapper<UpDownPercent> {

    /**
     * description: 批量删除
     * @param ids 删除数据的id数组
     * @return 删除成功的条数
     */
    Integer deleteBatch(@Param("ids") List<Long> ids);

    /**
     * description: 当天数据统计
     * @return 条数
     */
    @Select("select count(id) from up_down_percent where to_days(create_date) = to_days(now())")
    Integer currentCount();

    /**
     * description: 查询某一天涨停的数据
     * @return 数据
     */
    List<UpDownPercent> getHistoryUpByDate(String dateStr);

}

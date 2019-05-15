package per.san.stock.mapper;

import org.apache.ibatis.annotations.Param;
import per.san.stock.domain.OptionalStock;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * description: 
 *
 * @author sanchar
 * @date 2019-05-13 09:03
 * lastUpdateBy: sanchar
 * lastUpdateDate: 2019-05-13 09:03
 */
public interface OptionalStockMapper extends BaseMapper<OptionalStock> {

    /**
     * description: 批量删除
     * @param ids 删除数据的id数组
     * @return 删除成功的条数
     */
    Integer deleteBatch(@Param("ids") List<Long> ids);

    /**
     * description: 批量新增
     * @param optionalStocks 新增的信息
     * @return 新增成功的条数
     */
    Integer insertList(@Param("optionalStocks") List<OptionalStock> optionalStocks);

    /**
     * description: 批量新增
     * @param codes 新增的信息
     * @return 新增成功的条数
     */
    Integer insertCodeList(@Param("codes") List<String> codes);

}

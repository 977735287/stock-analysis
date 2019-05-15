package per.san.stock.service;

import com.github.pagehelper.PageInfo;
import per.san.common.utils.page.PageRequest;
import per.san.stock.domain.OptionalStock;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * description: 
 *
 * @author sanchar
 * @date 2019-05-13 09:03
 * lastUpdateBy: sanchar
 * lastUpdateDate: 2019-05-13 09:03
 */
public interface IOptionalStockService {

    /**
     * description: 新增
     * @param optionalStock 新增的信息
     * @return 新增成功的条数
     */
    Integer add(OptionalStock optionalStock);

    /**
     * description: 批量新增
     * @param optionalStocks 新增的信息
     * @return 新增成功的条数
     */
    Integer addList(List<OptionalStock> optionalStocks);

    /**
     * description: 批量新增
     * @param codes 新增的信息
     * @return 新增成功的条数
     */
    Integer addCodeList(List<String> codes);

    /**
     * description: 根据主键id删除
     * @param id 删除数据的id
     * @return 删除成功的条数
     */
    Integer deleteByPrimaryKey(Long id);

    /**
     * description: 批量删除
     * @param ids 删除数据的id数组
     * @return 删除成功的条数
     */
    Integer deleteBatch(List<Long> ids);

    /**
    * description: 更新
    * @param optionalStock 更新的信息
    * @return 更新成功的条数
    */
    Integer update(OptionalStock optionalStock);

    /**
    * description: 分页查询
    * @param pageRequest 分页查询参数
    * @param optionalStock 分页查询条件
    * @return 分页数据
    */
    PageInfo<OptionalStock> pageQuery(PageRequest pageRequest, OptionalStock optionalStock);

    /**
    * description: 查询List
    * @param optionalStock 查询条件
    * @return 数据List
    */
    List<OptionalStock> queryList(OptionalStock optionalStock);

    /**
    * description: 根据ID查询
    * @param id 查询数据的ID
    * @return 数据对象
    */
    OptionalStock queryById(Long id);

    /**
    * description:
    * @param response
    * @param start
    * @param end
    * @return 数据对象
    */
    void downloadHistoryInfo(HttpServletResponse response, String start, String end) throws IOException;

}

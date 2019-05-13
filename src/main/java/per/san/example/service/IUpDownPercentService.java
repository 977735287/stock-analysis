package per.san.example.service;

import com.github.pagehelper.PageInfo;
import per.san.common.utils.page.PageRequest;
import per.san.example.domain.UpDownPercent;

import java.util.List;

/**
 * description: 
 *
 * @author sanchar
 * @date 2019-03-31 10:13
 * lastUpdateBy: sanchar
 * lastUpdateDate: 2019-03-31 10:13
 */
public interface IUpDownPercentService {

    /**
     * description: 新增
     * @param upDownPercent 新增的信息
     * @return 新增成功的条数
     */
    Integer add(UpDownPercent upDownPercent);

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
    * @param upDownPercent 更新的信息
    * @return 更新成功的条数
    */
    Integer update(UpDownPercent upDownPercent);

    /**
    * description: 分页查询
    * @param pageRequest 分页查询参数
    * @param upDownPercent 分页查询条件
    * @return 分页数据
    */
    PageInfo<UpDownPercent> pageQuery(PageRequest pageRequest, UpDownPercent upDownPercent);

    /**
    * description: 查询List
    * @param upDownPercent 查询条件
    * @return 数据List
    */
    List<UpDownPercent> queryList(UpDownPercent upDownPercent);

    /**
    * description: 根据ID查询
    * @param id 查询数据的ID
    * @return 数据对象
    */
    UpDownPercent queryById(Long id);

    /**
     * description: 每天定时更新数据
     * @return Integer
     */
    Integer infoUpdate();
}

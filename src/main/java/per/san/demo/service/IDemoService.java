package per.san.demo.service;

import com.github.pagehelper.PageInfo;
import per.san.common.utils.page.PageRequest;
import per.san.demo.domain.Demo;

import java.util.List;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 12/13/2018 13:00
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/13/2018
 */
public interface IDemoService {

    /**
     * description: 新增
     * @param demo 新增的信息
     * @return 新增成功的条数
     */
    Integer add(Demo demo);

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
     * @param demo 更新的信息
     * @return 更新成功的条数
     */
    Integer update(Demo demo);

    /**
     * description: 分页查询
     * @param pageRequest 分页查询参数
     * @param demo 分页查询条件
     * @return 分页数据
     */
    PageInfo<Demo> pageQuery(PageRequest pageRequest, Demo demo);

    /**
     * description: 查询List
     * @param demo 查询条件
     * @return 数据List
     */
    List<Demo> queryList(Demo demo);

    /**
     * description: 根据ID查询
     * @param id 查询数据的ID
     * @return 数据List
     */
    Demo queryById(Long id);

}

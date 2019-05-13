package ${packageName}.service;

import com.github.pagehelper.PageInfo;
import per.san.common.utils.page.PageRequest;
import ${packageName}.domain.${table.className};

import java.util.List;

/**
 * description: ${table.comments}
 *
 * @author ${author}
 * @date ${date?string('yyyy-MM-dd hh:mm')}
 * lastUpdateBy: ${author}
 * lastUpdateDate: ${date?string('yyyy-MM-dd hh:mm')}
 */
public interface I${table.className}Service {

    /**
     * description: 新增
     * @param ${table.className?uncap_first} 新增的信息
     * @return 新增成功的条数
     */
    Integer add(${table.className} ${table.className?uncap_first});

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
    * @param ${table.className?uncap_first} 更新的信息
    * @return 更新成功的条数
    */
    Integer update(${table.className} ${table.className?uncap_first});

    /**
    * description: 分页查询
    * @param pageRequest 分页查询参数
    * @param ${table.className?uncap_first} 分页查询条件
    * @return 分页数据
    */
    PageInfo<${table.className}> pageQuery(PageRequest pageRequest, ${table.className} ${table.className?uncap_first});

    /**
    * description: 查询List
    * @param ${table.className?uncap_first} 查询条件
    * @return 数据List
    */
    List<${table.className}> queryList(${table.className} ${table.className?uncap_first});

    /**
    * description: 根据ID查询
    * @param id 查询数据的ID
    * @return 数据对象
    */
    ${table.className} queryById(Long id);

}

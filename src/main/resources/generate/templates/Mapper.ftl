package ${packageName}.mapper;

import org.apache.ibatis.annotations.Param;
import ${packageName}.domain.${table.className};
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

/**
 * description: ${table.comments}
 *
 * @author ${author}
 * @date ${date?string('yyyy-MM-dd hh:mm')}
 * lastUpdateBy: ${author}
 * lastUpdateDate: ${date?string('yyyy-MM-dd hh:mm')}
 */
public interface ${table.className}Mapper extends BaseMapper<${table.className}> {

    /**
     * description: 批量删除
     * @param ids 删除数据的id数组
     * @return 删除成功的条数
     */
    Integer deleteBatch(@Param("ids") List<Long> ids);

}

package per.san.demo.mapper;

import org.apache.ibatis.annotations.Param;
import per.san.demo.domain.Demo;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface DemoMapper extends BaseMapper<Demo> {

    /**
     * description: 批量删除
     * @param ids 删除数据的id数组
     * @return 删除成功的条数
     */
    Integer deleteBatch(@Param("ids") List<Long> ids);
}

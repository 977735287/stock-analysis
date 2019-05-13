package per.san.generate.mapper;

import org.apache.ibatis.annotations.Param;
import per.san.generate.domain.Table;

import java.util.List;
import java.util.Map;

/**
 * description: 数据库接口
 *
 * @author shencai.huang@hand-china.com
 * @date 12/12/2018 13:29
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/12/2018
 */
public interface GenerateMapper {

    /**
     * description: 数据库表列表查询
     *
     * @param table
     * @return 数据库表的map
     */
    List<Table> queryList(Table table);

    /**
     * description: 数据库表列表查询
     *
     * @param tableNames 表名list
     * @return 数据库表的map
     */
    List<Table> queryTable(@Param("tableNames") List<String> tableNames);

    /**
     * description: 数据库表列表查询
     *
     * @param tableName 表名
     * @return 数据库表的map
     */
    List<Map<String, String>> queryColumns(String tableName);
}

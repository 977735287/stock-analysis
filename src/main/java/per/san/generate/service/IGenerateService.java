package per.san.generate.service;

import com.github.pagehelper.PageInfo;
import per.san.common.utils.page.PageRequest;
import per.san.generate.domain.Table;

import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 12/12/2018 19:03
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/12/2018
 */
public interface IGenerateService {

    PageInfo<Table> queryPage(PageRequest pageRequest, Table table);

    List<Table> generate(List<String> tableNames);

    List<Table> generateBatch(List<String> tableNames, String packagePath, String mapperXmlPath);

    List<Map<String, String>> queryColumns(String tableName);
}

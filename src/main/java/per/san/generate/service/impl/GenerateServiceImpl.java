package per.san.generate.service.impl;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.san.common.CommonConstant;
import per.san.common.utils.CodeGenerateUtils;
import per.san.common.utils.page.PageHelper;
import per.san.common.utils.page.PageRequest;
import per.san.generate.domain.Table;
import per.san.generate.mapper.MySQLGenerateMapper;
import per.san.generate.service.IGenerateService;

import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 12/12/2018 19:04
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/12/2018
 */
@Service
public class GenerateServiceImpl implements IGenerateService {

    private static final Logger logger = LoggerFactory.getLogger(GenerateServiceImpl.class);

    @Autowired
    MySQLGenerateMapper mySQLGenerateMapper;

    @Override
    public PageInfo<Table> queryPage(PageRequest pageRequest, Table table) {
        return PageHelper.doPageAndSort(pageRequest, () -> mySQLGenerateMapper.queryList(table));
    }

    @Override
    public List<Table> generate(List<String> tableNames) {
        List<Table> tables = mySQLGenerateMapper.queryTable(tableNames);
        try {
            CodeGenerateUtils.generate(tables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public List<Table> generateBatch(List<String> tableNames, String packagePath, String mapperXmlPath) {
        List<Table> tables = mySQLGenerateMapper.queryTable(tableNames);
        try {
            String targetPath = System.getProperty("user.dir") + "\\src\\main";
            logger.info(targetPath);
            if(packagePath.contains(CommonConstant.POINT)){
                packagePath = packagePath.replace(CommonConstant.POINT, CommonConstant.BACK_SLASH);
            }
            if(mapperXmlPath.contains(CommonConstant.POINT)){
                mapperXmlPath = mapperXmlPath.replace(CommonConstant.POINT, CommonConstant.BACK_SLASH);
            }
            CodeGenerateUtils.generate(tables, targetPath, packagePath, mapperXmlPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tables;
    }

    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        return mySQLGenerateMapper.queryColumns(tableName);
    }
}

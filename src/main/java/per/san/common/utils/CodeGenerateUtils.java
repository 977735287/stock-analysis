package per.san.common.utils;

import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import per.san.common.CommonConstant;
import per.san.generate.domain.BaseDomain;
import per.san.generate.domain.Column;
import per.san.generate.domain.Table;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description: 代码生成器
 *
 * @author shencai.huang@hand-china.com
 * @date 12/10/2018 17:10
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/10/2018
 */
public class CodeGenerateUtils {

    private static final Logger logger = LoggerFactory.getLogger(CodeGenerateUtils.class);

    private static final String AUTHOR = "sanchar";

    private static Date CURRENT_DATE;

    private static String TARGET_PATH;

    private static String PACKAGE_PATH;

    private static String MAPPER_XML_PATH;

    static {
        CURRENT_DATE = new Date();
        TARGET_PATH = System.getProperty("user.dir") + "\\src\\main";
        PACKAGE_PATH = "per\\san\\example";
        MAPPER_XML_PATH = "mapper\\example";
    }

    public static void generate(List<Table> tables) {
        generate(tables, TARGET_PATH, PACKAGE_PATH, MAPPER_XML_PATH);
    }

    /**
     * description: 代码生成
     *
     * @param tables        表属性信息
     * @param targetPath    代码生成项目路径
     * @param packagePath   代码生成包路径
     * @param mapperXmlPath 代码生成mapper.xml包路径
     */
    public static void generate(List<Table> tables, String targetPath, String packagePath, String mapperXmlPath) {
        TARGET_PATH = targetPath;
        PACKAGE_PATH = packagePath;
        MAPPER_XML_PATH = mapperXmlPath;
        List<String> fields = getBaseDomainFields();
        tables.forEach(item -> {
            List<Column> list = item.getColumns().stream().filter(column
                    -> !fields.contains(column.getAttrName())).collect(Collectors.toList());
            item.setColumns(list);
            generateModelFile(item, TARGET_PATH, PACKAGE_PATH);
            generateMapperFile(item, TARGET_PATH, PACKAGE_PATH);
            generateServiceFile(item, TARGET_PATH, PACKAGE_PATH);
            generateServiceImplFile(item, TARGET_PATH, PACKAGE_PATH);
            generateControllerFile(item, TARGET_PATH, PACKAGE_PATH);
            generateMapperXmlFile(item, TARGET_PATH, PACKAGE_PATH, MAPPER_XML_PATH);
        });
    }

    private static void generateModelFile(Table table, String targetPath, String packagePath) {
        String suffix = ".java";
        String path = targetPath + "\\java\\" + packagePath + "\\domain\\" + table.getClassName() + suffix;
        String templateName = "Model.ftl";
        generateFile(table, path, packagePath, templateName);
    }

    private static void generateMapperXmlFile(Table table, String targetPath, String packagePath, String mapperXmlPath) {
        String suffix = "Mapper.xml";
        String path = targetPath + "\\resources\\" + mapperXmlPath + "\\" + table.getClassName() + suffix;
        String templateName = "MapperXml.ftl";
        generateFile(table, path, packagePath, templateName);
    }

    private static void generateMapperFile(Table table, String targetPath, String packagePath) {
        String suffix = "Mapper.java";
        String path = targetPath + "\\java\\" + packagePath + "\\mapper\\" + table.getClassName() + suffix;
        String templateName = "Mapper.ftl";
        generateFile(table, path, packagePath, templateName);
    }

    private static void generateServiceImplFile(Table table, String targetPath, String packagePath) {
        String suffix = "ServiceImpl.java";
        String path = targetPath + "\\java\\" + packagePath + "\\service\\impl\\" + table.getClassName() + suffix;
        String templateName = "ServiceImpl.ftl";
        generateFile(table, path, packagePath, templateName);
    }

    private static void generateServiceFile(Table table, String targetPath, String packagePath) {
        String suffix = "Service.java";
        String path = targetPath + "\\java\\" + packagePath + "\\service\\I" + table.getClassName() + suffix;
        String templateName = "Service.ftl";
        generateFile(table, path, packagePath, templateName);
    }

    private static void generateControllerFile(Table table, String targetPath, String packagePath) {
        String suffix = "Controller.java";
        String path = targetPath + "\\java\\" + packagePath + "\\controller\\" + table.getClassName() + suffix;
        String templateName = "Controller.ftl";
        generateFile(table, path, packagePath, templateName);
    }

    private static void generateFile(Table table, String path, String packagePath, String templateName) {
        logger.info(path);
        Map<String, Object> dataMap = new HashMap<>(2);
        dataMap.put("table", table);
        String packageName = packagePath;
        if (packageName.contains(CommonConstant.BACK_SLASH)) {
            packageName = packageName.replace(CommonConstant.BACK_SLASH, ".");
        } else if (packageName.contains(CommonConstant.SLASH)) {
            packageName = packageName.replace(CommonConstant.SLASH, ".");
        }
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        dataMap.put("packageName", packageName);
        try {
            generateFileByTemplate(templateName, file, dataMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generateFileByTemplate(final String templateName, File file, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName);
        FileOutputStream fos = new FileOutputStream(file);
        dataMap.put("author", AUTHOR);
        dataMap.put("date", CURRENT_DATE);
        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
        out.flush();
        out.close();
    }

    /**
     * BaseDomain的列属性
     */
    public static List<String> getBaseDomainFields() {
        Class<?> clz = new BaseDomain().getClass();
        Field[] fields = clz.getDeclaredFields();
        List<String> list = new ArrayList<>();
        for (Field field : fields) {
            list.add(StringUtils.capitalize(field.getName()));
        }
        return list;
    }
}

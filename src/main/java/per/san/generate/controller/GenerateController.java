package per.san.generate.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import per.san.common.utils.page.CustomPageRequest;
import per.san.common.utils.page.PageRequest;
import per.san.generate.domain.Table;
import per.san.generate.service.IGenerateService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 12/13/2018 13:00
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/13/2018
 */

@RestController
@RequestMapping("/v1/generate")
public class GenerateController {

    @Autowired
    IGenerateService iGenerateService;

    @ApiOperation(value = "分页查询")
    @GetMapping("/table/page")
    public ResponseEntity<PageInfo<Table>> queryPage(
            PageRequest pageRequest,
            @RequestParam(required = false) String tableName,
            @RequestParam(required = false) String engine,
            @RequestParam(required = false) String comments) {
        Table table = new Table();
        table.setTableName(tableName);
        table.setEngine(engine);
        table.setComments(comments);
        return new ResponseEntity<>(iGenerateService.queryPage(pageRequest, table), HttpStatus.OK);
    }

    @ApiOperation(value = "生成代码")
    @PostMapping("/{tableName}/")
    public ResponseEntity<Table> generate(@PathVariable("tableName") String tableName) {
        List<String> tableNames = new ArrayList<>();
        tableNames.add(tableName);
        List<Table> tables = iGenerateService.generate(tableNames);
        Table table = null;
        if(tables != null && !tables.isEmpty()){
            table = tables.get(0);
        }
        return new ResponseEntity<>(table, HttpStatus.OK);
    }

    @ApiOperation(value = "批量生成代码（默认路径）")
    @PostMapping("/batch/default")
    public ResponseEntity<List<Table>> generateBatchDefault(@RequestBody List<String> tableNames) {
        return new ResponseEntity<>(iGenerateService.generate(tableNames), HttpStatus.OK);
    }

    @ApiOperation(value = "批量生成代码")
    @PostMapping("/batch")
    public ResponseEntity<List<Table>> generateBatch(
            @RequestParam(defaultValue = "per.san.demo") String packagePath,
            @RequestParam(defaultValue = "mapper.demo") String mapperXmlPath,
            @RequestBody List<String> tableNames) {
        return new ResponseEntity<>(iGenerateService.generateBatch(tableNames, packagePath, mapperXmlPath), HttpStatus.OK);
    }

    @ApiOperation(value = "查询表列信息")
    @GetMapping("/table/info/{tableName}")
    public ResponseEntity<List<Map<String, String>>> queryColumns(@PathVariable("tableName") String tableName) {
        return new ResponseEntity<>(iGenerateService.queryColumns(tableName), HttpStatus.OK);
    }
}

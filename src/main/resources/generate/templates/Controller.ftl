package ${packageName}.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.san.common.utils.page.PageRequest;
import ${packageName}.domain.${table.className};
import ${packageName}.service.I${table.className}Service;
<#if table.existDate == 1>

import java.util.Date;
</#if>
import java.util.List;

/**
 * description: ${table.comments}
 *
 * @author ${author}
 * @date ${date?string('yyyy-MM-dd hh:mm')}
 * lastUpdateBy: ${author}
 * lastUpdateDate: ${date?string('yyyy-MM-dd hh:mm')}
 */
@RestController
@RequestMapping("/v1/${table.tableName?replace("_","/")}")
public class ${table.className}Controller {

    @Autowired
    I${table.className}Service i${table.className}Service;

    @ApiOperation(value = "新增")
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody ${table.className} ${table.className?uncap_first}) {
    return new ResponseEntity<>(i${table.className}Service.add(${table.className?uncap_first}), HttpStatus.OK);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(i${table.className}Service.deleteByPrimaryKey(id), HttpStatus.OK);
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/batch")
    public ResponseEntity<Integer> deleteBatch(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(i${table.className}Service.deleteBatch(ids), HttpStatus.OK);
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody ${table.className} ${table.className?uncap_first}) {
        return new ResponseEntity<>(i${table.className}Service.update(${table.className?uncap_first}), HttpStatus.OK);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public ResponseEntity<PageInfo<${table.className}>> pageQuery(
            PageRequest pageRequest,
            <#if table.columns?exists>
                <#list table.columns as column>
            @RequestParam(required = false) ${column.attrType} ${column.attrName?uncap_first}<#if column_has_next>,</#if>
                </#list>
            </#if>) {
        ${table.className} ${table.className?uncap_first} = new ${table.className}();
        <#if table.columns?exists>
            <#list table.columns as column>
        ${table.className?uncap_first}.set${column.attrName}(${column.attrName?uncap_first});
            </#list>
        </#if>
        return new ResponseEntity<>(i${table.className}Service.pageQuery(pageRequest, ${table.className?uncap_first}), HttpStatus.OK);
    }

    @ApiOperation(value = "查询list")
    @GetMapping
    public ResponseEntity<List<${table.className}>> queryList(
            <#if table.columns?exists>
                <#list table.columns as column>
            @RequestParam(required = false) ${column.attrType} ${column.attrName?uncap_first}<#if column_has_next>,</#if>
                </#list>
            </#if>) {
        ${table.className} ${table.className?uncap_first} = new ${table.className}();
        <#if table.columns?exists>
            <#list table.columns as column>
        ${table.className?uncap_first}.set${column.attrName}(${column.attrName?uncap_first});
            </#list>
        </#if>
        return new ResponseEntity<>(i${table.className}Service.queryList(${table.className?uncap_first}), HttpStatus.OK);
    }

    @ApiOperation(value = "根据ID查询")
    @GetMapping("/{id}")
    public ResponseEntity<${table.className}> queryById(
        @PathVariable("id") Long id) {
        return new ResponseEntity<>(i${table.className}Service.queryById(id), HttpStatus.OK);
    }
}

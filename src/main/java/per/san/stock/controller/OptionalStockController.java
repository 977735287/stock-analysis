package per.san.stock.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
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
import per.san.stock.domain.OptionalStock;
import per.san.stock.service.IOptionalStockService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author sanchar
 * @date 2019-05-13 09:03
 * lastUpdateBy: sanchar
 * lastUpdateDate: 2019-05-13 09:03
 */
@RestController
@RequestMapping("/v1/optional/stock")
public class OptionalStockController {

    @Autowired
    IOptionalStockService iOptionalStockService;

    @ApiOperation(value = "新增")
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody OptionalStock optionalStock) {
        return new ResponseEntity<>(iOptionalStockService.add(optionalStock), HttpStatus.OK);
    }

    @ApiOperation(value = "批量新增")
    @PostMapping("/batch")
    public ResponseEntity<Integer> addList(@RequestBody List<OptionalStock> optionalStocks) {
        return new ResponseEntity<>(iOptionalStockService.addList(optionalStocks), HttpStatus.OK);
    }

    @ApiOperation(value = "批量新增")
    @PostMapping("/code/batch")
    public ResponseEntity<Integer> addCodeList(@RequestBody List<String> codes) {
        return new ResponseEntity<>(iOptionalStockService.addCodeList(codes), HttpStatus.OK);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(iOptionalStockService.deleteByPrimaryKey(id), HttpStatus.OK);
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/batch")
    public ResponseEntity<Integer> deleteBatch(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(iOptionalStockService.deleteBatch(ids), HttpStatus.OK);
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody OptionalStock optionalStock) {
        return new ResponseEntity<>(iOptionalStockService.update(optionalStock), HttpStatus.OK);
    }

    @ApiOperation(value = "批量更新")
    @PutMapping("/update/batch")
    public ResponseEntity<Integer> update(@RequestBody List<OptionalStock> optionalStockList) {
        return new ResponseEntity<>(iOptionalStockService.updateBatch(optionalStockList), HttpStatus.OK);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public ResponseEntity<PageInfo<OptionalStock>> pageQuery(
            PageRequest pageRequest,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        OptionalStock optionalStock = new OptionalStock();
        optionalStock.setCode(code);
        optionalStock.setName(name);
        return new ResponseEntity<>(iOptionalStockService.pageQuery(pageRequest, optionalStock), HttpStatus.OK);
    }

    @ApiOperation(value = "查询list")
    @GetMapping
    public ResponseEntity<List<OptionalStock>> queryList(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name
    ) {
        OptionalStock optionalStock = new OptionalStock();
        optionalStock.setCode(code);
        optionalStock.setName(name);
        return new ResponseEntity<>(iOptionalStockService.queryList(optionalStock), HttpStatus.OK);
    }

    @ApiOperation(value = "根据ID查询")
    @GetMapping("/{id}")
    public ResponseEntity<OptionalStock> queryById(
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(iOptionalStockService.queryById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "自选股实时数据")
    @GetMapping("/data/current")
    public ResponseEntity<List<List<Object>>> getOptionalDataCurrent() {
        return new ResponseEntity<>(iOptionalStockService.getOptionalDataCurrent(), HttpStatus.OK);
    }
}

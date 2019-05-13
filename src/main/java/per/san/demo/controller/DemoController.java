package per.san.demo.controller;

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
import per.san.demo.domain.Demo;
import per.san.demo.service.IDemoService;

import java.util.Date;
import java.util.List;

/**
 * description:
 *
 * @author sanchar
 * @date 12/5/2018 15:00
 * lastUpdateBy: sanchar
 * lastUpdateDate: 12/5/2018
 */
@RestController
@RequestMapping("/v1/demo")
public class DemoController {

    @Autowired
    IDemoService iDemoService;

    @ApiOperation(value = "新增")
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody Demo demo) {
        return new ResponseEntity<>(iDemoService.add(demo), HttpStatus.OK);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(iDemoService.deleteByPrimaryKey(id), HttpStatus.OK);
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/batch")
    public ResponseEntity<Integer> deleteBatch(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(iDemoService.deleteBatch(ids), HttpStatus.OK);
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody Demo demo) {
        return new ResponseEntity<>(iDemoService.update(demo), HttpStatus.OK);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public ResponseEntity<PageInfo<Demo>> pageQuery(
            PageRequest pageRequest,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password) {
        Demo demo = new Demo();
        demo.setName(name);
        demo.setPassword(password);
        return new ResponseEntity<>(iDemoService.pageQuery(pageRequest, demo), HttpStatus.OK);
    }

    @ApiOperation(value = "查询list")
    @GetMapping
    public ResponseEntity<List<Demo>> queryList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) Date date) {
        Demo demo = new Demo();
        demo.setName(name);
        demo.setPassword(password);
        return new ResponseEntity<>(iDemoService.queryList(demo), HttpStatus.OK);
    }

    @ApiOperation(value = "根据ID查询")
    @GetMapping("/id/{id}")
    public ResponseEntity<Demo> queryById(
            @PathVariable("id") Long id) {
        return new ResponseEntity<>(iDemoService.queryById(id), HttpStatus.OK);
    }

}

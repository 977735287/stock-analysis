package per.san.example.controller;

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
import per.san.example.domain.UpDownPercent;
import per.san.example.service.IUpDownPercentService;
import java.util.List;

/**
 * description: 
 *
 * @author sanchar
 * @date 2019-03-31 10:13
 * lastUpdateBy: sanchar
 * lastUpdateDate: 2019-03-31 10:13
 */
@RestController
@RequestMapping("/v1/up/down/percent")
public class UpDownPercentController {

    @Autowired
    IUpDownPercentService iUpDownPercentService;

    @ApiOperation(value = "新增")
    @PostMapping
    public ResponseEntity<Integer> add(@RequestBody UpDownPercent upDownPercent) {
    return new ResponseEntity<>(iUpDownPercentService.add(upDownPercent), HttpStatus.OK);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> delete(@PathVariable("id") Long id) {
        return new ResponseEntity<>(iUpDownPercentService.deleteByPrimaryKey(id), HttpStatus.OK);
    }

    @ApiOperation(value = "批量删除")
    @DeleteMapping("/batch")
    public ResponseEntity<Integer> deleteBatch(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(iUpDownPercentService.deleteBatch(ids), HttpStatus.OK);
    }

    @ApiOperation(value = "更新")
    @PutMapping
    public ResponseEntity<Integer> update(@RequestBody UpDownPercent upDownPercent) {
        return new ResponseEntity<>(iUpDownPercentService.update(upDownPercent), HttpStatus.OK);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping("/page")
    public ResponseEntity<PageInfo<UpDownPercent>> pageQuery(
            PageRequest pageRequest,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double trade,
            @RequestParam(required = false) Double priceChange,
            @RequestParam(required = false) String changePercent,
            @RequestParam(required = false) Double settlement,
            @RequestParam(required = false) Double open,
            @RequestParam(required = false) Double high,
            @RequestParam(required = false) Double low,
            @RequestParam(required = false) Double volume,
            @RequestParam(required = false) Double amount
            ) {
        UpDownPercent upDownPercent = new UpDownPercent();
        upDownPercent.setCode(code);
        upDownPercent.setName(name);
        upDownPercent.setTrade(trade);
        upDownPercent.setPriceChange(priceChange);
        upDownPercent.setChangePercent(changePercent);
        upDownPercent.setSettlement(settlement);
        upDownPercent.setOpen(open);
        upDownPercent.setHigh(high);
        upDownPercent.setLow(low);
        upDownPercent.setVolume(volume);
        upDownPercent.setAmount(amount);
        return new ResponseEntity<>(iUpDownPercentService.pageQuery(pageRequest, upDownPercent), HttpStatus.OK);
    }

    @ApiOperation(value = "查询list")
    @GetMapping
    public ResponseEntity<List<UpDownPercent>> queryList(
            @RequestParam(required = false) String code,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double trade,
            @RequestParam(required = false) Double priceChange,
            @RequestParam(required = false) String changePercent,
            @RequestParam(required = false) Double settlement,
            @RequestParam(required = false) Double open,
            @RequestParam(required = false) Double high,
            @RequestParam(required = false) Double low,
            @RequestParam(required = false) Double volume,
            @RequestParam(required = false) Double amount
            ) {
        UpDownPercent upDownPercent = new UpDownPercent();
        upDownPercent.setCode(code);
        upDownPercent.setName(name);
        upDownPercent.setTrade(trade);
        upDownPercent.setPriceChange(priceChange);
        upDownPercent.setChangePercent(changePercent);
        upDownPercent.setSettlement(settlement);
        upDownPercent.setOpen(open);
        upDownPercent.setHigh(high);
        upDownPercent.setLow(low);
        upDownPercent.setVolume(volume);
        upDownPercent.setAmount(amount);
        return new ResponseEntity<>(iUpDownPercentService.queryList(upDownPercent), HttpStatus.OK);
    }

    @ApiOperation(value = "根据ID查询")
    @GetMapping("/{id}")
    public ResponseEntity<UpDownPercent> queryById(
        @PathVariable("id") Long id) {
        return new ResponseEntity<>(iUpDownPercentService.queryById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "每天定时更新数据")
    @GetMapping("/info/update")
    public ResponseEntity<Integer> infoUpdate() {
        return new ResponseEntity<>(iUpDownPercentService.infoUpdate(), HttpStatus.OK);
    }
}

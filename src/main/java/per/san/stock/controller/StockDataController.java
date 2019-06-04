package per.san.stock.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import per.san.stock.service.IStockDataService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author Sanchar
 * @date 5/21/2019 19:48
 * lastUpdateBy: Sanchar
 * lastUpdateDate: 5/21/2019 19:48
 */
@RestController
@RequestMapping("/v1/stock/data")
public class StockDataController {

    @Autowired
    IStockDataService iStockDataService;


    @ApiOperation(value = "download")
    @GetMapping("/history/download")
    public void downloadHistoryInfo(
            HttpServletResponse response,
            @RequestParam(value = "start") String start,
            @RequestParam(value = "end") String end) throws IOException {
        iStockDataService.downloadHistoryInfo(response, start, end);
    }

    @ApiOperation(value = "大盘指数")
    @GetMapping("/market")
    public ResponseEntity<Map<String, List<String>>> getMarketIndex() {
        return new ResponseEntity<>(iStockDataService.getMarketIndex(), HttpStatus.OK);
    }

    @ApiOperation(value = "分时数据")
    @GetMapping("/min/data/{type}/{code}")
    public ResponseEntity<Map<String, Object>> getMinuteData(@PathVariable("code") String code,
                                                             @PathVariable("type") String type) {
        return new ResponseEntity<>(iStockDataService.getMinuteData(code, type), HttpStatus.OK);
    }

    @ApiOperation(value = "历史涨停股实时数据")
    @GetMapping("/history/data/current")
    public ResponseEntity<List<List<Object>>> getHistoryDataCurrent(@RequestParam("date") String date) {
        return new ResponseEntity<>(iStockDataService.getHistoryDataCurrent(date), HttpStatus.OK);
    }
}

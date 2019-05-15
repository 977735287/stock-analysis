package per.san.stock.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.san.common.utils.ExcelUtils;
import per.san.common.utils.HttpRequest;
import per.san.common.utils.page.PageHelper;
import per.san.common.utils.page.PageRequest;
import per.san.stock.domain.OptionalStock;
import per.san.stock.mapper.OptionalStockMapper;
import per.san.stock.service.IOptionalStockService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
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
@Service
public class OptionalStockServiceImpl implements IOptionalStockService {

    @Autowired
    OptionalStockMapper optionalStockMapper;

    @Override
    public Integer add(OptionalStock optionalStock) {
        return optionalStockMapper.insertSelective(optionalStock);
    }

    @Override
    public Integer addList(List<OptionalStock> optionalStocks) {
        return optionalStockMapper.insertList(optionalStocks);
    }

    @Override
    public Integer addCodeList(List<String> codes) {
        List<OptionalStock> optionalStocks = Lists.newArrayList();
        codes.forEach(item -> {
            OptionalStock optionalStock = new OptionalStock();
            optionalStock.setCode(item);
            if (item.substring(0,1).equals("0")) {
                optionalStock.setArea("sz");
            }
            if (item.substring(0,1).equals("6")) {
                optionalStock.setArea("sh");
            }
            optionalStocks.add(optionalStock);
        });
        return optionalStockMapper.insertList(optionalStocks);
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) {
        return optionalStockMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteBatch(List<Long> ids) {
        return optionalStockMapper.deleteBatch(ids);
    }

    @Override
    public Integer update(OptionalStock optionalStock) {
        return optionalStockMapper.updateByPrimaryKeySelective(optionalStock);
    }

    @Override
    public PageInfo<OptionalStock> pageQuery(PageRequest pageRequest, OptionalStock optionalStock) {
        return PageHelper.doPage(pageRequest, () -> optionalStockMapper.select(optionalStock));
    }

    @Override
    public List<OptionalStock> queryList(OptionalStock optionalStock) {
        return optionalStockMapper.select(optionalStock);
    }

    @Override
    public OptionalStock queryById(Long id) {
    return optionalStockMapper.selectByPrimaryKey(id);
    }

    @Override
    public void downloadHistoryInfo(HttpServletResponse response, String start, String end) throws IOException {
        List<OptionalStock> optionalStocks = this.queryList(new OptionalStock());
        Map<String, List<List<Object>>> dataMap = Maps.newHashMap();
        List<List<Object>> data = Lists.newArrayList();
        Map<Integer, List<Short>> redIndexMap = Maps.newHashMap();
        Map<Integer, List<Short>> blueIndexMap = Maps.newHashMap();
        int i = 1;
        String url = "http://q.stock.sohu.com/hisHq";
        for (OptionalStock optionalStock : optionalStocks) {
            List<Object> header = Lists.newArrayList();
            List<Object> line = Lists.newArrayList();
            String param = "code=cn_" + optionalStock.getCode()
                    + "&start=" + start + "&end=" + end + "&stat=0&order=D&period=d&callback=&rt=jsonp";
            String result = HttpRequest.get(param, url);
            result = result.substring(2, result.length() - 3);
            Gson gson = new Gson();
            Map<String, Object> map = gson.fromJson(result, new TypeToken<Map<String, Object>>(){}.getType());
            List<List<String>> lists = (List<List<String>>) map.get("hq");
            int k = 1;
            for (List<String> list : lists) {
                if (i == 1) {
                    header.add("");
                    header.add(list.get(0));
                    header.add("");
                    header.add("");
                }
                if (k == 1) {
                    line.add(optionalStock.getCode());
                }
                line.add(list.get(2));
                line.add(list.get(4));
                line.add(list.get(3));
                line.add("    ");
                k = 0;
            }
            if (i == 1) {
                data.add(header);
            }
            i = 0;
            data.add(line);
        }
        dataMap.put("data", data);
        response.addHeader("Content-Disposition", "filename=down.xlsx");

        ExcelUtils.writeExcel(dataMap, Lists.newArrayList()).write(response.getOutputStream());
    }
}

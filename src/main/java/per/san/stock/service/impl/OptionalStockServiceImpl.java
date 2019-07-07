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
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static final String historyInfoUrl = "http://q.stock.sohu.com/hisHq";
    private static final String marketIndexUrl = "http://hq.sinajs.cn/list=s_sh000001,s_sz399001,s_sz399006";
    private static final String minuteDataUrl = "http://pdfm2.eastmoney.com/EM_UBG_PDTI_Fast/api/js";
    private static final String currentDataUrl = "http://hq.sinajs.cn/list=";

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
    public Integer updateBatch(List<OptionalStock> optionalStockList) {
        optionalStockList.forEach(item -> optionalStockMapper.updateByPrimaryKeySelective(item));
        return optionalStockList.size();
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
    public List<List<Object>> getOptionalDataCurrent() {
        List<OptionalStock> optionalStocks = optionalStockMapper.queryList(new OptionalStock());
        String param = "";
        for (OptionalStock item : optionalStocks) {
            param += item.getArea() + item.getCode() + ",";
        }
        if (!param.isEmpty()) {
            param = param.substring(0, param.length() - 1);
        }
        String res = HttpRequest.get(null, currentDataUrl + param);
        res = " " + res;
        res = res.substring(0, res.length() - 1);
        List<String> strings = Lists.newArrayList(res.split(";"));
        strings = strings.stream().map(item -> item.substring(22, item.length() - 1)).collect(Collectors.toList());
        List<List<Object>> lists = Lists.newArrayList();
        for (int i = 0; i < strings.size(); i++) {
            List<Object> list = Lists.newArrayList(strings.get(i).split(","));
            list.add(optionalStocks.get(i).getCode());
            lists.add(list);
        }
        return lists;
    }
}

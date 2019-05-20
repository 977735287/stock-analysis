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
        int row = 1;
        for (OptionalStock optionalStock : optionalStocks) {
            List<Object> header = Lists.newArrayList();
            List<Object> line = Lists.newArrayList();
            String param = "code=cn_" + optionalStock.getCode()
                    + "&start=" + start + "&end=" + end + "&stat=0&order=D&period=d&callback=&rt=jsonp";
            String result = HttpRequest.get(param, historyInfoUrl);
            result = result.substring(2, result.length() - 3);
            Gson gson = new Gson();
            Map<String, Object> map = gson.fromJson(result, new TypeToken<Map<String, Object>>(){}.getType());
            List<List<String>> lists = (List<List<String>>) map.get("hq");
            int k = 1;
            List<Short> redIndexList = Lists.newArrayList();
            List<Short> blueIndexList = Lists.newArrayList();
            int j = 1;
            for (List<String> list : lists) {
                if (i == 1) {
                    header.add("");
                    header.add(list.get(0));
                    header.add("");
                    header.add("");
                }
                if (k == 1) {
                    String name = optionalStock.getCode();
                    if (optionalStock.getName() != null) {
                        name += "(" + optionalStock.getName() + ")";
                    }
                    line.add(name);
                }
                line.add(list.get(2));
                line.add(list.get(4));
                String downPrice = list.get(3);
                line.add(downPrice);
                if (downPrice != null && Double.parseDouble(downPrice) < 0) {
                    redIndexList.add((short) j);
                    redIndexList.add((short) (j + 1));
                    redIndexList.add((short) (j + 2));
                }
                if (downPrice != null && Double.parseDouble(downPrice) > 0) {
                    blueIndexList.add((short) j);
                    blueIndexList.add((short) (j + 1));
                    blueIndexList.add((short) (j + 2));
                }
                line.add("    ");
                k = 0;
                j += 4;
            }
            if (i == 1) {
                data.add(header);
            }
            redIndexMap.put(row, redIndexList);
            blueIndexMap.put(row, blueIndexList);
            i = 0;
            row ++;
            data.add(line);
        }
        dataMap.put("data", data);
        response.addHeader("Content-Disposition", "filename=down.xlsx");
        ExcelUtils.writeExcel(dataMap, redIndexMap, blueIndexMap).write(response.getOutputStream());
    }

    @Override
    public Map<String, List<String>> getMarketIndex() {
        String sh_res = HttpRequest.get(null, marketIndexUrl);
        String sh = sh_res.substring(sh_res.indexOf("var hq_str_s_sh000001"), sh_res.indexOf("var hq_str_s_sz399001"));
        String sz = sh_res.substring(sh_res.indexOf("var hq_str_s_sz399001"), sh_res.indexOf("var hq_str_s_sz399006"));
        String cy = sh_res.substring(sh_res.indexOf("var hq_str_s_sz399006"));
        sh = sh.substring(sh.indexOf("\"") + 1, sh.lastIndexOf("\""));
        sz = sz.substring(sz.indexOf("\"") + 1, sz.lastIndexOf("\""));
        cy = cy.substring(cy.indexOf("\"") + 1, cy.lastIndexOf("\""));
        Map<String, List<String>> marketMap = Maps.newHashMap();
        marketMap.put("sh", Lists.newArrayList(sh.split(",")));
        marketMap.put("sz", Lists.newArrayList(sz.split(",")));
        marketMap.put("cy", Lists.newArrayList(cy.split(",")));
        return marketMap;
    }

    @Override
    public Map<String, Object> getMinuteData(String code, String type) {
        String param = "id=" + code;
        if ("sh".equals(type)) {
            param += "1";
        }else {
            param += "2";
        }
        param += "&TYPE=r&rtntype=5&isCR=false";
        String result = HttpRequest.get(param, minuteDataUrl);
        result = result.substring(1, result.length() - 1);
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(result, new TypeToken<Map<String, Object>>(){}.getType());
        return map;
    }
}

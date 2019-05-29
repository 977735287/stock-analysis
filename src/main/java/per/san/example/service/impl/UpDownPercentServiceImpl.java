package per.san.example.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.san.common.HolidayCopy;
import per.san.common.utils.HttpRequest;
import per.san.common.utils.page.PageHelper;
import per.san.common.utils.page.PageRequest;
import per.san.example.domain.UpDownPercent;
import per.san.example.mapper.UpDownPercentMapper;
import per.san.example.service.IUpDownPercentService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * description: 
 *
 * @author sanchar
 * @date 2019-03-31 10:13
 * lastUpdateBy: sanchar
 * lastUpdateDate: 2019-03-31 10:13
 */
@Service
public class UpDownPercentServiceImpl implements IUpDownPercentService {

    private static final String url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData";
    private static final String paramDown = "page=1&num=20&sort=changepercent&asc=1&node=hs_a&symbol=&_s_r_a=setlen";
    private static final String paramUp = "page=1&num=100&sort=changepercent&asc=0&node=hs_a&symbol=&_s_r_a=setlen";

    @Autowired
    UpDownPercentMapper upDownPercentMapper;

    @Override
    public Integer add(UpDownPercent upDownPercent) {
        return upDownPercentMapper.insertSelective(upDownPercent);
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) {
        return upDownPercentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteBatch(List<Long> ids) {
        return upDownPercentMapper.deleteBatch(ids);
    }

    @Override
    public Integer update(UpDownPercent upDownPercent) {
        return upDownPercentMapper.updateByPrimaryKeySelective(upDownPercent);
    }

    @Override
    public PageInfo<UpDownPercent> pageQuery(PageRequest pageRequest, UpDownPercent upDownPercent) {
        return PageHelper.doPage(pageRequest, () -> upDownPercentMapper.select( upDownPercent));
    }

    @Override
    public List<UpDownPercent> queryList(UpDownPercent upDownPercent) {
        return upDownPercentMapper.select(upDownPercent);
    }

    @Override
    public UpDownPercent queryById(Long id) {
    return upDownPercentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer infoUpdate() {
        HolidayCopy holidayCopy = new HolidayCopy();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        int jsonResult = holidayCopy.request(f.format(new Date()));
        if (0 != jsonResult) {
            return 0;
        }
        if (upDownPercentMapper.currentCount() > 0) {
            return 0;
        }
        String down = HttpRequest.get(paramDown, url);
        String up = HttpRequest.get(paramUp, url);
        Gson gson = new Gson();
        List<Map<String, Object>> mapsDown = gson.fromJson(down, new TypeToken<List<Map<String, Object>>>(){}.getType());
        List<Map<String, Object>> mapsUp = gson.fromJson(up, new TypeToken<List<Map<String, Object>>>(){}.getType());
        List<UpDownPercent> upDownPercents = Lists.newArrayList();
        mapsDown.forEach(item -> {
            UpDownPercent upDownPercent = new UpDownPercent();
            upDownPercent.setCode((String) item.get("code"));
            upDownPercent.setName((String) item.get("name"));
            upDownPercent.setTrade(Double.valueOf((String) item.get("trade")));
            upDownPercent.setPriceChange(Double.valueOf((String) item.get("pricechange")));
            upDownPercent.setChangePercent((String) item.get("changepercent"));
            upDownPercent.setSettlement(Double.valueOf((String) item.get("settlement")));
            upDownPercent.setOpen(Double.valueOf((String) item.get("open")));
            upDownPercent.setHigh(Double.valueOf((String) item.get("high")));
            upDownPercent.setLow(Double.valueOf((String) item.get("low")));
            upDownPercent.setVolume((Double) item.get("volume"));
            upDownPercent.setAmount((Double) item.get("amount"));
            upDownPercent.setCreateDate(new Date());
            upDownPercent.setTrendStatus("down");
            upDownPercents.add(upDownPercent);
            upDownPercentMapper.insertSelective(upDownPercent);
        });
        mapsUp.forEach(item -> {
            UpDownPercent upDownPercent = new UpDownPercent();
            upDownPercent.setCode((String) item.get("code"));
            upDownPercent.setName((String) item.get("name"));
            upDownPercent.setTrade(Double.valueOf((String) item.get("trade")));
            upDownPercent.setPriceChange(Double.valueOf((String) item.get("pricechange")));
            upDownPercent.setChangePercent((String) item.get("changepercent"));
            upDownPercent.setSettlement(Double.valueOf((String) item.get("settlement")));
            upDownPercent.setOpen(Double.valueOf((String) item.get("open")));
            upDownPercent.setHigh(Double.valueOf((String) item.get("high")));
            upDownPercent.setLow(Double.valueOf((String) item.get("low")));
            upDownPercent.setVolume((Double) item.get("volume"));
            upDownPercent.setAmount((Double) item.get("amount"));
            upDownPercent.setCreateDate(new Date());
            upDownPercent.setTrendStatus("up");
            upDownPercents.add(upDownPercent);
            upDownPercentMapper.insertSelective(upDownPercent);
        });
        return upDownPercents.size();
    }
}

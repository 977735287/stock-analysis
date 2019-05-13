package per.san.demo.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.san.common.utils.page.PageHelper;
import per.san.common.utils.page.PageRequest;
import per.san.demo.domain.Demo;
import per.san.demo.mapper.DemoMapper;
import per.san.demo.service.IDemoService;

import java.util.List;
/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 12/13/2018 13:00
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/13/2018
 */
@Service
public class DemoServiceImpl implements IDemoService {

    @Autowired
    DemoMapper demoMapper;

    @Override
    public Integer add(Demo demo) {
        return demoMapper.insertSelective(demo);
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) {
        return demoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteBatch(List<Long> ids) {
        return demoMapper.deleteBatch(ids);
    }

    @Override
    public Integer update(Demo demo) {
        return demoMapper.updateByPrimaryKeySelective(demo);
    }

    @Override
    public PageInfo<Demo> pageQuery(PageRequest pageRequest, Demo demo) {
        return PageHelper.doPage(pageRequest, () -> demoMapper.select(demo));
    }

    @Override
    public List<Demo> queryList(Demo demo) {
        return demoMapper.select(demo);
    }

    @Override
    public Demo queryById(Long id) {
        return demoMapper.selectByPrimaryKey(id);
    }
}

package ${packageName}.service.impl;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.san.common.utils.page.PageHelper;
import per.san.common.utils.page.PageRequest;
import ${packageName}.domain.${table.className};
import ${packageName}.mapper.${table.className}Mapper;
import ${packageName}.service.I${table.className}Service;

import java.util.List;

/**
 * description: ${table.comments}
 *
 * @author ${author}
 * @date ${date?string('yyyy-MM-dd hh:mm')}
 * lastUpdateBy: ${author}
 * lastUpdateDate: ${date?string('yyyy-MM-dd hh:mm')}
 */
@Service
public class ${table.className}ServiceImpl implements I${table.className}Service {

    @Autowired
    ${table.className}Mapper ${table.className?uncap_first}Mapper;

    @Override
    public Integer add(${table.className} ${table.className?uncap_first}) {
        return ${table.className?uncap_first}Mapper.insertSelective(${table.className?uncap_first});
    }

    @Override
    public Integer deleteByPrimaryKey(Long id) {
        return ${table.className?uncap_first}Mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteBatch(List<Long> ids) {
        return ${table.className?uncap_first}Mapper.deleteBatch(ids);
    }

    @Override
    public Integer update(${table.className} ${table.className?uncap_first}) {
        return ${table.className?uncap_first}Mapper.updateByPrimaryKeySelective(${table.className?uncap_first});
    }

    @Override
    public PageInfo<${table.className}> pageQuery(PageRequest pageRequest, ${table.className} ${table.className?uncap_first}) {
        return PageHelper.doPage(pageRequest, () -> ${table.className?uncap_first}Mapper.select(${table.className?uncap_first}));
    }

    @Override
    public List<${table.className}> queryList(${table.className} ${table.className?uncap_first}) {
        return ${table.className?uncap_first}Mapper.select(${table.className?uncap_first});
    }

    @Override
    public ${table.className} queryById(Long id) {
    return ${table.className?uncap_first}Mapper.selectByPrimaryKey(id);
    }
}

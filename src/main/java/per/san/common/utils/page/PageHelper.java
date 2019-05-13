package per.san.common.utils.page;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;

import java.util.List;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 12/13/2018 12:11
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/13/2018
 */
public class PageHelper extends PageMethod {
    public PageHelper() {
    }

    public static <E> PageInfo<E> doPage(PageRequest pageRequest, Select select) {
        startPage(pageRequest.getPage(), pageRequest.getSize());
        List<E> list = select.doSelect();
        PageInfo<E> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    public static <E> PageInfo<E> doPageAndSort(PageRequest pageRequest, Select select) {
        startPage(pageRequest.getPage(), pageRequest.getSize(), pageRequest.getSort());
        List<E> list = select.doSelect();
        PageInfo<E> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}

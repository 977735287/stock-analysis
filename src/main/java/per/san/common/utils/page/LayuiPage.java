package per.san.common.utils.page;

import com.github.pagehelper.PageInfo;
import per.san.stock.domain.OptionalStock;

import java.util.Collections;
import java.util.List;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 5/23/2019 13:50
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 5/23/2019
 */
public class LayuiPage {

    private Long code;

    private Long count;

    private List<Object> data;

    private String msg;

    public LayuiPage(PageInfo<OptionalStock> pageInfo) {
        this.code = 0L;
        this.count = pageInfo.getTotal();
        this.data = Collections.singletonList(pageInfo.getList());
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

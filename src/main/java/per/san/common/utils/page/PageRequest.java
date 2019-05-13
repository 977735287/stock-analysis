package per.san.common.utils.page;

/**
 * description: 分页参数
 *
 * @author shencai.huang@hand-china.com
 * @date 12/12/2018 19:26
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/12/2018
 */
public class PageRequest {

    private int page;
    private int size;
    private String sort;

    public PageRequest() {}

    public PageRequest(int page, int size) {
        this(page, size, null);
    }

    public PageRequest(int page, int size, String sort) {
        this.page = page;
        this.size = size;
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}

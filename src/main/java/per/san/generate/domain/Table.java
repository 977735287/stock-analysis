package per.san.generate.domain;

import per.san.common.CommonConstant;
import per.san.common.utils.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * description: 表数据
 *
 * @author shencai.huang@hand-china.com
 * @date 12/12/2018 13:26
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/12/2018
 */
public class Table {

    /**
     * 表的名称
     */
    private String tableName;

    /**
     * 数据库引擎
     */
    private String engine;

    /**
     * 表的备注
     */
    private String comments;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 类名(第一个字母大写)，如：sys_users => SysUsers
     */
    private String className;

    /**
     * 是否存在日期类型列
     * 0:不存在  1：存在
     */
    private Integer existDate;

    /**
     * 表的主键
     */
    private Column pk;

    /**
     * 表的列名
     */
    private List<Column> columns;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClassName() {
        return StringUtils.replaceUnderLineAndUpperCase(this.tableName);
    }

    public Integer getExistDate() {
        List<Column> list = this.getColumns();
        if (list != null) {
            for (Column column : list) {
                if (CommonConstant.DATE.equalsIgnoreCase(column.getAttrType())) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public Column getPk() {
        return pk;
    }

    public void setPk(Column pk) {
        this.pk = pk;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}

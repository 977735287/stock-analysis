package per.san.generate.domain;

import per.san.common.SqlToJavaTypeEnum;
import per.san.common.utils.StringUtils;

/**
 * description: 列的属性
 *
 * @author shencai.huang@hand-china.com
 * @date 12/12/2018 13:24
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/12/2018
 */
public class Column {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 列名类型
     */
    private String dataType;

    /**
     * 列名备注
     */
    private String comments;

    /**
     * 主键
     */
    private String columnKey;

    /**
     * 属性名称(第一个字母小写)，如：user_name => userName
     */
    private String attrName;

    /**
     * 属性类型
     */
    private String attrType;

    /**
     * auto_increment
     */
    private String extra;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getAttrName() {
        return StringUtils.replaceUnderLineAndUpperCase(this.columnName);
    }

    public String getAttrType() {
        if(this.dataType != null)
            return SqlToJavaTypeEnum.valueOf(this.dataType.toUpperCase()).getJavaType();
        return null;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}

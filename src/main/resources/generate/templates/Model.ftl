package ${packageName}.domain;

<#if packageName != "per.san.sys">
import per.san.sys.domain.BaseDomain;

</#if>
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
<#if table.existDate == 1>
import java.util.Date;

</#if>
/**
 * description: ${table.comments}
 *
 * @author ${author}
 * @date ${date?string('yyyy-MM-dd hh:mm')}
 * lastUpdateBy: ${author}
 * lastUpdateDate: ${date?string('yyyy-MM-dd hh:mm')}
 */
@Table(name="${table.tableName}")
public class ${table.className} extends BaseDomain {

    /**
    *${table.pk.comments!}
    */
    @Id
    private ${table.pk.attrType} ${table.pk.attrName?uncap_first};

<#if table.columns?exists>
    <#list table.columns as column>
    /**
    *${column.comments!}
    */
    @Column(name = "${column.columnName}",columnDefinition = "${column.dataType?upper_case}")
    private ${column.attrType} ${column.attrName?uncap_first};

    </#list>
</#if>
    public ${table.pk.attrType} get${table.pk.attrName}() {
        return this.${table.pk.attrName?uncap_first};
    }

    public void set${table.pk.attrName}(${table.pk.attrType} ${table.pk.attrName?uncap_first}) {
        this.${table.pk.attrName?uncap_first} = ${table.pk.attrName?uncap_first};
    }

<#if table.columns?exists>
    <#list table.columns as column>
    public ${column.attrType} get${column.attrName}() {
        return this.${column.attrName?uncap_first};
    }

    public void set${column.attrName}(${column.attrType} ${column.attrName?uncap_first}) {
        this.${column.attrName?uncap_first} = ${column.attrName?uncap_first};
    }

    </#list>
</#if>
}

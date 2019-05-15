package per.san.stock.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description:
 *
 * @author sanchar
 * @date 2019-05-13 09:03
 * lastUpdateBy: sanchar
 * lastUpdateDate: 2019-05-13 09:03
 */
@Table(name = "optional_stock")
public class OptionalStock {

    /**
     * id
     */
    @Id
    private Integer id;

    /**
     * code
     */
    @Column(name = "code", columnDefinition = "VARCHAR")
    private String code;

    /**
     * name
     */
    @Column(name = "name", columnDefinition = "VARCHAR")
    private String name;

    /**
     * area
     */
    @Column(name = "area", columnDefinition = "VARCHAR")
    private String area;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

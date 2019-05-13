package per.san.example.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * description:
 *
 * @author sanchar
 * @date 2019-03-31 10:13
 * lastUpdateBy: sanchar
 * lastUpdateDate: 2019-03-31 10:13
 */
@Table(name = "up_down_percent")
public class UpDownPercent {

    /**
     *
     */
    @Id
    private Integer id;

    /**
     * 代码
     */
    @Column(name = "code", columnDefinition = "VARCHAR")
    private String code;

    /**
     * 名称
     */
    @Column(name = "name", columnDefinition = "VARCHAR")
    private String name;

    /**
     * 价格
     */
    @Column(name = "trade", columnDefinition = "DECIMAL")
    private Double trade;

    /**
     * 涨跌额
     */
    @Column(name = "price_change", columnDefinition = "DECIMAL")
    private Double priceChange;

    /**
     * 涨跌幅
     */
    @Column(name = "change_percent", columnDefinition = "VARCHAR")
    private String changePercent;

    /**
     * 昨收
     */
    @Column(name = "settlement", columnDefinition = "DECIMAL")
    private Double settlement;

    /**
     * 今开
     */
    @Column(name = "open", columnDefinition = "DECIMAL")
    private Double open;

    /**
     * 最高
     */
    @Column(name = "high", columnDefinition = "DECIMAL")
    private Double high;

    /**
     * 最低
     */
    @Column(name = "low", columnDefinition = "DECIMAL")
    private Double low;

    /**
     * 成交量/手
     */
    @Column(name = "volume", columnDefinition = "DECIMAL")
    private Double volume;

    /**
     *
     */
    @Column(name = "amount", columnDefinition = "DECIMAL")
    private Double amount;

    /**
     *
     */
    @Column(name = "create_date", columnDefinition = "DATETIME")
    private Date createDate;

    /**
     * 涨跌趋势
     */
    @Column(name = "trend_status", columnDefinition = "VARCHAR")
    private String trendStatus;

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

    public Double getTrade() {
        return this.trade;
    }

    public void setTrade(Double trade) {
        this.trade = trade;
    }

    public Double getPriceChange() {
        return this.priceChange;
    }

    public void setPriceChange(Double priceChange) {
        this.priceChange = priceChange;
    }

    public String getChangePercent() {
        return this.changePercent;
    }

    public void setChangePercent(String changePercent) {
        this.changePercent = changePercent;
    }

    public Double getSettlement() {
        return this.settlement;
    }

    public void setSettlement(Double settlement) {
        this.settlement = settlement;
    }

    public Double getOpen() {
        return this.open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return this.high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return this.low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVolume() {
        return this.volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTrendStatus() {
        return trendStatus;
    }

    public void setTrendStatus(String trendStatus) {
        this.trendStatus = trendStatus;
    }
}

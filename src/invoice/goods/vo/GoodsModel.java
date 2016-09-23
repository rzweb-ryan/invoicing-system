package invoice.goods.vo;

import invoice.goodsType.vo.GoodsTypeModel;
import utils.format.FormatUtil;

/**
 * Created by RZ on 7/5/16.
 */
public class GoodsModel {
    private Long uuid;
    private String name;
    private String origin;
    private String producer;
    private String unit;

    private Double inPrice;
    private Double outPrice;

    private String inPriceView;
    private String outPriceView;

    //
    private GoodsTypeModel gtm;

    public GoodsTypeModel getGtm() {
        return gtm;
    }

    public void setGtm(GoodsTypeModel gtm) {
        this.gtm = gtm;
    }

    public String getInPriceView() {
        return inPriceView;
    }

    public String getOutPriceView() {
        return outPriceView;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getInPrice() {
        return inPrice;
    }

    public void setInPrice(Double inPrice) {
        inPriceView = FormatUtil.formatPrice(inPrice);
        this.inPrice = inPrice;
    }

    public Double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Double outPrice) {
        outPriceView = FormatUtil.formatPrice(outPrice);
        this.outPrice = outPrice;
    }
}

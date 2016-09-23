package invoice.orderdetail.vo;

import invoice.goods.vo.GoodsModel;
import invoice.order.vo.OrderModel;
import invoice.storage.vo.StorageModel;
import utils.format.FormatUtil;

/**
 * Created by RZ on 7/8/16.
 */
public class OrderDetailModel {
    private Long uuid;

    private Integer num;
    private Integer surPlus; //remains
    private Double price;


    private StorageModel sm;

    private String priceView;

    public String getPriceView() {
        return priceView;
    }

    private GoodsModel gm;
    private OrderModel om;

    public StorageModel getSm() {
        return sm;
    }

    public void setSm(StorageModel sm) {
        this.sm = sm;
    }
    public Integer getSurPlus() {
        return surPlus;
    }

    public void setSurPlus(Integer surPlus) {
        this.surPlus = surPlus;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
        priceView = FormatUtil.formatPrice(price);
    }

    public GoodsModel getGm() {
        return gm;
    }

    public void setGm(GoodsModel gm) {
        this.gm = gm;
    }

    public OrderModel getOm() {
        return om;
    }

    public void setOm(OrderModel om) {
        this.om = om;
    }
}

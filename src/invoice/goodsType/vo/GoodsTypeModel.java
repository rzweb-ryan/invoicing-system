package invoice.goodsType.vo;

import invoice.supplier.vo.SupplierModel;

/**
 * Created by RZ on 7/3/16.
 */
public class GoodsTypeModel {
    private Long uuid;
    private String name;
    private SupplierModel sm;

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

    public SupplierModel getSm() {
        return sm;
    }

    public void setSm(SupplierModel sm) {
        this.sm = sm;
    }
}

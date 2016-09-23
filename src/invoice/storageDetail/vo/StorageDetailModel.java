package invoice.storageDetail.vo;

import invoice.goods.vo.GoodsModel;
import invoice.storage.vo.StorageModel;

/**
 * Created by RZ on 7/10/16.
 */
public class StorageDetailModel {

    private Long uuid;
    private Integer num;

    private GoodsModel gm;
    private StorageModel sm;

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

    public GoodsModel getGm() {
        return gm;
    }

    public void setGm(GoodsModel gm) {
        this.gm = gm;
    }

    public StorageModel getSm() {
        return sm;
    }

    public void setSm(StorageModel sm) {
        this.sm = sm;
    }
}

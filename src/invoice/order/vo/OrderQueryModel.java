package invoice.order.vo;

import utils.base.BaseQueryModel;
import utils.format.FormatUtil;

public class OrderQueryModel extends OrderModel implements BaseQueryModel{
	// TODO 添加自定义查询条件
    private Long createTime2;
    private Integer totalNum2;
    private Double totalPrice2;

    private String createTimeView2;

    public String getCreateTimeView2() {
        return createTimeView2;
    }

    public Long getCreateTime2() {
        return createTime2;
    }

    public void setCreateTime2(Long createTime2) {
        createTimeView2= FormatUtil.formatDate(createTime2);
        this.createTime2 = createTime2;
    }

    public Integer getTotalNum2() {
        return totalNum2;
    }

    public void setTotalNum2(Integer totalNum2) {
        this.totalNum2 = totalNum2;
    }

    public Double getTotalPrice2() {
        return totalPrice2;
    }

    public void setTotalPrice2(Double totalPrice2) {
        this.totalPrice2 = totalPrice2;
    }
}

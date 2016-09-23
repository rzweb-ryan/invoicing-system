package invoice.report.vo;

import utils.base.BaseQueryModel;
import utils.format.FormatUtil;

public class ReportQueryModel implements BaseQueryModel{
	// TODO 添加自定义查询条件
    private Integer status;
    private Long supplierUuid;
    private Long beginDate;
    private Long endDate;

    private String beginDateView;
    private String endDateView;

    public String getBeginDateView() {
        return beginDateView;
    }

    public String getEndDateView() {
        return endDateView;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSupplierUuid() {
        return supplierUuid;
    }

    public void setSupplierUuid(Long supplierUuid) {
        this.supplierUuid = supplierUuid;
    }

    public Long getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Long beginDate) {
        beginDateView = FormatUtil.formatDate(beginDate);
        this.beginDate = beginDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        endDateView = FormatUtil.formatDate(endDate);
        this.endDate = endDate;
    }
}

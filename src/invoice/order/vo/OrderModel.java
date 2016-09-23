package invoice.order.vo;

import auth.emp.vo.EmpModel;
import invoice.orderdetail.vo.OrderDetailModel;
import invoice.supplier.vo.SupplierModel;
import utils.format.FormatUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by RZ on 7/6/16.
 */
public class OrderModel {
    public static final Integer ORDER_ORDERTYPE_OF_BUY = 1;
    public static final Integer ORDER_ORDERTYPE_OF_SALE = 2;
    public static final Integer ORDER_ORDERTYPE_OF_RETURN_BUY = 3;
    public static final Integer ORDER_ORDERTYPE_OF_RETURN_SALE = 4;

    public static final String ORDER_ORDERTYPE_OF_BUY_VIEW = "采购";
    public static final String ORDER_ORDERTYPE_OF_SALE_VIEW = "销售";
    public static final String ORDER_ORDERTYPE_OF_RETURN_BUY_VIEW = "采购退货";
    public static final String ORDER_ORDERTYPE_OF_RETURN_SALE_VIEW = "销售退货";

    public static final Integer ORDER_STATUS_OF_BUY_NO_CHECK = 111;
    public static final Integer ORDER_STATUS_OF_BUY_CHECK_PASS = 121;
    public static final Integer ORDER_STATUS_OF_BUY_CHECK_NO_PASS = 120;
    public static final Integer ORDER_STATUS_OF_BUY_BUYING = 131;
    public static final Integer ORDER_STATUS_OF_BUY_IN_STORE = 141;
    public static final Integer ORDER_STATUS_OF_BUY_COMPLETE = 199;

    public static final String ORDER_STATUS_OF_BUY_NO_CHECK_VIEW = "未审核";
    public static final String ORDER_STATUS_OF_BUY_CHECK_PASS_VIEW = "通过";
    public static final String ORDER_STATUS_OF_BUY_CHECK_NO_PASS_VIEW = "驳回";
    public static final String ORDER_STATUS_OF_BUY_BUYING_VIEW = "采购中";
    public static final String ORDER_STATUS_OF_BUY_IN_STORE_VIEW = "入库中";
    public static final String ORDER_STATUS_OF_BUY_COMPLETE_VIEW = "结单";

    public static final Integer ORDER_STATUS_OF_SALE_NO_CHECK = 211;
    public static final Integer ORDER_STATUS_OF_SALE_WAITING = 221;
    public static final Integer ORDER_STATUS_OF_SALE_OUT_STORE = 231;
    public static final Integer ORDER_STATUS_OF_SALE_COMPLETE = 241;

    public static final String ORDER_STATUS_OF_SALE_NO_CHECK_VIEW = "下单完成";
    public static final String ORDER_STATUS_OF_SALE_WAITING_VIEW = "等待出库";
    public static final String ORDER_STATUS_OF_SALE_OUT_STORE_VIEW = "出库中";
    public static final String ORDER_STATUS_OF_SALE_COMPLETE_VIEW = "结单";

    public static final Map<Integer, String> orderTypeMap = new HashMap<Integer, String>();

    public static final Map<Integer, String> buyStatusMap = new TreeMap<Integer, String>();
    public static final Map<Integer, String> saleStatusMap = new TreeMap<Integer, String>();

    private static final Map<Integer, String> typeMap = new HashMap<Integer, String>();

    static{
        orderTypeMap.put(ORDER_ORDERTYPE_OF_BUY, ORDER_ORDERTYPE_OF_BUY_VIEW);
        orderTypeMap.put(ORDER_ORDERTYPE_OF_SALE, ORDER_ORDERTYPE_OF_SALE_VIEW);
        orderTypeMap.put(ORDER_ORDERTYPE_OF_RETURN_BUY, ORDER_ORDERTYPE_OF_RETURN_BUY_VIEW);
        orderTypeMap.put(ORDER_ORDERTYPE_OF_RETURN_SALE, ORDER_ORDERTYPE_OF_RETURN_SALE_VIEW);

        buyStatusMap.put(ORDER_STATUS_OF_BUY_NO_CHECK, ORDER_STATUS_OF_BUY_NO_CHECK_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_CHECK_PASS, ORDER_STATUS_OF_BUY_CHECK_PASS_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_CHECK_NO_PASS, ORDER_STATUS_OF_BUY_CHECK_NO_PASS_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_BUYING, ORDER_STATUS_OF_BUY_BUYING_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_IN_STORE, ORDER_STATUS_OF_BUY_IN_STORE_VIEW);
        buyStatusMap.put(ORDER_STATUS_OF_BUY_COMPLETE, ORDER_STATUS_OF_BUY_COMPLETE_VIEW);

        saleStatusMap.put(ORDER_STATUS_OF_SALE_NO_CHECK, ORDER_STATUS_OF_SALE_NO_CHECK_VIEW);
        saleStatusMap.put(ORDER_STATUS_OF_SALE_WAITING, ORDER_STATUS_OF_SALE_WAITING_VIEW);
        saleStatusMap.put(ORDER_STATUS_OF_SALE_OUT_STORE, ORDER_STATUS_OF_SALE_OUT_STORE_VIEW);
        saleStatusMap.put(ORDER_STATUS_OF_SALE_COMPLETE, ORDER_STATUS_OF_SALE_COMPLETE_VIEW);

        typeMap.putAll(buyStatusMap);
        typeMap.putAll(saleStatusMap);
    }

    private Long uuid;

    private String orderNum;
    private Integer totalNum;

    private Long createTime;
    private Long checkTime;
    private Long endTime;
    private Integer orderType;
    private Integer status;
    private Double totalPrice;

    private String createTimeView;
    private String checkTimeView;
    private String endTimeView;
    private String orderTypeView;
    private String statusView;
    private String totalPriceView;

    private EmpModel creator;
    private EmpModel checker;
    private EmpModel completer;
    private SupplierModel sm;
    //对订单明细一对多
    private Set<OrderDetailModel> odms;

    public Set<OrderDetailModel> getOdms() {
        return odms;
    }
    public void setOdms(Set<OrderDetailModel> odms) {
        this.odms = odms;
    }
    public Long getUuid() {
        return uuid;
    }
    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }
    public String getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
    public Integer getTotalNum() {
        return totalNum;
    }
    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
    public Long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
        this.createTimeView = FormatUtil.formatDate(createTime);
    }
    public Long getCheckTime() {
        return checkTime;
    }
    public void setCheckTime(Long checkTime) {
        this.checkTime = checkTime;
        this.checkTimeView = FormatUtil.formatDate(checkTime);
    }
    public Long getEndTime() {
        return endTime;
    }
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
        this.endTimeView = FormatUtil.formatDate(endTime);
    }
    public Integer getOrderType() {
        return orderType;
    }
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
        this.orderTypeView = orderTypeMap.get(orderType);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
        statusView = buyStatusMap.containsKey(status)?buyStatusMap.get(status):saleStatusMap.get(status);
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        this.totalPriceView = FormatUtil.formatPrice(totalPrice);
    }

    public EmpModel getCreator() {
        return creator;
    }

    public void setCreator(EmpModel creator) {
        this.creator = creator;
    }

    public EmpModel getChecker() {
        return checker;
    }
    public void setChecker(EmpModel checker) {
        this.checker = checker;
    }
    public EmpModel getCompleter() {
        return completer;
    }
    public void setCompleter(EmpModel completer) {
        this.completer = completer;
    }
    public SupplierModel getSm() {
        return sm;
    }
    public void setSm(SupplierModel sm) {
        this.sm = sm;
    }
    public String getCreateTimeView() {
        return createTimeView;
    }
    public String getCheckTimeView() {
        return checkTimeView;
    }
    public String getEndTimeView() {
        return endTimeView;
    }
    public String getOrderTypeView() {
        return orderTypeView;
    }
    public String getStatusView() {
        return statusView;
    }
    public String getTotalPriceView() {
        return totalPriceView;
    }
}

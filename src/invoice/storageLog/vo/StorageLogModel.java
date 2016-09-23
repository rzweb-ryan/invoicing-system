package invoice.storageLog.vo;

import auth.emp.vo.EmpModel;
import invoice.storageDetail.vo.StorageDetailModel;
import utils.format.FormatUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RZ on 7/10/16.
 */
public class StorageLogModel {
    public static Map<Integer,String> typeMap = new HashMap<Integer,String>();
    public static final Integer STORAGE_LOG_TYPE_IN = 0;
    public static final Integer STORAGE_LOG_TYPE_OUT = 1;
    public static final String STORAGE_LOG_TYPE_IN_VIEW ="Storage In";
    public static final String STORAGE_LOG_TYPE_OUT_VIEW ="Storage Out";
    static{
        typeMap.put(STORAGE_LOG_TYPE_IN,STORAGE_LOG_TYPE_IN_VIEW);
        typeMap.put(STORAGE_LOG_TYPE_OUT,STORAGE_LOG_TYPE_OUT_VIEW);
    }

    private Long uuid;
    private Integer num;
    private Integer type;//0buy in 1 sell out
    private Long operTime;

    private String operTimeView;
    private String typeView;

    private EmpModel em;
    private StorageDetailModel sdm;

    public String getOperTimeView() {
        return operTimeView;
    }

    public String getTypeView() {
        return typeView;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        typeView = typeMap.get(type);
        this.type = type;
    }

    public Long getOperTime() {
        return operTime;
    }

    public void setOperTime(Long operTime) {
        operTimeView = FormatUtil.formatDate(operTime);
        this.operTime = operTime;
    }

    public EmpModel getEm() {
        return em;
    }

    public void setEm(EmpModel em) {
        this.em = em;
    }

    public StorageDetailModel getSdm() {
        return sdm;
    }

    public void setSdm(StorageDetailModel sdm) {
        this.sdm = sdm;
    }
}

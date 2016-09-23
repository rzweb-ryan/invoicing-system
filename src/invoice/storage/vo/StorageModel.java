package invoice.storage.vo;

import auth.emp.vo.EmpModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RZ on 7/10/16.
 */
public class StorageModel {
    public static final Integer STORAGE_IS_FULL_YES = 1;
    public static final Integer STORAGE_IS_FULL_NO = 0;
    public static final String STORAGE_IS_FULL_YES_VIEW = "FULL";
    public static final String STORAGE_IS_FULL_NO_VIEW =  "AVAILABLE";

    public static Map<Integer,String> isFullMap = new HashMap<Integer,String>();
    static {
        isFullMap.put(STORAGE_IS_FULL_YES,STORAGE_IS_FULL_YES_VIEW);
        isFullMap.put(STORAGE_IS_FULL_NO,STORAGE_IS_FULL_NO_VIEW);
    }

    private Long uuid;
    private String name;
    private String address;
    private Integer isFull;

    private String isFullView;

    private EmpModel em;//admin


    public String getIsFullView() {
        return isFullView;
    }



    public Integer getIsFull() {
        return isFull;
    }

    public void setIsFull(Integer isFull) {
        this.isFull = isFull;
        isFullView = isFullMap.get(isFull);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public EmpModel getEm() {
        return em;
    }

    public void setEm(EmpModel em) {
        this.em = em;
    }
}

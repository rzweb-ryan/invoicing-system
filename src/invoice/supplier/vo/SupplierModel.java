package invoice.supplier.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by RZ on 7/3/16.
 */
public class SupplierModel {
    public static final Integer SUPPLIER_DELIVERY_PROVIDE_YES = 1;
    public static final Integer SUPPLIER_DELIVERY_PROVIDE_NO = 0;
    public static final String SUPPLIER_DELIVERY_PROVIDE_YES_VIEW = "delivery provide";
    public static final String SUPPLIER_DELIVERY_PROVIDE_NO_VIEW = "no delivery";
    public static Map<Integer,String> deliveryViewMap = new HashMap<Integer,String>();
    static{
        deliveryViewMap.put(SUPPLIER_DELIVERY_PROVIDE_YES,SUPPLIER_DELIVERY_PROVIDE_YES_VIEW);
        deliveryViewMap.put(SUPPLIER_DELIVERY_PROVIDE_NO,SUPPLIER_DELIVERY_PROVIDE_NO_VIEW);
    }

    private Long uuid;
    private String name;
    private String contact;
    private String address;
    private String tel;


    private Integer delivery;
    private String deliveryView;



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        deliveryView = deliveryViewMap.get(delivery);
        this.delivery = delivery;
    }

    public String getDeliveryView() {
        return deliveryView;
    }
}

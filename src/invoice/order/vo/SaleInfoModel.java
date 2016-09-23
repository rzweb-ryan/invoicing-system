package invoice.order.vo;

/**
 * Created by RZ on 7/13/16.
 */
public class SaleInfoModel {
    private Long uuid;
    private String contact;
    private String tel;
    private String address;

    private OrderModel om;

    public OrderModel getOm() {
        return om;
    }

    public void setOm(OrderModel om) {
        this.om = om;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}

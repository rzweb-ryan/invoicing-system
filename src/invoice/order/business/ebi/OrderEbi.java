package invoice.order.business.ebi;

import auth.emp.vo.EmpModel;
import invoice.order.vo.OrderQueryModel;
import invoice.order.vo.SaleInfoModel;
import invoice.orderdetail.vo.OrderDetailModel;
import org.springframework.transaction.annotation.Transactional;

import invoice.order.vo.OrderModel;
import utils.base.BaseEbi;

import java.util.List;

@Transactional
public interface OrderEbi extends BaseEbi<OrderModel>{
    /**
     * save buy items
     * @param om OrderModel
     * @param em EmpModel/creator
     * @param goodsUuids
     * @param nums
     * @param prices
     */
    public void buy(OrderModel om, EmpModel em, Long[] goodsUuids, Integer[] nums, Double[] prices);

    /**
     * approve order by update the status
     * @param om em  order emp
     */
    public void approve(OrderModel om, EmpModel em);

    public void disApprove(OrderModel om, EmpModel login);


    /**
     * transports list, which status is not nocheck nopass
     * @return
     */
    List<OrderModel> getTransportList(OrderQueryModel oqm, Integer pageNum, Integer pageCount);
    Integer getTransportCount(OrderQueryModel oqm);

    /**
     * assignTask completer
     * @param om
     */
    public void assignTask(OrderModel om);


    /**
     * get login user's transport tasks
     *
     * @param oqm
     * @param login
     * @return
     */

    Integer getCountTasks(OrderQueryModel oqm, EmpModel login);
    List<OrderModel> getTasks(OrderQueryModel oqm, EmpModel login, Integer pageNum, Integer pageCount);

    public void endTransportTask(OrderModel om);

    /**
     * store goods method
     * @param storageUuid
     * @param odmUuid orderDetailModel
     * @param inNum in goods numbers
     * @param login emp
     * @return OrderDetailModel
     */
    public OrderDetailModel store(Long storageUuid, Long odmUuid, Integer inNum, EmpModel login);

    /**
     * sell goods
     * @param storageUuids
     * @param goodsUuids
     * @param nums
     * @param prices
     * @param login
     * @param info
     */
    public void sale(Long[] storageUuids, Long[] goodsUuids, Integer[] nums, Double[] prices, EmpModel login, SaleInfoModel info);

    public void out(Long odmUuid, EmpModel login);
}

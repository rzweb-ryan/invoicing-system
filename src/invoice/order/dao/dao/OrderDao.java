package invoice.order.dao.dao;

import auth.emp.vo.EmpModel;
import invoice.order.vo.OrderModel;
import invoice.order.vo.OrderQueryModel;
import utils.base.BaseDao;

import java.util.List;

public interface OrderDao extends BaseDao<OrderModel> {

    Integer getTransportCount(OrderQueryModel oqm, Integer[] notTasks);

    List<OrderModel> getTransportList(OrderQueryModel oqm, Integer pageNum, Integer pageCount, Integer[] notTasks);

    Integer getCountTasks(OrderQueryModel oqm, EmpModel login);

    List<OrderModel> getTasks(OrderQueryModel oqm, EmpModel login, Integer pageNum, Integer pageCount);
}

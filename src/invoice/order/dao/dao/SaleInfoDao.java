package invoice.order.dao.dao;

import auth.emp.vo.EmpModel;
import invoice.order.vo.OrderModel;
import invoice.order.vo.SaleInfoModel;
import invoice.order.vo.SaleInfoQueryModel;
import utils.base.BaseDao;

import java.util.List;

public interface SaleInfoDao extends BaseDao<SaleInfoModel> {

    SaleInfoModel getInfo(OrderModel om);

    public List<SaleInfoModel> getTasks(SaleInfoQueryModel sqm, EmpModel login, Integer pageNum, Integer pageCount);
}

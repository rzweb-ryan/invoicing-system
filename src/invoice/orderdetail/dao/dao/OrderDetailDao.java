package invoice.orderdetail.dao.dao;

import invoice.orderdetail.vo.OrderDetailModel;
import invoice.report.vo.ReportQueryModel;
import utils.base.BaseDao;

import java.util.List;

public interface OrderDetailDao extends BaseDao<OrderDetailModel> {

    public List<OrderDetailModel> getByOrderUuid(Long uuid);

    public List<OrderDetailModel> getGmList(ReportQueryModel rqm, Long gmUuid);
}

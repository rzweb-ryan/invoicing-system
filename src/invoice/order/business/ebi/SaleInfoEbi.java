package invoice.order.business.ebi;

import auth.emp.vo.EmpModel;
import invoice.order.vo.OrderModel;
import invoice.order.vo.SaleInfoQueryModel;
import org.springframework.transaction.annotation.Transactional;

import invoice.order.vo.SaleInfoModel;
import utils.base.BaseEbi;

import java.util.List;

@Transactional
public interface SaleInfoEbi extends BaseEbi<SaleInfoModel>{
    public SaleInfoModel getInfo(OrderModel om);

    public List<SaleInfoModel> getTasks(SaleInfoQueryModel sqm, EmpModel login, Integer pageNum, Integer pageCount);
}

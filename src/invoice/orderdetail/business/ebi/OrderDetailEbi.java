package invoice.orderdetail.business.ebi;

import invoice.report.vo.ReportQueryModel;
import org.springframework.transaction.annotation.Transactional;

import invoice.orderdetail.vo.OrderDetailModel;
import utils.base.BaseEbi;

import java.util.List;

@Transactional
public interface OrderDetailEbi extends BaseEbi<OrderDetailModel>{

    public List<OrderDetailModel> getByOrderUuid(Long uuid);

    public List<OrderDetailModel> getGmList(ReportQueryModel rqm, Long gmUuid);
}

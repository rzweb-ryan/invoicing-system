package invoice.report.dao.dao;

import invoice.report.vo.ReportQueryModel;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import utils.base.BaseDao;

import java.util.List;

public interface ReportDao {

    List<Object[]> getReport(ReportQueryModel rqm);
}

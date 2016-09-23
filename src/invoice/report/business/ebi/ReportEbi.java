package invoice.report.business.ebi;

import invoice.report.vo.ReportQueryModel;
import org.springframework.transaction.annotation.Transactional;

import utils.base.BaseEbi;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Transactional
public interface ReportEbi{
    /**
     * get buy report
     * @param rqm
     * @return
     */
    public List<Object[]> getReport(ReportQueryModel rqm);

    public void writeJFreeChartToOs(OutputStream os, ReportQueryModel rqm) throws Exception;

    public InputStream getWriteExcelStream(ReportQueryModel rqm) throws Exception;
}

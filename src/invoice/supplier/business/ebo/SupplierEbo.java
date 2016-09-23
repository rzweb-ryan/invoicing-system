package invoice.supplier.business.ebo;

import java.io.Serializable;
import java.util.List;

import invoice.supplier.business.ebi.SupplierEbi;
import invoice.supplier.dao.dao.SupplierDao;
import invoice.supplier.vo.SupplierModel;
import utils.base.BaseQueryModel;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("supplierEbi")
public class SupplierEbo implements SupplierEbi{
	@Resource
	private SupplierDao supplierDao;

	public void save(SupplierModel sm) {
		supplierDao.save(sm);
	}

	public void update(SupplierModel sm) {
		supplierDao.update(sm);
	}

	public void delete(SupplierModel sm) {
		supplierDao.delete(sm);
	}

	public SupplierModel getByUuid(Serializable uuid) {
		return supplierDao.get(uuid);
	}

	public List<SupplierModel> getAll() {
		return supplierDao.getAll();
	}

	public List<SupplierModel> getAll(BaseQueryModel qm, Integer pageNum,Integer pageCount) {
		return supplierDao.getAll(qm,pageNum,pageCount);
	}

	public Integer getCount(BaseQueryModel qm) {
		return supplierDao.getCount(qm);
	}

	public List<SupplierModel> getAllUnion() {
		return supplierDao.getAllUnion();
	}

	public List<SupplierModel> getAllUnion2() {
		return supplierDao.getAllUnion2();
	}
}

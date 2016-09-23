package invoice.order.business.ebo;

import auth.emp.vo.EmpModel;
import invoice.goods.vo.GoodsModel;
import invoice.order.business.ebi.OrderEbi;
import invoice.order.dao.dao.OrderDao;
import invoice.order.dao.dao.SaleInfoDao;
import invoice.order.vo.OrderModel;
import invoice.order.vo.OrderQueryModel;
import invoice.order.vo.SaleInfoModel;
import invoice.orderdetail.business.ebi.OrderDetailEbi;
import invoice.orderdetail.vo.OrderDetailModel;
import invoice.storage.vo.StorageModel;
import invoice.storageDetail.dao.dao.StorageDetailDao;
import invoice.storageDetail.vo.StorageDetailModel;
import invoice.storageLog.dao.dao.StorageLogDao;
import invoice.storageLog.vo.StorageLogModel;
import org.springframework.stereotype.Service;
import utils.base.BaseQueryModel;
import utils.exception.AppException;
import utils.num.OrderNumGenerator;

import javax.annotation.Resource;
import javax.persistence.CacheStoreMode;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("orderEbi")
public class OrderEbo implements OrderEbi {
    @Resource
    private OrderDao orderDao;
    @Resource
    private OrderDetailEbi orderDetailEbi;
    @Resource
    private StorageDetailDao storageDetailDao;
    @Resource
    private StorageLogDao storageLogDao;
    @Resource
    private SaleInfoDao saleInfoDao;

    public void save(OrderModel om) {
        orderDao.save(om);
    }

    public void update(OrderModel om) {
        orderDao.update(om);
    }

    public void delete(OrderModel om) {
        orderDao.delete(om);
    }

    public OrderModel getByUuid(Serializable uuid) {
        return orderDao.get(uuid);
    }

    public List<OrderModel> getAll() {
        return orderDao.getAll();
    }

    public List<OrderModel> getAll(BaseQueryModel qm, Integer pageNum, Integer pageCount) {
        return orderDao.getAll(qm, pageNum, pageCount);
    }

    public Integer getCount(BaseQueryModel qm) {
        return orderDao.getCount(qm);
    }

    public void buy(OrderModel om, EmpModel em, Long[] goodsUuids, Integer[] nums, Double[] prices) {
        //TODO
        om.setCreator(em);
        om.setCreateTime(System.currentTimeMillis());
        om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_BUY);
        om.setStatus(OrderModel.ORDER_STATUS_OF_BUY_NO_CHECK);
        om.setOrderNum(OrderNumGenerator.generator());
        Integer totalNum = 0;
        Double totalPrice = 0.0d;
        Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
        for (int i = 0; i < goodsUuids.length; i++) {
            totalNum += nums[i];
            totalPrice += prices[i];
            OrderDetailModel odm = new OrderDetailModel();
            odm.setOm(om);
            GoodsModel gm = new GoodsModel();
            gm.setUuid(goodsUuids[i]);
            odm.setGm(gm);
            odm.setNum(nums[i]);
            odm.setPrice(prices[i]);
            odm.setSurPlus(nums[i]);
            odms.add(odm);
        }
        om.setTotalNum(totalNum);
        om.setTotalPrice(totalPrice);
        om.setOdms(odms);
        orderDao.save(om);
    }

    public void approve(OrderModel om, EmpModel em) {
        //privilege check
        OrderModel temp = orderDao.get(om.getUuid());
        if (!temp.getStatus().equals(OrderModel.ORDER_STATUS_OF_BUY_NO_CHECK)) {
            throw new AppException("illegal operation!");
        }
        temp.setStatus(OrderModel.ORDER_STATUS_OF_BUY_CHECK_PASS);
        temp.setChecker(em);
        temp.setCheckTime(System.currentTimeMillis());
    }

    public void disApprove(OrderModel om, EmpModel em) {
        //privilege check
        OrderModel temp = orderDao.get(om.getUuid());
        if (!temp.getStatus().equals(OrderModel.ORDER_STATUS_OF_BUY_NO_CHECK)) {
            throw new AppException("illegal operation!");
        }
        temp.setStatus(OrderModel.ORDER_STATUS_OF_BUY_CHECK_NO_PASS);
        temp.setChecker(em);
        temp.setCheckTime(System.currentTimeMillis());
    }

    Integer[] notTasks = {
            OrderModel.ORDER_STATUS_OF_BUY_NO_CHECK,
            OrderModel.ORDER_STATUS_OF_BUY_CHECK_NO_PASS
    };

    public List<OrderModel> getTransportList(OrderQueryModel oqm, Integer pageNum, Integer pageCount) {
        return orderDao.getTransportList(oqm, pageNum, pageCount, notTasks);
    }

    public Integer getTransportCount(OrderQueryModel oqm) {
        return orderDao.getTransportCount(oqm, notTasks);
    }

    public void assignTask(OrderModel om) {
        OrderModel temp = orderDao.get(om.getUuid());
        if (!(temp.getStatus().equals(OrderModel.ORDER_STATUS_OF_BUY_CHECK_PASS)||temp.getStatus().equals(OrderModel.ORDER_STATUS_OF_SALE_NO_CHECK))) {
            throw new AppException("illegal operation!");
        }
        temp.setCompleter(om.getCompleter());
        if(temp.getOrderType()==OrderModel.ORDER_ORDERTYPE_OF_BUY){
            temp.setStatus(OrderModel.ORDER_STATUS_OF_BUY_BUYING);
        }else {
            temp.setStatus(OrderModel.ORDER_STATUS_OF_SALE_WAITING);

        }
    }

    public Integer getCountTasks(OrderQueryModel oqm, EmpModel login) {
        return orderDao.getCountTasks(oqm, login);
    }

    public List<OrderModel> getTasks(OrderQueryModel oqm, EmpModel login, Integer pageNum, Integer pageCount) {
        return orderDao.getTasks(oqm, login,pageNum,pageCount);
    }

    public void endTransportTask(OrderModel om) {
        OrderModel temp = orderDao.get(om.getUuid());
        if (!(temp.getStatus().equals(OrderModel.ORDER_STATUS_OF_BUY_BUYING)||temp.getStatus().equals(OrderModel.ORDER_STATUS_OF_SALE_WAITING) )) {
            throw new AppException("illegal operation!");
        }
        if(temp.getStatus().equals(OrderModel.ORDER_STATUS_OF_BUY_BUYING)) {
            temp.setStatus(OrderModel.ORDER_STATUS_OF_BUY_IN_STORE);
        }else {
            temp.setStatus(OrderModel.ORDER_STATUS_OF_SALE_OUT_STORE);
        }
    }

    public OrderDetailModel store(Long storageUuid, Long odmUuid, Integer inNum, EmpModel login) {
        //odm surPlus 减少
        OrderDetailModel odm = orderDetailEbi.getByUuid(odmUuid);
        if (!odm.getOm().getStatus().equals(OrderModel.ORDER_STATUS_OF_BUY_IN_STORE)) {
            throw new AppException("illegal operation!");
        }
        if(odm.getSurPlus()<inNum) {
            throw new AppException("invalid input value of NUMBER!");
        }
        odm.setSurPlus(odm.getSurPlus()-inNum);
        GoodsModel gm = odm.getGm();
        //仓库里增加
        StorageDetailModel sdm = storageDetailDao.getBySmAndGm(storageUuid,gm.getUuid());
        if(sdm!=null) {
            sdm.setNum(sdm.getNum()+inNum);
        }else  {
            sdm = new StorageDetailModel();
            sdm.setGm(gm);
            StorageModel sm = new StorageModel();
            sm.setUuid(storageUuid);
            sdm.setSm(sm);
            sdm.setNum(inNum);
            storageDetailDao.save(sdm);
        }
        //LOG
        StorageLogModel slm = new StorageLogModel();
        slm.setNum(inNum);
        slm.setEm(login);
        slm.setOperTime(System.currentTimeMillis());
        slm.setType(StorageLogModel.STORAGE_LOG_TYPE_IN);
        slm.setSdm(sdm);
        storageLogDao.save(slm);
        OrderModel om = odm.getOm();
        boolean remain = false;
        for (OrderDetailModel orderDetailModel : om.getOdms()) {
            if(orderDetailModel.getSurPlus()==0) {
                continue;
            }else {
                remain = true;
            }
        }
        if(!remain) {
            om.setStatus(OrderModel.ORDER_STATUS_OF_BUY_COMPLETE);
        }
        return odm;
    }

    public void sale(Long[] storageUuids, Long[] goodsUuids, Integer[] nums, Double[] prices, EmpModel login, SaleInfoModel info) {
        //
        OrderModel om = new OrderModel();
        om.setCreator(login);
        om.setCreateTime(System.currentTimeMillis());
        om.setOrderType(OrderModel.ORDER_ORDERTYPE_OF_SALE);
        om.setStatus(OrderModel.ORDER_STATUS_OF_SALE_NO_CHECK);
        om.setOrderNum(OrderNumGenerator.generator());
        Integer totalNum = 0;
        Double totalPrice = 0.0d;
        Set<OrderDetailModel> odms = new HashSet<OrderDetailModel>();
        for (int i = 0; i < goodsUuids.length; i++) {
            totalNum += nums[i];
            totalPrice += prices[i];
            OrderDetailModel odm = new OrderDetailModel();
            odm.setOm(om);

            StorageModel sm = new StorageModel();
            sm.setUuid(storageUuids[i]);
            odm.setSm(sm);

            GoodsModel gm = new GoodsModel();
            gm.setUuid(goodsUuids[i]);
            odm.setGm(gm);
            odm.setNum(nums[i]);
            odm.setPrice(prices[i]);
            odm.setSurPlus(nums[i]);
            odms.add(odm);
        }
        om.setTotalNum(totalNum);
        om.setTotalPrice(totalPrice);
        om.setOdms(odms);
        orderDao.save(om);

        info.setOm(om);
        saleInfoDao.save(info);

    }

    public void out(Long odmUuid, EmpModel login) {
        //odm set surplus = 0;
        OrderDetailModel om = orderDetailEbi.getByUuid(odmUuid);
        Integer surPlus = om.getSurPlus();
        om.setSurPlus(0);
        GoodsModel gm = om.getGm();
        //store minus surplus;
        StorageDetailModel sdm = storageDetailDao.getBySmAndGm(om.getSm().getUuid(),gm.getUuid());
        sdm.setNum(sdm.getNum()-surPlus);
        //storeLog
        StorageLogModel slm = new StorageLogModel();
        slm.setNum(surPlus);
        slm.setEm(login);
        slm.setOperTime(System.currentTimeMillis());
        slm.setType(StorageLogModel.STORAGE_LOG_TYPE_OUT);
        slm.setSdm(sdm);
        storageLogDao.save(slm);
        //order complete
        OrderModel o = om.getOm();
        boolean remain = false;
        for (OrderDetailModel orderDetailModel : o.getOdms()) {
            if(orderDetailModel.getSurPlus()==0) {
                continue;
            }else {
                remain = true;
            }
        }
        if(!remain) {
            o.setStatus(OrderModel.ORDER_STATUS_OF_SALE_COMPLETE);
        }
    }


}

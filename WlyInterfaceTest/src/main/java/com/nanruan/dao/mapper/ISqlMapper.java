package com.nanruan.dao.mapper;

import com.nanruan.model.OrderIndex;
import org.apache.poi.hssf.record.formula.functions.Or;

import java.util.List;

public interface ISqlMapper {
   public List<OrderIndex> queryLongDistance(int companyID);
   public List<OrderIndex> queryLongDistanceCurMonth(int companyID);
   public OrderIndex queryIndexById(int indexID);
   public OrderIndex queryIndexBySupplierOrderID(OrderIndex orderIndex);

}

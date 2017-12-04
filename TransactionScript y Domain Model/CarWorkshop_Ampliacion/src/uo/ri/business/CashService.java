package uo.ri.business;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface CashService {
	Map<String,Object> createInvoiceFor(List<Long> idAveria) throws BusinessException;
	Map <String,Object> findInvoice(Long invoiceID);
	//List<Map<String,Object>> findPayMethodsForInvoice(Long invoiceID);
	void settleInvoice(Long invoiceID,Map<Long,Double> map);
}

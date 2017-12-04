package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.impl.cash.CreateInvoiceFor;
import uo.ri.business.impl.cash.InvoiceFinder;
import uo.ri.business.impl.cash.SettleInvoice;
import uo.ri.common.BusinessException;

public class CashServiceImpl implements CashService {

	@Override
	public Map<String, Object> createInvoiceFor(List<Long> idAveria) throws BusinessException {
		CreateInvoiceFor createInvoiceFor = new CreateInvoiceFor(idAveria);
		return createInvoiceFor.execute();
	}

	@Override
	public Map<String, Object> findInvoice(Long invoiceID) {
		InvoiceFinder invoiceFinder = new InvoiceFinder(invoiceID);
		return invoiceFinder.execute();
	}

//	@Override
//	public List<Map<String, Object>> findPayMethodsForInvoice(Long invoiceID) {
//		PaymentMeansForInvoiceFinder paymentMeansForInvoiceFinder = new PaymentMeansForInvoiceFinder(invoiceID);
//		return paymentMeansForInvoiceFinder.execute();
//	}

	@Override
	public void settleInvoice(Long invoiceID, Map<Long, Double> map) {
		SettleInvoice settleInvoice = new SettleInvoice(invoiceID, map);
		settleInvoice.execute();
	}

}

package business.impl;

import java.awt.event.InvocationEvent;
import java.util.List;
import java.util.Map;

import business.CashService;
import business.impl.cash.CreateInvoiceFor;
import business.impl.cash.InvoiceFinder;
import business.impl.cash.PaymentMeansForInvoiceFinder;
import business.impl.cash.SettleInvoice;
import sun.font.CreatedFontTracker;
import uo.ri.common.BusinessException;

public class CashServiceImpl implements CashService{

	public CashServiceImpl() {
		
	}
	
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
	/*
	@Override
	public List<Map<String, Object>> findPayMethodsForInvoice(Long invoiceID) {
		PaymentMeansForInvoiceFinder paymentMeansForInvoiceFinder = new PaymentMeansForInvoiceFinder(invoiceID);
		return paymentMeansForInvoiceFinder.execute();
	}
	*/

	@Override
	public void settleInvoice(Long invoiceID, Map<Long, Double> map) {
		SettleInvoice settleInvoice = new SettleInvoice(invoiceID, map);
		settleInvoice.execute();
	}

}

package POS_PD;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

public class Sale 
{

	private LocalDateTime dateTime = LocalDateTime.now();
	private Boolean taxFree;
	private ArrayList<Payment> payments;
	private ArrayList<SaleLineItem> saleLineItems;
	private BigDecimal total = new BigDecimal ("0.00");

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public LocalDateTime getDateTime() 
	{
		return this.dateTime;
	}

	public void setDateTime(LocalDateTime dateTime)
	{
		this.dateTime = dateTime;
	}

	public Boolean getTaxFree() 
	{
		return this.taxFree;
	}

	public void setTaxFree(Boolean taxFree) 
	{
		this.taxFree = taxFree;
	}

	public ArrayList<Payment> getPayments() 
	{
		return this.payments;
	}

	public void setPayments(ArrayList<Payment> payments) 
	{
		this.payments = payments;
	}

	public ArrayList<SaleLineItem> getSaleLineItems() 
	{
		return this.saleLineItems;
	}

	public void setSaleLineItems(ArrayList<SaleLineItem> saleLineItems)
	{
		this.saleLineItems = saleLineItems;
	}

	/**
	 * Default constructor of a sale.
	 */
	public Sale()
	{
		payments = new ArrayList<Payment>();
		saleLineItems = new ArrayList<SaleLineItem>();
		setDateTime(dateTime);
	}

	/**
	 * Constructor of a sale.
	 * @param taxFree
	 */
	public Sale(String taxFree) 
	{
		this();
		Boolean isFree = false;
		if(taxFree.equals("Y"))
		{
			isFree = true;
		}
		else if(taxFree.equals("N"))
		{
			isFree = false;
		}
		setTaxFree(isFree);
		setDateTime(dateTime);
	}

	/**
	 * Adds a payment type to a sale.
	 * @param payment
	 */
	public void addPayment(Payment payment) 
	{
		getPayments().add(payment);
	}

	/**
	 * Removes a form of payment from a sale.
	 * @param payment
	 */
	public void removePayment(Payment payment) 
	{
		getPayments().remove(payment);
	}

	/**
	 * Adds a sale line item to a sale.
	 * @param sli
	 */
	public void addSaleLineItem(SaleLineItem sli) 
	{
		getSaleLineItems().add(sli);
	}

	/**
	 * Removes a sale line item from a sale.
	 * @param sli
	 */
	public void removeSaleLineItem(SaleLineItem sli) 
	{
		getSaleLineItems().remove(sli);
	}

	/**
	 * Calculates the total of the sale.
	 */
	public BigDecimal calcTotal() 
	{
		BigDecimal total = new BigDecimal("0.00");
		
		total = calcSubTotal().add(calcTax());
		
		total.setScale(2, RoundingMode.CEILING);
		
		return total;
	}

	/**
	 * Calculates the sub total for a sale.
	 */
	public BigDecimal calcSubTotal() 
	{		
		BigDecimal subTotal = new BigDecimal("0.00");
		for (SaleLineItem i : this.getSaleLineItems())
		{
			subTotal = subTotal.add(i.calcSubTotal());
		}
		return subTotal;
	}

	/**
	 * Calculates the tax on a sale.
	 */
	public BigDecimal calcTax() 
	{
		if(taxFree)
		{
			return new BigDecimal ("0.00");
		}
		
		BigDecimal taxTotal = new BigDecimal("0.00");
		for (SaleLineItem i : this.getSaleLineItems())
		{
			taxTotal = taxTotal.add(i.calcTax());
		}
		taxTotal.setScale(2, RoundingMode.CEILING);
		return taxTotal;
	}

	/**
	 * Gets the total payment for a sale.
	 */
	public BigDecimal getTotalPayment() 
	{
		BigDecimal total = new BigDecimal("0.00");
		
		for(Payment p : getPayments())
		{
			total = total.add(p.getAmount());
		}
		
		return total;
	}

	/**
	 * Checks to see if the payment given is enough for the sale.
	 */
	public Boolean isPaymentEnough() 
	{
		boolean enough = false;
		BigDecimal total = calcTotal();
		int i = total.compareTo(getTotalPayment());
		if(i == -1 || i == 0)
		{
			enough = true;
		}
		return enough;
	}

	/**
	 * Calculates the amount given for the sale based on all payment methods.
	 * @param amtTendered
	 */
	public void calcAmountForPayment(BigDecimal amtTendered) 
	{
		// TODO - implement Sale.calcAmountForPayment
		throw new UnsupportedOperationException();
	}

	/**
	 * Calculates the change that is needed for the sale.
	 */
	public BigDecimal calcChange() 
	{
		BigDecimal change = calcTotal().subtract(calcAmtTendered());
		int i = change.compareTo(new BigDecimal ("0.00"));
		
		if (i == 0)
		{
			return new BigDecimal ("0.00");
		}
		else if (i == 1)
		{
			return new BigDecimal ("0.00");
		}
		else if (i == -1)
		{
			return change.abs();
		}
		return null;
	}

	/**
	 * Calculates the amount tendered for a sale.
	 */
	public BigDecimal calcAmtTendered() 
	{
		BigDecimal total = new BigDecimal("0.00");
		for (Payment p : payments)
		{
			total = total.add(p.getAmtTendered());
		}
		
		return total;
	}

	/**
	 * Returns a string value of the sale.
	 */
	public String toString() 
	{
		String outPut = "";
		
		for(SaleLineItem sli : saleLineItems)
		{
			outPut += sli.toString();
		}

		return "Sale: \n---------\nItems: \n"+outPut+"\nSub Total: $"+calcSubTotal().setScale(2, RoundingMode.CEILING)+"\nTax: $"
		+calcTax().setScale(2, RoundingMode.CEILING)+"\nTotal: $"+calcTotal().setScale(2, RoundingMode.CEILING)+"\nAmount Tendered: $"
		+calcAmtTendered().setScale(2, RoundingMode.CEILING).toPlainString()+"\nChange: $"+calcChange().abs().toPlainString()+"\n---------";
	}

}
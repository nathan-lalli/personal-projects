package POS_PD;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SaleLineItem
{

	private int quantity;
	private Sale sale;
	private Item item;

	public int getQuantity() 
	{
		return this.quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}
	
	public void setQuantity(String quantity) 
	{
		setQuantity(Integer.parseInt(quantity));
	}

	public Sale getSale() 
	{
		return this.sale;
	}

	public void setSale(Sale sale)
	{
		this.sale = sale;
	}

	public Item getItem() 
	{
		return this.item;
	}

	public void setItem(Item item) 
	{
		this.item = item;
	}

	/**
	 * Default constructor for sale line item.
	 */
	public SaleLineItem()
	{

	}

	/**
	 * Constructor for sale line item.
	 * @param sale
	 * @param item
	 * @param quantity
	 */
	public SaleLineItem(Sale sale, Item item, String quantity) 
	{
		setSale(sale);
		setItem(item);
		setQuantity(Integer.parseInt(quantity));
	}

	/**
	 * Calculates the sub total for that line.
	 */
	public BigDecimal calcSubTotal() 
	{
		BigDecimal total = new BigDecimal("0.00");
		total = item.calcAmountForDate(sale.getDateTime().toLocalDate(), getQuantity());
		total.setScale(2, RoundingMode.CEILING);
		return total;
	}

	/**
	 * Calculates the tax for that line.
	 */
	public BigDecimal calcTax() 
	{
		BigDecimal tax = new BigDecimal("0.00");
		tax = calcSubTotal().multiply(item.getTaxRateForDate(sale.getDateTime().toLocalDate()));
		tax.setScale(2, RoundingMode.CEILING);
		return tax;
	}

	/**
	 * Returns a string value for sale line item.
	 */
	public String toString() 
	{
		return item.getNumber()+" "+item.getDescription()+" "+getQuantity()+" "+
				calcSubTotal().setScale(2, RoundingMode.CEILING)+" "+calcTax().setScale(2, RoundingMode.CEILING)+"\n";
	}
}
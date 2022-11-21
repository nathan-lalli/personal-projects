package POS_PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PromoPrice extends Price
{

	private LocalDate endDate;

	public LocalDate getEndDate() 
	{
		return this.endDate;
	}

	public void setEndDate(LocalDate endDate)
	{
		this.endDate = endDate;
	}
	
	public void setEndDate(String text) 
	{
		LocalDate dateFromPattern = LocalDate.parse(text, DateTimeFormatter.ofPattern("M/d/yy"));
		setEndDate(dateFromPattern);		
	}

	/**
	 * The default constructor for the promo Price.
	 */
	public PromoPrice()
	{
		setPrice(new BigDecimal ("0.00"));
		setEffectiveDate("1/1/00");
		setEndDate("1/1/00");
	}

	/**
	 * The constructor for the promo Price.
	 * @param price
	 * @param effectiveDate
	 * @param endDate
	 */
	public PromoPrice(String price, String effectiveDate, String endDate) 
	{
		setPrice(new BigDecimal(price));
		setEffectiveDate(LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yy")));
		setEndDate(LocalDate.parse(endDate, DateTimeFormatter.ofPattern("M/d/yy")));
	}

	/**
	 * Compares the promo price to the price of the item.
	 * @param price
	 */
	public int compareTo(Price price)
	{
		return effectiveDate.compareTo(price.effectiveDate);
	}
	
	/**
	 * The effective date of the price.
	 * @param date
	 */
	public boolean isEffectiveDate(LocalDate date)
	{
		boolean isEffective = false;
		if (effectiveDate.isEqual(date) || effectiveDate.isBefore(date))
		{
			if (date.isEqual(endDate) || date.isBefore(endDate))
			{
				isEffective = true;
			}
		}
		return isEffective;
	}
		

	/**
	 * Returns a string of the promo price.
	 */
	public String toString()
	{
		return "$"+price.toPlainString();
	}

	
}
package POS_PD;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Price implements Comparable<Price> 
{

	protected BigDecimal price;
	protected LocalDate effectiveDate;
	private Item item;

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getEffectiveDate() {
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setEffectiveDate(String text) 
	{
		LocalDate dateFromPattern = LocalDate.parse(text, DateTimeFormatter.ofPattern("M/d/yy"));
		setEffectiveDate(dateFromPattern);		
	}
	
	/**
	 * The default constructor for the price of the item.
	 */
	public Price() 
	{
		
	}

	/**
	 * The price of the item.
	 * @param price
	 * @param effectiveDate
	 * @param item
	 */
	public Price(Item item, String price, String effectiveDate)
	{
        BigDecimal bigDecimalPrice = new BigDecimal(price);
        setPrice(bigDecimalPrice);
        LocalDate dateFromPattern = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/y"));
        setEffectiveDate(dateFromPattern);
        setItem(item);
	}

	/**
	 * The price of the item.
	 * @param price
	 * @param effectiveDate
	 */
	public Price(String price, String effectiveDate)
	{
        BigDecimal bigDecimalPrice = new BigDecimal(price);
        setPrice(bigDecimalPrice);
        LocalDate dateFromPattern = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yy"));
        setEffectiveDate(dateFromPattern);
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
			isEffective = true;
		}
		return isEffective;
	}

	/**
	 * Calculate the amount for the quantity of the item.
	 * @param quantity
	 */
	public BigDecimal calcAmountForQty(int quantity) 
	{
		BigDecimal multAmount = getPrice();
		multAmount = multAmount.multiply(new BigDecimal (quantity));
		multAmount.setScale(2, RoundingMode.HALF_DOWN);
		
		return multAmount;
	}

	/**
	 * Compare the price to the promo price.
	 * @param price
	 */
	public int compareTo(Price price) 
	{
		return effectiveDate.compareTo(price.effectiveDate);
	}

	/**
	 * Returns a string of the price of the item.
	 */
	public String toString() 
	{
		return "$"+price.toPlainString();
	}
}
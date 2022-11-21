package POS_PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaxRate implements Comparable<TaxRate>
{

	private BigDecimal taxRate;
	private LocalDate effectiveDate;

	public BigDecimal getTaxRate() 
	{
		return this.taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) 
	{
		this.taxRate = taxRate;
	}

	public LocalDate getEffectiveDate() 
	{
		return this.effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	/**
	 * Default constructor for a tax rate.
	 */
	public TaxRate()
	{

	}

	/**
	 * Constructor for the tax rate of an item.
	 * @param effectiveDate
	 * @param rate
	 */
	public TaxRate(BigDecimal rate, LocalDate effectiveDate) 
	{
		setEffectiveDate(effectiveDate);
		setTaxRate(rate);
	}

	/**
	 * Checks to see if the tax rate for an item is in effect based on date.
	 * @param date
	 */
	public boolean isEffective(LocalDate date)
	{
		boolean isEffective = false;
		if (effectiveDate.isEqual(date) || effectiveDate.isBefore(date))
		{
			isEffective = true;
		}
		
		return isEffective;
	}

	/**
	 * Compares the tax rate.
	 * @param taxRate
	 * @return 
	 */
	public int compareTo(TaxRate taxRate) 
	{
		return effectiveDate.compareTo(taxRate.effectiveDate);
	}
	
	public BigDecimal toBigDecimal()
	{
		return taxRate;
	}

	public void setEffectiveDate(String text) 
	{
		LocalDate dateFromPattern = LocalDate.parse(text, DateTimeFormatter.ofPattern("M/d/yy"));
		setEffectiveDate(dateFromPattern);		
	}
	
	public String toString()
	{
		return effectiveDate.toString();
	}

}
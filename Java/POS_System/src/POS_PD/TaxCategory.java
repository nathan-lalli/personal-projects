package POS_PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TaxCategory
{

	private String category;
	private TreeSet<TaxRate> taxRates;

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public TreeSet<TaxRate> getTaxRates() {
		return taxRates;
	}

	public void setTaxRates(TreeSet<TaxRate> taxRates) {
		this.taxRates = taxRates;
	}

	/**
	 * Default constructor for a tax category.
	 */
	public TaxCategory()
	{
		taxRates = new TreeSet<TaxRate>();
	}

	/**
	 * Constructor for tax category.
	 * @param category
	 */
	public TaxCategory(String category, String effectiveDate, String rate)
	{
		this();
		setCategory(category);
		LocalDate dateFromPattern = LocalDate.parse(effectiveDate, DateTimeFormatter.ofPattern("M/d/yy"));
		TaxRate taxRate = new TaxRate(new BigDecimal(rate), dateFromPattern);
		addTaxRate(taxRate);
	}

	/**
	 * Gets the tax rate for a given date.
	 * @param date
	 */
	public BigDecimal getTaxRateforDate(LocalDate date) 
	{
		BigDecimal isGood = new BigDecimal ("0.00");
		
		for (TaxRate t : getTaxRates())
		{
			if(t.isEffective(date))
			{
				isGood = t.toBigDecimal();
			}
		}
		return isGood;
		
	}

	/**
	 * Returns a string value of the tax category.
	 */
	public String toString() 
	{
		return category;
	}

	/**
	 * Removes the tax rate for a tax category.
	 * @param taxRate
	 */
	public void removeTaxRate(TaxRate taxRate) 
	{
		getTaxRates().remove(taxRate);
	}
	
	/**
	 * Adds a tax rate to the system.
	 * @param taxRate
	 */
	public void addTaxRate(TaxRate taxRate)
	{
		getTaxRates().add(taxRate);
	}
	
	public boolean isOKToDelete(TaxRate taxRate) {
		if(getTaxRates().isEmpty())
		{
			return true;
		}
		else return false;
	}

}
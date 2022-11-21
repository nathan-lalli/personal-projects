package POS_PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Item
{

	private String number;
	private String description;
	private TaxCategory taxCategory;
	private ArrayList<SaleLineItem> saleLineItem;
	private TreeSet<Price> prices;
	private TreeMap<String,UPC> upcs;


	public String getNumber() 
	{
		return this.number;
	}

	public void setNumber(String number)
	{
		this.number = number;
	}

	public String getDescription()
	{
		return this.description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public ArrayList<SaleLineItem> getSaleLineItem() 
	{
		return this.saleLineItem;
	}

	public void setSaleLineItem(ArrayList<SaleLineItem> saleLineItem) 
	{
		this.saleLineItem = saleLineItem;
	}

	public TreeSet<Price> getPrices()
	{
		return prices;
	}

	public void setPrices(TreeSet<Price> prices)
	{
		this.prices = prices;
	}

	public TreeMap<String,UPC> getUpcs()
	{
		return this.upcs;
	}

	public void setUpcs(TreeMap<String,UPC> upcs)
	{
		this.upcs = upcs;
	}

	public TaxCategory getTaxCategory() 
	{
		return this.taxCategory;
	}

	public void setTaxCategory(TaxCategory taxCategory) 
	{
		this.taxCategory = taxCategory;
	}

	/**
	 * Default constructor for an item.
	 */
	public Item() 
	{
		upcs = new TreeMap<String,UPC>();
		saleLineItem = new ArrayList<SaleLineItem>();
		prices = new TreeSet<Price>();
	}

	/**
	 * Constructor for an item.
	 * @param number
	 * @param description
	 */
	public Item(Store store, String number, String description, String taxCategory) 
	{
		this();
		setNumber(number);
		//setUpcs(upc);
		setDescription(description);
		setTaxCategory(store.findTaxCategorybyName(taxCategory));
		store.addItem(this);
	}

	/**
	 * Adds a price to an  item.
	 * @param price
	 */
	public void addPrice(Price price) 
	{
		getPrices().add(price);
	}

	/**
	 * Removes the price from an item.
	 * @param price
	 */
	public void removePrice(Price price) 
	{
		getPrices().remove(price);
	}

	/**
	 * Adds a UPC to an item.
	 * @param upc
	 */
	public void addUPC(UPC upc)
	{
		getUpcs().put(upc.getUPC(), upc);
	}

	/**
	 * Removes the UPC from an item.
	 * @param upc
	 */
	public void removeUPC(UPC upc) 
	{
		getUpcs().remove(upc.getUPC());
	}

	/**
	 * Gets the price for a certain date, depends on promo price.
	 * @param Date
	 */
	public Price getPriceForDate(LocalDate Date) 
	{
		Price isGood = null;
		
		for (Price p : this.getPrices())
		{
			if (p.isEffectiveDate(Date))
			{
				isGood = p;
			}
		}
		
		return isGood;

	}

	/**
	 * Get the tax rate for a certain day.
	 * @param date
	 */
	public BigDecimal getTaxRateForDate(LocalDate date)
	{
		return taxCategory.getTaxRateforDate(date);
	}

	/**
	 * Calculates the amount on a certain day.
	 * @param date
	 * @param quantity
	 */
	public BigDecimal calcAmountForDate(LocalDate date, int quantity)
	{
		Price price = getPriceForDate(date);
		return price.calcAmountForQty(quantity);
	}

	/**
	 * Returns a string value of an item.
	 */
	public String toString() 
	{
		return "Number: "+number+"     Name: "+description;
		
		/*
		LocalDate now = LocalDate.now();
		return "Item Number: "+number+"\nItem UPC: "+upcs.values()+"\nItem Name: "+description+
				"\nTax Category: "+taxCategory+"\nPrice: "+getPriceForDate(now).toString()+"\n";
				*/
	}

	public boolean isOKToDelete() {
		return true;
	}
}
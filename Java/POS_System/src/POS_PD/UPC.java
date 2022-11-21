package POS_PD;

public class UPC
{

	private String UPC;
	private Item item;

	public String getUPC() {
		return UPC;
	}

	public void setUPC(String UPC) {
		this.UPC = UPC;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Default constructor of the UPC.
	 */
	public UPC() 
	{

	}

	/**
	 * Constructor for the UPC.
	 * @param upc
	 */
	public UPC(String upc, Item item) 
	{
		setUPC(upc);
		setItem(item);
	}

	/**
	 * Returns a string value for the UPC.
	 */
	public String toString()
	{
			return UPC;
	}

}
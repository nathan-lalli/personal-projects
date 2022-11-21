package POS_PD;

import java.math.BigDecimal;

public class CashDrawer 
{

	private BigDecimal cashAmount;
	private BigDecimal startingAmount;
	private int position;

	public BigDecimal getCashAmount() 
	{
		return cashAmount;
	}

	public void setCashAmount(BigDecimal cashAmount) 
	{
		this.cashAmount = cashAmount;
	}

	public BigDecimal getStartingAmount() {
		return startingAmount;
	}

	public void setStartingAmount(BigDecimal startingAmount) {
		this.startingAmount = startingAmount;
	}

	
	public int getPosition()
	{
		return this.position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	/**
	 * Default constructor for the cash drawer.
	 */
	public CashDrawer() 
	{
		setPosition(0);
	}
	
	public CashDrawer (BigDecimal amount)
	{
		setPosition(0);
		setCashAmount(amount);
		setStartingAmount(amount);
	}

	/**
	 * Removes cash from the cash drawer.
	 * @param cash
	 */
	public void removeCash(BigDecimal cash) 
	{
		this.cashAmount = this.cashAmount.subtract(cash);
	}

	/**
	 * Adds cash to the cash drawer.
	 * @param cash
	 */
	public void addCash(BigDecimal cash) 
	{
		this.cashAmount = this.cashAmount.add(cash);
	}

	/**
	 * Returns a string value for the cash drawer.
	 */
	public String toString() 
	{
		String position = "";
		
		if (getPosition() == 1)
		{
			position = "Open";
		}
		else if (getPosition() == 0)
		{
			position = "Closed";
		}
		else position = null;
		
		return cashAmount.toString()+"\nRegister is: "+position;
	}

}
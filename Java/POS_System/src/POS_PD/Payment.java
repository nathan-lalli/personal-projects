package POS_PD;

import java.math.BigDecimal;

public class Payment 
{

	private BigDecimal amount;
	private  BigDecimal amtTendered;

	public BigDecimal getAmount() 
	{
		return this.amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public BigDecimal getAmtTendered() 
	{
		return this.amtTendered;
	}

	public void setAmtTendered(BigDecimal amtTendered)
	{
		this.amtTendered = amtTendered;
	}

	/**
	 * Boolean to count the payment as cash.
	 */
	public Boolean countAsCash() 
	{
		return null;
	}
}
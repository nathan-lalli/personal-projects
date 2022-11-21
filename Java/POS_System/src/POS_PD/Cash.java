package POS_PD;

import java.math.BigDecimal;

public class Cash extends Payment
{

	/**
	 * Default constructor for cash.
	 */
	public Cash() 
	{
		
	}

	/**
	 * Constructor for cash.
	 * @param amount
	 * @param amtTendered
	 */
	public Cash(String amount, BigDecimal amtTendered)
	{
		setAmount(new BigDecimal(amount));
		setAmtTendered(amtTendered);
	}

	/**
	 * Boolean counting the payment as cash.
	 */
	public Boolean countsAsCash() 
	{
		return true;
	}

	/**
	 * Returns a string value of cash.
	 */
	public String toString() 
	{
		return "\nAmount: "+getAmount()+"\nAmount Tendered: "+getAmtTendered();
	}

}
package POS_PD;

import java.math.BigDecimal;

public class Check extends AuthorizedPayemnt
{

	private String routingNumber;
	private String accountNumber;
	private String checkNumber;
	private String amount;

	public String getRoutingNumber() 
	{
		return this.routingNumber;
	}

	public void setRoutingNumber(String routingNumber)
	{
		this.routingNumber = routingNumber;
	}

	public String getAccountNumber()
	{
		return this.accountNumber;
	}

	public void setAccountNumber(String accountNumber) 
	{
		this.accountNumber = accountNumber;
	}

	public String getCheckNumber()
	{
		return this.checkNumber;
	}

	public void setCheckNumber(String checkNumber) 
	{
		this.checkNumber = checkNumber;
	}
	
	public String getCheckAmount()
	{
		return this.amount;
	}
	
	public void setCheckAmount(String amount)
	{
		this.amount = amount;
	}

	/**
	 * Default constructor of a check.
	 */
	public Check() 
	{

	}

	/**
	 * Constructor for a check.
	 * @param amount
	 * @param accountNumber
	 * @param checkNumber
	 */
	public Check(String amount, String routingNumber, String accountNumber, String checkNumber, BigDecimal amtTendered) 
	{
		setAmount(new BigDecimal (amount));
		setRoutingNumber(routingNumber);
		setAccountNumber(accountNumber);
		setCheckNumber(checkNumber);
		setAmtTendered(amtTendered);
	}

	/**
	 * Checks to see if the check is authorized.
	 */
	public Boolean isAuthorized()
	{
		return true;
	}

	public Boolean countsAsCash() 
	{
		return true;
	}

	
	/**
	 * Returns a string value of the check.
	 */
	public String toString() 
	{
		return "\nAmount: "+getCheckAmount()+"\nRouting Number: "+getRoutingNumber()+"\nAccount Number: "+getAccountNumber()+"\nCheck Number: "+getCheckNumber();
	}

}
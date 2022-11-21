package POS_PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Credit extends AuthorizedPayemnt 
{

	private String cardType;
	private String acctNumber;
	private LocalDate expireDate;

	public String getCardType()
	{
		return this.cardType;
	}

	public void setCardType(String cardType) 
	{
		this.cardType = cardType;
	}

	public String getAcctNumber() 
	{
		return this.acctNumber;
	}

	public void setAcctNumber(String acctNumber)
	{
		this.acctNumber = acctNumber;
	}

	public LocalDate getExpireDate()
	{
		return this.expireDate;
	}

	public void setExpireDate(LocalDate expireDate) 
	{
		this.expireDate = expireDate;
	}

	/**
	 * Default constructor.
	 */
	public Credit() 
	{
		
	}

	/**
	 * Constructor for a credit card.
	 * @param cardType
	 * @param acctNumber
	 * @param expireDate
	 */
	public Credit(String cardType, String acctNumber, String expireDate, BigDecimal amtTendered, String amount)
	{
		setCardType(cardType);
		setAcctNumber(acctNumber);
		setExpireDate(LocalDate.parse(expireDate, DateTimeFormatter.ofPattern("M/d/yy")));
		setAmtTendered(amtTendered);
		setAmount(new BigDecimal (amount));
	}

	/**
	 * Determines whether the card is authorized or not.
	 */
	public Boolean isAuthorized()
	{
		return true;
	}

	/**
	 * Returns a string value for card info.
	 */
	public String toString() 
	{
		return "\nCard Type: "+getCardType()+"\nAccount Number: "+getAcctNumber()+"\nExpiration Date: "+getExpireDate();
	}

}
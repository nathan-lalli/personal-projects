package POS_PD;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;

public class Session 
{

	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Register register;
	private Cashier cashier;
	private BigDecimal endingCash;
	private ArrayList<Sale> sales;
	private BigDecimal cashDiff = new BigDecimal("0.00");

	public Register getRegister() 
	{
		return this.register;
	}

	public void setRegister(Register register) 
	{
		this.register = register;
	}

	public BigDecimal getEndingCash() {
		return endingCash;
	}

	public void setEndingCash(BigDecimal endingCash) {
		this.endingCash = endingCash;
	}
	
	public LocalDateTime getStartDateTime() 
	{
		return this.startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime)
	{
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime()
	{
		return this.endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime)
	{
		this.endDateTime = endDateTime;
	}

	public Cashier getCashier()
	{
		return this.cashier;
	}

	public void setCashier(Cashier cashier)
	{
		this.cashier = cashier;
	}

	public ArrayList<Sale> getSales()
	{
		return sales;
	}

	public void setSales(ArrayList<Sale> sales)
	{
		this.sales = sales;
	}

	public BigDecimal getCashDiff() {
		return cashDiff;
	}

	public void setCashDiff(BigDecimal cashDiff) {
		this.cashDiff = cashDiff;
	}
	
	/**
	 * Default constructor for a session.
	 */
	public Session() 
	{
		setStartDateTime(startDateTime.now());
		sales = new ArrayList<Sale>();
		setEndingCash(new BigDecimal("0.00"));
	}

	/**
	 * Constructor for a session.
	 * @param cashier
	 * @param register
	 */
	public Session(Cashier cashier, Register register) 
	{
		this();
		setStartDateTime(startDateTime.now());
		setCashier(cashier);
		setRegister(register);
		sales = new ArrayList<Sale>();
		setEndingCash(new BigDecimal("0.00"));
		
		cashier.addSession(this);
		register.addSession(this);
	}

	/**
	 * Adds a sale to a session.
	 * @param sale
	 */
	public void addSale(Sale sale) 
	{
		getSales().add(sale);
	}

	/**
	 * Removes a sale from a session.
	 * @param sale
	 */
	public void removeSale(Sale sale)
	{
		getSales().remove(sale);
	}

	/**
	 * Calculates the cash difference in the session.
	 * @param cash
	 */
	public BigDecimal calcCashCountDiff() 
	{

		BigDecimal cashDiff = register.getCashDrawer().getCashAmount();
		
		if (cashDiff == null)
		{
			cashDiff = new BigDecimal("0.00");
		}
		
		cashDiff = cashDiff.subtract(getEndingCash());
		
		return cashDiff;
	}

	/**
	 * Returns a string value for a session.
	 */
	public String toString()
	{		
		LocalDateTime dateTime = getStartDateTime();
        String sales = "";
        for(Sale sale : getSales())
        {
            sales += ""+sale.toString()+"\n";

        }
        
        return "---------\nSession:\n---------\nCashier: "+getCashier().getPerson().getName() +"\nRegister: "
        +getRegister().getNumber()+"\nDate: "+dateTime.getDayOfWeek()+" "+dateTime.toLocalDate().toString()+""
        +"\nTime: "+dateTime.toLocalTime().toString()+ "\nTotal: "+calcTotal().setScale(2, RoundingMode.HALF_DOWN).toPlainString()+"\n---------\n"+sales;
    }
	
	public BigDecimal calcTotal()
	{
		BigDecimal total = new BigDecimal("0.00");
		for (Sale sale : getSales())
		{
			total = total.add(sale.calcTotal());
		}
		total.setScale(2, RoundingMode.HALF_DOWN);
		return total;
	}
}




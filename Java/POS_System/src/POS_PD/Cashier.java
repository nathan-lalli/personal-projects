package POS_PD;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Cashier
{

	private String number;
	private String password;
	private Person person;
	private ArrayList<Session> sessions;

	public String getNumber()
	{
		return number;
	}

	public void setNumber(String number) 
	{
		this.number = number;
	}

	public String getPassword()
	{
		return this.password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Person getPerson()
	{
		return this.person;
	}

	public void setPerson(Person person)
	{
		this.person = person;
	}

	public ArrayList<Session> getSessions() 
	{
		return this.sessions;
	}

	public void setSessions(ArrayList<Session> sessions) 
	{
		this.sessions = sessions;
	}

	/**
	 * Default constructor for a cashier.
	 */
	public Cashier() 
	{
		setNumber("");
		setPassword("");
		sessions = new ArrayList<Session>();
	}

	/**
	 * Constructor for a cashier.
	 * @param number
	 * @param person
	 * @param password
	 */
	public Cashier(String number, Person person, String password) 
	{
		setNumber(number);
		setPerson(person);
		setPassword(password);
		sessions = new ArrayList<Session>();
	}

	/**
	 * Adds a session for a cashier.
	 * @param session
	 */
	public void addSession(Session session)
	{
		getSessions().add(session);
	}

	/**
	 * Removes a session for a cashier.
	 * @param session
	 */
	public void removeSession(Session session)
	{
		getSessions().remove(session);
	}

	/**
	 * Checks to make sure that the cashier is authorized for a session.
	 * @param password
	 */
	public Boolean isAuthorized(String password) 
	{
		// TODO - implement Cashier.isAuthorized
		throw new UnsupportedOperationException();
	}

	public boolean isOKToDelete()
	{
		if(getSessions().isEmpty())
		{
			return true;
		}
		else return false;
	}
	
	/**
	 * Returns a string value for a cashier.
	 */
	public String toString()
	{
		return number+" "+person.getName().toString();
	}

	public String toString2()
	{
		return person.getName().toString();
	}

	public BigDecimal cashierDollarSales(LocalDate date) {
		BigDecimal dollarSales = new BigDecimal("0.00");
		
		for(Session s : getSessions())
		{
			for(Sale sale : s.getSales())
			{
				if(sale.getDateTime().toLocalDate().equals(date))
				{
					dollarSales = dollarSales.add(sale.getTotalPayment());
				}
				
			}
		}
		
		return dollarSales;
	}

	public BigDecimal calcTotalDiff(LocalDate date) {
		BigDecimal totalDiff = new BigDecimal ("0.00");
		
		for(Session s : getSessions())
		{
			if(s.getStartDateTime().toLocalDate().equals(date))
			{
				totalDiff = totalDiff.add(s.getCashDiff());
			}
		}
		
		return totalDiff;
	}

	public int calcNumberSales(LocalDate date) {
		int numberSales = 0;
		
		for(Session s : getSessions())
		{
			for(Sale sale : s.getSales())
			{
				if(sale.getDateTime().toLocalDate().equals(date))
				{
					numberSales++;
				}
				
			}
		}
		
		return numberSales;
	}
	
}
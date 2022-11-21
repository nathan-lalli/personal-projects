package POS_PD;

import java.util.ArrayList;

public class Register
{

	private String number;
	private CashDrawer cashDrawer;
	private ArrayList<Session> sessions;

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public CashDrawer getCashDrawer() {
		return this.cashDrawer;
	}

	public void setCashDrawer(CashDrawer cashDrawer) {
		this.cashDrawer = cashDrawer;
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
	 * Default constructor for the register.
	 */
	public Register() 
	{
		sessions = new ArrayList<Session>();
	}

	/**
	 * Constructor for the register.
	 * @param number
	 */
	public Register(String number) 
	{
		setNumber(number);
		this.cashDrawer = new CashDrawer();
		sessions = new ArrayList<Session>();
	}
	
	/**
	 * Adds a session for a register.
	 * @param session
	 */
	public void addSession(Session session)
	{
		getSessions().add(session);
	}

	/**
	 * Removes a session for a register.
	 * @param session
	 */
	public void removeSession(Session session)
	{
		getSessions().remove(session);
	}
	
	public boolean isOKToDelete() {
		if(getSessions().isEmpty())
		{
			return true;
		}
		else return false;
	}

	/**
	 * Returns a string value for a register.
	 */
	
	public String toString() 
	{
		return number;
	}
}
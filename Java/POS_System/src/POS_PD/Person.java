package POS_PD;

public class Person 
{

	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String SSN;
	private Cashier cashier;

	public String getName() 
	{
		return this.name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getAddress()
	{
		return this.address;
	}

	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getCity() 
	{
		return this.city;
	}

	public void setCity(String city) 
	{
		this.city = city;
	}

	public String getState()
	{
		return this.state;
	}

	public void setState(String state) 
	{
		this.state = state;
	}

	public String getZip() 
	{
		return this.zip;
	}

	public void setZip(String zip) 
	{
		this.zip = zip;
	}

	public String getPhone()
	{
		return this.phone;
	}

	public void setPhone(String phone) 
	{
		this.phone = phone;
	}

	public String getSSN() 
	{
		return this.SSN;
	}

	public void setSSN(String SSN)
	{
		this.SSN = SSN;
	}

	public Cashier getCashier() {
		return this.cashier;
	}

	public void setCashier(Cashier cashier) 
	{
		this.cashier = cashier;
	}

	/**
	 * Default constructor of a person.
	 */
	public Person()
	{
		setName("");
		setAddress("");
		setCity("");
		setState("");
		setZip("");
		setPhone("");
		setSSN("");
	}

	/**
	 * Constructor of a person.
	 * @param name
	 * @param address
	 * @param city
	 * @param zip
	 * @param phone
	 * @param sSN
	 */
	public Person(String name, String address, String city, String state, String zip, String phone, String SSN)
	{
		setName(name);
		setAddress(address);
		setCity(city);
		setState(state);
		setZip(zip);
		setPhone(phone);
		setSSN(SSN);
	}

	/**
	 * Returns a string value of a person.
	 */
	public String toString()
	{
		return "\nName: "+name+"\nAddress: "+address+"\nCity: "+city+"\nState: "+state+
				"\nZip Code: "+zip+"\nPhone Number: "+phone+"\nSocial Security Number: "+SSN;
	}

}
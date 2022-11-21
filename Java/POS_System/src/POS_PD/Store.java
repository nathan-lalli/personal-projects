package POS_PD;

import java.math.BigDecimal;
import java.util.*;
import POS_DM.StoreDM;

public class Store 
{

	private String number;
	private String name;
	private TreeMap<String,Cashier> cashiers;
	private ArrayList<Session> sessions;
	private TreeMap<String,UPC> upcs;
	private TreeMap<String,Item> items;
	private TreeMap<String,Register> registers;
	private TreeMap<String,TaxCategory> taxCategory;

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public TreeMap<String,Cashier> getCashiers() {
		return cashiers;
	}

	public void setCashiers(TreeMap<String, Cashier> cashiers) {
		this.cashiers = cashiers;
	}

	public ArrayList<Session> getSessions() {
		return sessions;
	}

	public void setSessions(ArrayList<Session> sessions) {
		this.sessions = sessions;
	}

	public TreeMap<String, UPC> getUpcs() {
		return upcs;
	}

	public void setUpcs(TreeMap<String, UPC> upcs) {
		this.upcs = upcs;
	}

	public TreeMap<String,Item> getItems() {
		return items;
	}

	public void setItems(TreeMap<String, Item> items) {
		this.items = items;
	}

	public TreeMap<String, Register> getRegisters() {
		return registers;
	}

	public void setRegisters(TreeMap<String, Register> registers) {
		this.registers = registers;
	}

	public TreeMap<String, TaxCategory> getTaxCategory() {
		return taxCategory;
	}

	public void setTaxCategory(TreeMap<String, TaxCategory> taxCategory) {
		this.taxCategory = taxCategory;
	}

	/**
	 * Default constructor for a store.
	 */
	public Store()
	{
		taxCategory = new TreeMap<String,TaxCategory>();
		sessions = new ArrayList<Session>();
		items = new TreeMap<String,Item>();
		upcs = new TreeMap<String,UPC>();
		registers = new TreeMap<String,Register>();
		cashiers = new TreeMap<String,Cashier>();
	}

	/**
	 * Constructor for a store.
	 * @param number
	 * @param name
	 */
	public Store(String number, String name) 
	{
		this();
		setNumber(number);
		setName(name);
	}

	public void openStore()
	{
		StoreDM.loadStore(this);
	}
	
	/**
	 * Adds an item to the store.
	 * @param item
	 */
	public void addItem(Item item) 
	{
		getItems().put(item.getNumber(), item);
	}

	/**
	 * Removes an item from the store.
	 * @param item
	 */
	public void removeItem(Item item) 
	{
		getItems().remove(item.getNumber(), item);
	}

	/**
	 * Adds a UPC to the store.
	 * @param upc
	 */
	public void addUPC(UPC upc) 
	{
		getUpcs().put(upc.getUPC(), upc);
	}
	
	/**
	 * Removes a UPC from the store
	 * @param upc
	 */
	public void removeUPC(UPC upc)
	{
		getUpcs().remove(upc.getUPC());
	}

	/**
	 * Adds a register to the store.
	 * @param register
	 */
	public void addRegister(Register register) 
	{
		getRegisters().put(register.getNumber(), register);
	}

	/**
	 * Removes a register from the store.
	 * @param register
	 */
	public void removeRegister(Register register) 
	{
		getRegisters().remove(register.getNumber(), register);
	}

	/**
	 * Adds a cashier to the store.
	 * @param chasier
	 */
	public void addCashier(Cashier cashier) 
	{
		getCashiers().put(cashier.getNumber(), cashier);
	}

	/**
	 * Removes a cashier from the store.
	 * @param cashier
	 */
	public void removeCashier(Cashier cashier) 
	{
		getCashiers().remove(cashier.getNumber(), cashier);
	}

	/**
	 * Adds a tax category to the store.
	 * @param taxCategory
	 */
	public void addTaxCategory(TaxCategory taxCategory) 
	{
		getTaxCategory().put(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * Removes a tax category from the store.
	 * @param taxCategory
	 */
	public void removeTaxCategory(TaxCategory taxCategory) 
	{
		getTaxCategory().remove(taxCategory.getCategory(), taxCategory);
	}

	/**
	 * Adds a session to the store.
	 * @param session
	 */
	public void addSession(Session session) 
	{
		session.getRegister().getCashDrawer().setCashAmount(new BigDecimal ("0.00"));
		getSessions().add(session);
	}

	/**
	 * Removes a session from the store.
	 * @param session
	 */
	public void removeSession(Session session)
	{
		getSessions().remove(session);
	}

	/**
	 * Finds a register based on the number it has.
	 * @param number
	 */
	public Register findRegisterbyNumber(String number) 
	{
		if (number.length() > 0)
		{
			return registers.get(number);
		}
		else return null;
	}

	/**
	 * Finds an item based on its UPC.
	 * @param upc
	 */
	public Item findItemForUPC(String upc) 
	{

		if (upc.length() > 0)
		{
			for (UPC u : getUpcs().values())
			{
				if(u.toString().equals(upc))
				{
					return u.getItem();
				}
			}
			return null;
		}
		else return null;
	}

	/**
	 * Finds an item based on a number.
	 * @param number
	 */
	public Item findItemForNumber(String number) 
	{
		if (number.length() > 0)
		{
			return items.get(number);
		}
		else return null;
	}

	/**
	 * Finds cashier based on a number.
	 * @param number
	 */
	public Cashier findCashierForNumber(String number) 
	{
		if (number.length() > 0)
		{
			return cashiers.get(number);
		}
		else return null;
	}

	/**
	 * Finds a tax category based on a number.
	 * @param taxCategory2
	 */
	public TaxCategory findTaxCategorybyName(String taxCategory2) 
	{
		if (taxCategory2.length() > 0)
		{
			return taxCategory.get(taxCategory2);
		}
		else return null;
	}

	/**
	 * Returns a string value of the store.
	 */
	public String toString() 
	{
		return "\nStore number: " +number+ "\nStore name: " +name+ "\n";
		
		/*String list = "";
		for (Item itemList : items.values()) 
		{
			list += itemList.toString();
		}
		return list;*/
	}

	public boolean isOKToDelete(TaxCategory taxCat) {
		return true;
	}

}
package POS_Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import POS_PD.*;

public class StoreTest 
{	
 	public static void printStore(Store Store)
	{
		System.out.println(Store);
		System.out.println("===============");
		System.out.println("Cashiers");
		System.out.println("===============");
		
		for (Cashier cashier : Store.getCashiers().values())
		{
			System.out.println(cashier.getPerson().getName());
		}
		
		System.out.println("===============");
		System.out.println("Registers");
		System.out.println("===============");
		
		for (Register register : Store.getRegisters().values())
		{
			System.out.println(register.getNumber());
		}
		
		System.out.println("===============");
		System.out.println("Items");
		System.out.println("===============");
		
		for (Item i : Store.getItems().values())
		{
			System.out.println(i.toString());
		}
		
		System.out.println("===============");
		System.out.println("Sessions");
		System.out.println("===============");
		
		for (Session session : Store.getSessions())
		{
			System.out.println(session.toString());
		}
	}
	
	public static void ac_1_1_Test(Store store)
	{
		Item item;
		UPC upc;
		Price price;
		
		System.out.println("\n************");
		System.out.println("POS AC 1.1");
		System.out.println("************");
		
		
		TaxCategory taxCat = new TaxCategory("Food","01/01/2020", "0.08");
		store.addTaxCategory(taxCat);
		
		//Add Item 1
		item = new Item(store,"0001","Peanut M&M's","Food");
		upc = new UPC("1111111111", item);
		item.addUPC(upc);
		store.addUPC(upc);
		price = new Price(item,"3.29","1/01/20");
		item.addPrice(price);
		
		//Add Item 2
		item = new Item(store, "0002","Skittles","Food");
		upc = new UPC("2222222222", item);
		item.addUPC(upc);
		price = new Price(item,"3.14","01/01/20");
		item.addPrice(price);
		
		//Add Item 3
		item = new Item(store,"0003","Snickers","Food");
		upc = new UPC("3333333333", item);
		item.addUPC(upc);
		price = new Price(item,"3.04", "01/01/20");
		item.addPrice(price);
		
		//Printing out data
		System.out.println(store.toString());
		
		for (Item i : store.getItems().values())
		{
			System.out.println(i.toString());
		}
		
	}
	
	public static void ac_1_2_Test(Store store)
	{
		Cashier cashier;
		Person person;
		
		System.out.println("\n************");
		System.out.println("POS AC 1.2");
		System.out.println("************");
		
		//Add cashier 1
		person = new Person("Nathan Lalli","100 N St","Edmond","OK","73013","405-000-0000","000-00-0000");
		cashier = new Cashier("01",person,"Password1");
		store.addCashier(cashier);
		
		//Add cashier 2
		person = new Person("Peyton Powell","101 N St","Edmond","OK","73013","405-111-1111","111-11-1111");
		cashier = new Cashier("02",person,"Password2");
		store.addCashier(cashier);
		
		//Add cashier 3
		person = new Person("Logan Demaray","102 N St","Edmond","OK","73013","405-222-2222","222-22-2222");
		cashier = new Cashier("03",person,"Password3");
		store.addCashier(cashier);
		
		for(Cashier c : store.getCashiers().values())
		{
			System.out.println(c.toString());
		}
	}
	
	public static void ac_1_3_Test(Store store)
	{
		Register register;
		
		System.out.println("\n************");
		System.out.println("POS AC 1.3");
		System.out.println("************");
		
		//Add register 1
		register = new Register("01");
		store.addRegister(register);
		
		//Add register 2
		register = new Register("02");
		store.addRegister(register);
		
		for (Register r : store.getRegisters().values())
		{
			System.out.println(r.toString());
		}
		
	}
	
	public static void ac_1_4_Test(Store store)
	{
		Session session;
		Register register;
		Cashier cashier;
		Person person;
		Sale sale;
		SaleLineItem saleLineItem;
		SaleLineItem saleLineItem2;
		Item item;
		Item item2;
		UPC upc;
		Price price;
		TaxCategory taxCategory;
		LocalDateTime now;
		
		now = LocalDateTime.now();
		
		//Add Tax Category
		taxCategory = new TaxCategory("Food","01/01/20", "0.08");
		store.addTaxCategory(taxCategory);
		
		//Add Register
		register = new Register("01");
		store.addRegister(register);
		
		//Add Cashier
		person = new Person("Nathan Lalli","100 N St","Edmond","OK","73013","405-000-0000","000-00-0000");
		cashier = new Cashier("01",person,"Password1");
		store.addCashier(cashier);
		
		//Add Item 1
		item = new Item(store,"0001","Peanut M&M's","Food");
		upc = new UPC("1111111111", item);
		item.addUPC(upc);
		store.addUPC(upc);
		price = new Price(item,"3.29","1/01/20");
		item.addPrice(price);
		
		//Add Item 2
		item2 = new Item(store, "0002","Skittles","Food");
		upc = new UPC("2222222222", item);
		item2.addUPC(upc);
		price = new Price(item2,"3.14","01/01/20");
		item2.addPrice(price);
		
		System.out.println("\n************");
		System.out.println("POS AC 1.4");
		System.out.println("************");
		
		session = new Session(cashier,register);
		session.setStartDateTime(now);
		//session = new Session();
		//session.setCashier(cashier);
		//session.setRegister(register);

		sale = new Sale("N");
		sale.setDateTime(now);
		
		saleLineItem = new SaleLineItem(sale,item,"1");
		sale.addSaleLineItem(saleLineItem);
		saleLineItem2 = new SaleLineItem(sale,item2,"2");
		sale.addSaleLineItem(saleLineItem2);
		session.addSale(sale);
		
		store.addSession(session);
		
		for(Session s : store.getSessions())
		{
			System.out.println(s.toString());
		}
		
		for(SaleLineItem sli : sale.getSaleLineItems())
		{
			System.out.println(sli.toString());
		}
	}
}
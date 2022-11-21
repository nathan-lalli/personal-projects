package POS_DM;

import POS_PD.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class StoreDM 
{
	public static void loadStore(Store store)
	{
		String fileName ="StoreData.csv";
		String line = null;
		String dataType; 
		TaxCategory taxCategory;
		Person person;
		Cashier cashier;
		Register register;
		Item item;
		Sale sale = null;
		UPC upc;
		Price price;
		Session session = null;
		SaleLineItem saleLineItem;
		Payment payment;
		Cash cash;
		Check check;
		Credit credit;
		LocalDateTime now;
		PromoPrice promoPrice;
		
		now = LocalDateTime.now();
		
	    try {
	        // FileReader reads text files in the default encoding.
	        FileReader fileReader =  new FileReader(fileName);

	        // Always wrap FileReader in BufferedReader.
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
	        
	        while((line = bufferedReader.readLine()) != null) 
	        {
	          String[] token = line.split(",");
	          dataType = token[0];
	          if (dataType.contentEquals("Store"))
	          {
	        	  store.setNumber(token[1]);
	        	  store.setName(token[2]);
	          }
	          else if (dataType.contentEquals("TaxCategory"))
	          {
	        	  taxCategory = new TaxCategory(token[1], token[3], token[2]);
	        	  store.addTaxCategory(taxCategory);
	          }
	          else if (dataType.contentEquals("Cashier"))
	          {
	        	  person = new Person(token[2], token[4], token[5], token[6], token[7], token[8], token[3]);
	        	  cashier = new Cashier(token[1], person, token[9]);
	        	  store.addCashier(cashier);
	          }
	          else if (dataType.contentEquals("Register"))
	          {
	        	  register = new Register(token[1]);
	        	  store.addRegister(register);
	          }
	          else if (dataType.contentEquals("Item"))
	          {
	        	  item = new Item(store, token[1], token[3], token[4]);
	        	  upc = new UPC(token[2], item);
	        	  item.addUPC(upc);
	        	  store.addUPC(upc);
	        	  price = new Price(item, token[5], token[6]);
	        	  item.addPrice(price);
	        	  
	        	  if (token.length > 7)
	        	  {
	        		  promoPrice = new PromoPrice(token[7],token[8],token[9]);
	        		  item.addPrice(promoPrice);
	        	  }
	        	  store.addItem(item);
	          }
	          else if (dataType.contentEquals("Session"))
	          {
	        	  session = new Session(store.findCashierForNumber(token[1]),store.findRegisterbyNumber(token[2]));
	        	  session.setStartDateTime(now);
	        	  session.getRegister().setCashDrawer(new CashDrawer());
	        	  store.addSession(session);
	          }
	          else if (dataType.contentEquals("Sale"))
	          {
	        	  sale = new Sale(token[1]);
	        	  sale.setDateTime(now);
	          }
	          else if (dataType.contentEquals("SaleLineItem"))
	          {
	        	  saleLineItem = new SaleLineItem(sale,store.findItemForNumber(token[1]),token[2]);
	        	  sale.addSaleLineItem(saleLineItem);
	          }
	          else if (dataType.contentEquals("Payment"))
	          {
	        	  if (token[1].contentEquals("Cash"))
	        	  {
	        		  cash = new Cash(token[2],new BigDecimal (token[3]));
	        		  sale.addPayment(cash);
	        	  }
	        	  else if(token[1].contentEquals("Credit"))
	        	  {
	        		  credit = new Credit(token[4],token[5],token[6],new BigDecimal(token[3]),token[2]);
	        		  sale.addPayment(credit);
	        	  }
	        	  else if(token[1].contentEquals("Check"))
	        	  {
	        		  check = new Check(token[2],token[4],token[5],token[6],new BigDecimal(token[3]));
	        		  sale.addPayment(check);
	        	  }
	        	  session.addSale(sale);
	          }
	        }    
	      // Always close files.
	      bufferedReader.close();            
	    }
	    catch(FileNotFoundException ex) 
	    {
	      System.out.println( "Unable to open file '" +  fileName + "'");                
	    }
	    catch(IOException ex) 
	    {
	       System.out.println (  "Error reading file '" + fileName + "'");   	
	     }
	   }
}

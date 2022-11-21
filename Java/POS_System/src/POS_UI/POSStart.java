package POS_UI;

import POS_PD.*;
import POS_DM.*;
import POS_Test.StoreTest;

public class POSStart
{
	public static Store myStore;
	
	public static void main(String[] args)
	{
		//Run with test set 1
		//myStore = new Store("1","Nathan's store");
		//System.out.println("=================================");
		//System.out.println("\nReady to test Store: "+myStore);
		//System.out.println("=================================");
		//storeTest.ac_1_1_Test(myStore);
		//storeTest.ac_1_2_Test(myStore);
		//storeTest.ac_1_3_Test(myStore);
		//storeTest.ac_1_4_Test(myStore);
		//storeTest.printStore(myStore);
		
		//Run with Test	
		//myStore = new Store();
		//System.out.println("Ready to open Store");
		//myStore.openStore();
		//storeTest.printStore(myStore);
		//System.out.println("Store Open: "+myStore.getName());
		
		//Run with UI
		myStore = new Store();
		myStore.openStore();
		POS_Frame.open(myStore);
	}
}
package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POS_PD.*;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.RoundingMode;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Sale_Entry_Panel extends JPanel {
	private JTextField item_Field;
	private JTextField quantity_Field;
	private JTextField subTotal_Field;
	private JTextField tax_Field;
	private JTextField total_Field;
	private JTextField amtTendered_Field;
	private JTextField change_Field;
	SaleLineItem saleLineItem;
	Item item;
	DefaultListModel<SaleLineItem> itemList;
	JList<SaleLineItem> list;
	Boolean taxFree;
	JLabel error_Message;
	JLabel not_Enough_Money;

	/**
	 * Create the panel.
	 */
	public Sale_Entry_Panel(JFrame currentFrame, Store store, Session session, Sale sale, Boolean isTaxFree, Boolean error) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		sale.setTaxFree(isTaxFree);
		
		JLabel lblNewLabel = new JLabel("Cashier:");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(26, 28, 65, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Register:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(26, 53, 65, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Item:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(26, 145, 48, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity:");
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setBounds(172, 145, 86, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sale");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setBounds(227, 39, 54, 28);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("SubTotal:");
		lblNewLabel_5.setForeground(new Color(255, 255, 255));
		lblNewLabel_5.setBounds(304, 177, 65, 14);
		add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Tax:");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setBounds(304, 206, 48, 14);
		add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Total:");
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setBounds(304, 244, 48, 14);
		add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Amt Tendered:");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setBounds(296, 294, 90, 14);
		add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Change:");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setBounds(296, 341, 73, 14);
		add(lblNewLabel_9);
		
		item_Field = new JTextField();
		item_Field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean addQuant = false;
				item = store.findItemForUPC(item_Field.getText());
				if(item != null)
				{
					for(SaleLineItem sli : sale.getSaleLineItems())
					{
						if(sli.getItem().getDescription().equals(item.getDescription()))
						{
							addQuant = true;
							int temp = sli.getQuantity();
							int quant = temp + Integer.parseInt(quantity_Field.getText());
							sli.setQuantity(quant);
							
							item_Field.setText("");
							currentFrame.getContentPane().removeAll();
							currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, sale, taxFree, false));
							currentFrame.getContentPane().revalidate();
							currentFrame.getContentPane().repaint();
						}
						else
						{
							addQuant = false;
						}
					}
					if(!addQuant)
					{
						saleLineItem = new SaleLineItem(sale, item, quantity_Field.getText());
						sale.addSaleLineItem(saleLineItem);
						item_Field.setText("");
						currentFrame.getContentPane().removeAll();
						currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, sale, taxFree, false));
						currentFrame.getContentPane().revalidate();
						currentFrame.getContentPane().repaint();
					}
				}
				else
				{					
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, sale, taxFree, true));
					currentFrame.getContentPane().revalidate();
					currentFrame.getContentPane().repaint();
				}
			}
		});
		item_Field.setBackground(new Color(192, 192, 192));
		item_Field.setBounds(66, 142, 96, 20);
		add(item_Field);
		item_Field.setColumns(10);
		
		quantity_Field = new JTextField("1");
		quantity_Field.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item = store.findItemForUPC(item_Field.getText());
				if(item != null)
				{
					saleLineItem = new SaleLineItem(sale, item, quantity_Field.getText());
					sale.addSaleLineItem(saleLineItem);
					item_Field.setText("");
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, sale, taxFree, false));
					currentFrame.getContentPane().revalidate();
					currentFrame.getContentPane().repaint();
				}
				else
				{					
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, sale, taxFree, true));
					currentFrame.getContentPane().revalidate();
					currentFrame.getContentPane().repaint();
				}
			}
		});
		quantity_Field.setBackground(new Color(192, 192, 192));
		quantity_Field.setBounds(244, 142, 96, 20);
		add(quantity_Field);
		quantity_Field.setColumns(10);		
		
		String subTotal = "";
		if (sale.calcSubTotal().toString() != null)
		{
			subTotal = sale.calcSubTotal().setScale(2, RoundingMode.CEILING).toString();
		}
		subTotal_Field = new JTextField(subTotal);
		subTotal_Field.setBackground(new Color(192, 192, 192));
		subTotal_Field.setBounds(386, 174, 96, 20);
		add(subTotal_Field);
		subTotal_Field.setColumns(10);
		
		String tax = "";
		if (sale.calcTax().toString() != null)
		{
			tax = sale.calcTax().setScale(2, RoundingMode.CEILING).toString();
		}
		tax_Field = new JTextField(tax);
		tax_Field.setBackground(new Color(192, 192, 192));
		tax_Field.setBounds(386, 203, 96, 20);
		add(tax_Field);
		tax_Field.setColumns(10);
		
		String total = "";
		if (sale.calcTotal().toString() != null)
		{
			total = sale.calcTotal().setScale(2, RoundingMode.CEILING).toString();
		}
		total_Field = new JTextField(total);
		total_Field.setBackground(new Color(192, 192, 192));
		total_Field.setBounds(386, 241, 96, 20);
		add(total_Field);
		total_Field.setColumns(10);
		
		String amtTendered = "";
		if (sale.calcAmtTendered().toString() != null)
		{
			amtTendered = sale.calcAmtTendered().setScale(2, RoundingMode.CEILING).toString();
		}		
		amtTendered_Field = new JTextField(amtTendered);
		amtTendered_Field.setBackground(new Color(192, 192, 192));
		amtTendered_Field.setBounds(386, 288, 96, 20);
		add(amtTendered_Field);
		amtTendered_Field.setColumns(10);
		
		String change = "";
		if (sale.calcChange().toString() != null)
		{
			change = sale.calcChange().setScale(2, RoundingMode.CEILING).toString();
		}
		change_Field = new JTextField(change);
		change_Field.setBackground(new Color(192, 192, 192));
		change_Field.setBounds(386, 335, 96, 20);
		add(change_Field);
		change_Field.setColumns(10);
		
		
		itemList = new DefaultListModel<SaleLineItem>();
		
		for (SaleLineItem sli : sale.getSaleLineItems())
		{
			itemList.addElement(sli);
		}
		
		list = new JList<SaleLineItem>(itemList);
		list.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				itemList = new DefaultListModel<SaleLineItem>();
				
				for (SaleLineItem sli : sale.getSaleLineItems())
				{
					itemList.addElement(sli);
				}
				list.setModel(itemList);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		list.setBackground(new Color(192, 192, 192));
		list.setBounds(36, 170, 255, 115);
		add(list);
		
		JButton complete_Button = new JButton("Complete Sale");
		complete_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cmp = sale.calcAmtTendered().compareTo(sale.calcTotal());
				
				if(cmp == 0)
				{
					session.getRegister().getCashDrawer().removeCash(sale.calcChange().setScale(2, RoundingMode.CEILING));
					sale.setTotal(sale.calcTotal());
					session.addSale(sale);
					
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, new Sale("N"), false, false));
					currentFrame.getContentPane().revalidate();
				}
				else if(cmp == 1)
				{
					session.getRegister().getCashDrawer().removeCash(sale.calcChange().setScale(2, RoundingMode.CEILING));
					sale.setTotal(sale.calcTotal());
					session.addSale(sale);
					
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, new Sale("N"), false, false));
					currentFrame.getContentPane().revalidate();
				}
				else if(cmp == -1)
				{
					not_Enough_Money.setVisible(true);
				}
			}
		});
		complete_Button.setBackground(new Color(192, 192, 192));
		complete_Button.setBounds(149, 314, 125, 23);
		add(complete_Button);
		
		JButton payment_Button = new JButton("Payment");
		payment_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Payment_Panel(currentFrame, store, sale, session, taxFree, false, new Payment()));
				currentFrame.getContentPane().revalidate();
			}
		});
		payment_Button.setBackground(new Color(192, 192, 192));
		payment_Button.setBounds(14, 314, 125, 23);
		add(payment_Button);
		
		JButton cancel_Button = new JButton("Cancel Sale");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, new Sale("N"), false, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		cancel_Button.setBackground(new Color(192, 192, 192));
		cancel_Button.setBounds(14, 348, 125, 23);
		add(cancel_Button); 
		
		JButton end_Button = new JButton("End Session");
		end_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean isSale = false;
				
				for(Sale s : session.getSales())
				{
					isSale = true;
				}
				
				if(isSale)
				{
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new End_Session_Panel(currentFrame, store, session, new String(""), new String("")));
					currentFrame.getContentPane().revalidate();
				}
				else
				{
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POS_Home_Panel(store));
					currentFrame.getContentPane().revalidate();
				}

			}
		});
		end_Button.setBackground(new Color(192, 192, 192));
		end_Button.setBounds(149, 348, 125, 23);
		add(end_Button);
		
		JCheckBox taxFree_Check = new JCheckBox("Tax Free");
		taxFree_Check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(taxFree_Check.isSelected())
				{
					sale.setTaxFree(isTaxFree);
					taxFree = true;
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, sale, taxFree, false));
					currentFrame.getContentPane().revalidate();
					currentFrame.getContentPane().repaint();
				}
				else
				{
					sale.setTaxFree(isTaxFree);
					taxFree = false;
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new Sale_Entry_Panel(currentFrame, store, session, sale, taxFree, false));
					currentFrame.getContentPane().revalidate();
					currentFrame.getContentPane().repaint();
				}
			}
		});
		taxFree_Check.setBackground(new Color(128, 0, 0));
		taxFree_Check.setForeground(new Color(255, 255, 255));
		taxFree_Check.setBounds(355, 87, 99, 23);
		if(isTaxFree)
		{
			taxFree_Check.setSelected(true);
			taxFree = true;
			sale.setTaxFree(isTaxFree);
		}
		else
		{
			taxFree_Check.setSelected(false);
			taxFree = false;
			sale.setTaxFree(isTaxFree);
		}
		add(taxFree_Check);
		
		JLabel cashier_Label = new JLabel(session.getCashier().toString2());
		cashier_Label.setForeground(new Color(255, 255, 255));
		cashier_Label.setBounds(84, 28, 96, 14);
		add(cashier_Label);
		
		JLabel register_Label = new JLabel(session.getRegister().toString());
		register_Label.setForeground(new Color(255, 255, 255));
		register_Label.setBounds(84, 53, 96, 14);
		add(register_Label);
		
		error_Message = new JLabel("Invalid Item!");
		error_Message.setForeground(new Color(255, 0, 0));
		error_Message.setBounds(66, 120, 89, 14);
		error_Message.setVisible(error);
		add(error_Message);
		
		not_Enough_Money = new JLabel("Not Enough Money Given!");
		not_Enough_Money.setForeground(Color.RED);
		not_Enough_Money.setBounds(306, 372, 169, 14);
		not_Enough_Money.setVisible(false);
		add(not_Enough_Money);

	}
}

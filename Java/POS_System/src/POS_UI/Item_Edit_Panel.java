package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import POS_PD.*;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.JComboBox;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Item_Edit_Panel extends JPanel {
	private JTextField number_Text;
	private JTextField description_Text;
	JList<Price> price_List;
	JList<UPC> UPC_List;
	DefaultListModel<Price> PriceList;
	DefaultListModel<UPC> UPCList;
	JButton price_Delete;
	JButton price_Edit;
	JButton UPC_Delete;
	JButton UPC_Edit;

	/**
	 * Create the panel.
	 */
	public Item_Edit_Panel(JFrame currentFrame, Store store, Item item, Boolean isAdd) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		JPanel currentPanel = this;
		
		JLabel lblNewLabel = new JLabel("Edit Item");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(226, 13, 71, 14);
		add(lblNewLabel);
		
		UPCList = new DefaultListModel<UPC>();
		
		for (UPC u : item.getUpcs().values())
		{
			UPCList.addElement(u);
		}
		
		UPC_List = new JList<UPC>(UPCList);
		UPC_List.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!UPC_List.isSelectionEmpty())
				{
					UPC_Delete.setEnabled(true);
					UPC_Edit.setEnabled(true);
				}
				else
				{
					UPC_Delete.setEnabled(false);
					UPC_Edit.setEnabled(false);
				}
			}
		});
		UPC_List.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				UPCList = new DefaultListModel<UPC>();
				
				for (UPC u : item.getUpcs().values())
				{
					UPCList.addElement(u);
				}
				UPC_List.setModel(UPCList);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		UPC_List.setBackground(new Color(192, 192, 192));
		UPC_List.setBounds(320, 38, 162, 91);
		add(UPC_List);
		
		PriceList = new DefaultListModel<Price>();
		
		for (Price p : item.getPrices())
		{
			PriceList.addElement(p);
		}
		
		price_List = new JList<Price>();
		price_List.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!price_List.isSelectionEmpty())
				{
					price_Delete.setEnabled(true);
					price_Edit.setEnabled(true);
				}
				else
				{
					price_Delete.setEnabled(false);
					price_Edit.setEnabled(false);
				}
			}
		});
		price_List.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				PriceList = new DefaultListModel<Price>();
				
				for (Price p : item.getPrices())
				{
					PriceList.addElement(p);
				}
				price_List.setModel(PriceList);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		price_List.setBackground(new Color(192, 192, 192));
		price_List.setBounds(320, 229, 162, 91);
		add(price_List);
		
		JButton price_Add = new JButton("Add");
		price_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Price_Edit_Panel(currentFrame, currentPanel, store, item, new Price(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		price_Add.setBackground(new Color(192, 192, 192));
		price_Add.setBounds(298, 331, 85, 23);
		add(price_Add);
		
		price_Edit = new JButton("Edit");
		price_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Price priceSelected = price_List.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Price_Edit_Panel(currentFrame, currentPanel, store, item, priceSelected, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		price_Edit.setBackground(new Color(192, 192, 192));
		price_Edit.setBounds(419, 331, 85, 23);
		price_Edit.setEnabled(false);
		add(price_Edit);
		
		price_Delete = new JButton("Delete");
		price_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Price priceSelected = price_List.getSelectedValue();
				item.removePrice(priceSelected);
				PriceList.removeElement(priceSelected);
			}
		});
		price_Delete.setBackground(new Color(192, 192, 192));
		price_Delete.setBounds(360, 365, 85, 23);
		price_Delete.setEnabled(false);
		add(price_Delete);
		
		JButton UPC_Add = new JButton("Add");
		UPC_Add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPC_Edit_Panel(currentFrame, currentPanel, store, item, new UPC(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		UPC_Add.setBackground(new Color(192, 192, 192));
		UPC_Add.setBounds(298, 140, 85, 23);
		add(UPC_Add);
		
		UPC_Edit = new JButton("Edit");
		UPC_Edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UPC UPCSelected = UPC_List.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPC_Edit_Panel(currentFrame, currentPanel, store, item, UPCSelected, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		UPC_Edit.setBackground(new Color(192, 192, 192));
		UPC_Edit.setBounds(419, 140, 85, 23);
		UPC_Edit.setEnabled(false);
		add(UPC_Edit);
		
		UPC_Delete = new JButton("Delete");
		UPC_Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UPC UPCSelected = UPC_List.getSelectedValue();
				item.removeUPC(UPCSelected);
				UPCList.removeElement(UPCSelected);
			}
		});
		UPC_Delete.setBackground(new Color(192, 192, 192));
		UPC_Delete.setBounds(360, 174, 85, 23);
		UPC_Delete.setEnabled(false);
		add(UPC_Delete);
		
		JLabel number_Label = new JLabel("Item Number:");
		number_Label.setForeground(new Color(255, 255, 255));
		number_Label.setBackground(new Color(255, 255, 255));
		number_Label.setBounds(25, 92, 85, 14);
		add(number_Label);
		
		String itemNum = "";
		if (item.getNumber() != null)
		{
			itemNum = item.getNumber();
		}
		number_Text = new JTextField(itemNum);
		number_Text.setBackground(new Color(192, 192, 192));
		number_Text.setBounds(120, 89, 86, 20);
		add(number_Text);
		number_Text.setColumns(10);
		
		JLabel desc_Label = new JLabel("Description:");
		desc_Label.setForeground(new Color(255, 255, 255));
		desc_Label.setBackground(new Color(255, 255, 255));
		desc_Label.setBounds(25, 133, 85, 14);
		add(desc_Label);
		
		JLabel taxCat_Label = new JLabel("Tax Category:");
		taxCat_Label.setForeground(new Color(255, 255, 255));
		taxCat_Label.setBackground(new Color(255, 255, 255));
		taxCat_Label.setBounds(25, 178, 85, 14);
		add(taxCat_Label);
		
		String itemDesc = "";
		if (item.getDescription() != null)
		{
			itemDesc = item.getDescription();
		}
		description_Text = new JTextField(itemDesc);
		description_Text.setBackground(new Color(192, 192, 192));
		description_Text.setBounds(120, 130, 86, 20);
		add(description_Text);
		description_Text.setColumns(10);
		
		DefaultComboBoxModel<TaxCategory> tcList = new DefaultComboBoxModel<TaxCategory>();
		
		for (TaxCategory t : store.getTaxCategory().values())
		{
			tcList.addElement(t);
		}
		
		JComboBox<TaxCategory> comboBox = new JComboBox<TaxCategory>(tcList);
		
		if(!isAdd)
		{
			comboBox.setSelectedItem(item.getTaxCategory());
		}
		
		comboBox.setBackground(new Color(192, 192, 192));
		comboBox.setBounds(120, 174, 86, 22);
		add(comboBox);
		
		JButton save_Button = new JButton("Save");
		save_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!isAdd && !item.getNumber().equals(number_Text.getText()))
				{
					store.removeItem(item);
					item.setNumber(number_Text.getText());
					store.addItem(item);
				}
				
				if(isAdd)
				{
					item.setNumber(number_Text.getText());
					store.addItem(item);
				}
				
				item.setDescription(description_Text.getText());
				item.setTaxCategory((TaxCategory) comboBox.getSelectedItem());
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Item_Selection_Panel(currentFrame, store, item));
				currentFrame.getContentPane().revalidate();
			}
		});
		save_Button.setBackground(new Color(192, 192, 192));
		save_Button.setBounds(30, 331, 89, 23);
		add(save_Button);
		
		JButton cancel_Button = new JButton("Cancel");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Item_Selection_Panel(currentFrame, store, item));
				currentFrame.getContentPane().revalidate();
			}
		});
		cancel_Button.setBackground(new Color(192, 192, 192));
		cancel_Button.setBounds(162, 331, 89, 23);
		add(cancel_Button);
		
		JLabel lblNewLabel_1 = new JLabel("UPC List");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(378, 13, 67, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Price List");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(380, 208, 65, 14);
		add(lblNewLabel_2);

	}
}

package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import POS_PD.*;
import java.awt.Color;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Tax_Category_Edit_Panel extends JPanel {
	private JTextField textField;
	JButton delete_Button;
	JButton edit_Button;
	JList<TaxRate> list;
	DefaultListModel<TaxRate> taxRates;

	/**
	 * Create the panel.
	 * @param taxCategory
	 */
	public Tax_Category_Edit_Panel(JFrame currentFrame, TaxCategory taxCategory, Store store, Boolean isAdd) {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
				taxRates = new DefaultListModel<TaxRate>();
				
				for (TaxRate taxRate : taxCategory.getTaxRates())
				{
					taxRates.addElement(taxRate);
				}
				list.setModel(taxRates);
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		}); 
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		JPanel currentPanel = this;
		
		JLabel lblNewLabel = new JLabel("Tax Category Edit");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(157, 37, 135, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Category:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(23, 102, 68, 14);
		add(lblNewLabel_1);
		
		textField = new JTextField(taxCategory.getCategory());
		textField.setBackground(new Color(192, 192, 192));
		textField.setBounds(139, 99, 96, 20);
		add(textField);
		textField.setColumns(10);
		
		taxRates = new DefaultListModel<TaxRate>();
		
		for (TaxRate taxRate : taxCategory.getTaxRates())
		{
			taxRates.addElement(taxRate);
		}
		
		list = new JList<TaxRate>(taxRates);
		list.setBackground(new Color(192, 192, 192));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (!list.isSelectionEmpty())
					{
						delete_Button.setEnabled(true);
						edit_Button.setEnabled(true);
					}
					else
					{
						delete_Button.setEnabled(false);
						edit_Button.setEnabled(false);
					}
			}
		});
		list.setBounds(278, 127, 186, 138);
		add(list);
		
		JLabel lblNewLabel_2 = new JLabel("Tax Rates");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(345, 102, 68, 14);
		add(lblNewLabel_2);
		
		JButton save_Button = new JButton("Save");
		save_Button.setBackground(new Color(192, 192, 192));
		save_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.setCategory(textField.getText());
				
				if(isAdd)
				{
					store.addTaxCategory(taxCategory);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Tax_Category_Selection_Panel(currentFrame, taxCategory, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		save_Button.setBounds(97, 335, 89, 23);
		add(save_Button);
		
		JButton cancel_Button = new JButton("Cancel");
		cancel_Button.setBackground(new Color(192, 192, 192));
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Tax_Category_Selection_Panel(currentFrame, taxCategory, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		cancel_Button.setBounds(335, 335, 89, 23);
		add(cancel_Button);
		
		JButton add_Button = new JButton("Add");
		add_Button.setBackground(new Color(192, 192, 192));
		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Tax_Rate_Edit_Panel(currentFrame, currentPanel, taxCategory, new TaxRate(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		add_Button.setBounds(234, 278, 80, 20);
		add(add_Button);
		
		edit_Button = new JButton("Edit");
		edit_Button.setBackground(new Color(192, 192, 192));
		edit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxRate taxRate = list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Tax_Rate_Edit_Panel(currentFrame, currentPanel, taxCategory, taxRate, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		edit_Button.setBounds(335, 278, 80, 20);
		edit_Button.setBackground(new Color(192, 192, 192));
		edit_Button.setEnabled(false);
		add(edit_Button);
		
		delete_Button = new JButton("Delete");
		delete_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxRate delRate = list.getSelectedValue();				
				taxCategory.removeTaxRate(delRate);
				taxRates.removeElement(delRate);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		delete_Button.setBackground(new Color(192, 192, 192));
		delete_Button.setBounds(434, 278, 80, 20);
		delete_Button.setEnabled(false);
		add(delete_Button);

	}
}

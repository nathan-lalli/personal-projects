package POS_UI;

import javax.swing.JPanel;

import POS_PD.*;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Tax_Category_Selection_Panel extends JPanel {
	JButton edit_Button;
	JButton delete_Button;
	
	/**
	 * Create the panel.
	 */
	public Tax_Category_Selection_Panel(JFrame currentFrame, TaxCategory taxCategory, Store store) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tax Categories");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(210, 11, 94, 14);
		add(lblNewLabel);
		
		DefaultListModel<TaxCategory> taxCats = new DefaultListModel<TaxCategory>();
		
		for (TaxCategory t : store.getTaxCategory().values())
		{
			taxCats.addElement(t);
		}
		
		JList<TaxCategory> list = new JList<TaxCategory>(taxCats);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {				
				
				if (!list.isSelectionEmpty())
				{
					edit_Button.setEnabled(true);
					if(store.isOKToDelete(list.getSelectedValue()))
					{
						delete_Button.setEnabled(true);
					}
					else
					{
						delete_Button.setEnabled(false);
					}
				}
			} 
		});
		list.setBackground(new Color(192, 192, 192));
		list.setBounds(77, 70, 356, 225);
		add(list);
		
		JButton add_Button = new JButton(" Add");
		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Tax_Category_Edit_Panel(currentFrame, new TaxCategory(), store, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		add_Button.setBackground(new Color(192, 192, 192));
		add_Button.setBounds(31, 342, 89, 23);
		add(add_Button);
		
		edit_Button = new JButton("Edit");
		edit_Button.setBackground(new Color(192, 192, 192));
		edit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategory taxCat = list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Tax_Category_Edit_Panel(currentFrame, taxCat, store, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		edit_Button.setBounds(210, 342, 89, 23);
		edit_Button.setEnabled(false);
		add(edit_Button);
		
		delete_Button = new JButton("Delete");
		delete_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TaxCategory taxCat = list.getSelectedValue();
				if (store.isOKToDelete(taxCat))
				{
					store.removeTaxCategory(taxCat);
					taxCats.removeElement(taxCat);
				}
			}
		});
		delete_Button.setBackground(new Color(192, 192, 192));
		delete_Button.setBounds(389, 342, 89, 23);
		delete_Button.setEnabled(false);
		add(delete_Button);

	}
}

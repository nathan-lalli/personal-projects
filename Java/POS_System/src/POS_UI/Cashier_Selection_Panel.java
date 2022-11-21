package POS_UI;

import javax.swing.JPanel;

import POS_PD.*;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Cashier_Selection_Panel extends JPanel {

	JButton edit_button;
	JButton delete_button;
	/**
	 * Create the panel.
	 * @param currentFrame 
	 * @param cashier 
	 * @param person 
	 */
	public Cashier_Selection_Panel(JFrame currentFrame, Cashier cashier, Store store, Person person) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cashier Selection List");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(196, 21, 132, 32);
		add(lblNewLabel);
		
		DefaultListModel<Cashier> cashierList = new DefaultListModel<Cashier>();
		
		for (Cashier c : store.getCashiers().values())
		{
			cashierList.addElement(c);
		}
		
		JList<Cashier> list = new JList<Cashier>(cashierList);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				//edit_button.setEnabled(true);
				//delete_button.setEnabled(list.getSelectedValue().isOKToDelete());
				
				
				if (!list.isSelectionEmpty())
				{
					edit_button.setEnabled(true);
					if(list.getSelectedValue().isOKToDelete())
					{
						delete_button.setEnabled(true);
					}
					else
					{
						delete_button.setEnabled(false);
					}
				} 
		}});
		list.setBackground(new Color(192, 192, 192));
		list.setBounds(114, 107, 297, 185);
		add(list);
		
		JButton add_button = new JButton("Add");
		add_button.setBackground(new Color(192, 192, 192));
		add_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Cashier_Edit_Panel(currentFrame, new Cashier(), store, true, new Person()));
				currentFrame.getContentPane().revalidate();
			}
		});
		add_button.setBounds(54, 343, 89, 23);
		add(add_button);
		
		
		
		edit_button = new JButton("Edit");
		edit_button.setBackground(new Color(192, 192, 192));
		edit_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cashier cashier = list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Cashier_Edit_Panel(currentFrame, cashier, store, false, cashier.getPerson()));
				currentFrame.getContentPane().revalidate();
			}
		});
		edit_button.setBounds(218, 343, 89, 23);
		edit_button.setEnabled(false);
		add(edit_button);
		
		delete_button = new JButton("Delete");
		delete_button.setBackground(new Color(192, 192, 192));
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cashier cashier = list.getSelectedValue();
				if (cashier.isOKToDelete())
				{
					store.removeCashier(cashier);
					cashierList.removeElement(cashier);
				}
			}
		});
		delete_button.setBounds(382, 343, 89, 23);
		delete_button.setEnabled(false);
		add(delete_button);

	}
}

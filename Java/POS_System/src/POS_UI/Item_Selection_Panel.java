package POS_UI;

import javax.swing.JPanel;

import POS_PD.*;

import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class Item_Selection_Panel extends JPanel {
	
	JButton delete_Button;
	JButton edit_Button;
	/**
	 * Create the panel.
	 */
	public Item_Selection_Panel(JFrame currentFrame, Store store, Item item) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item Selection List");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(197, 21, 115, 14);
		add(lblNewLabel);
		
		DefaultListModel<Item> itemList = new DefaultListModel<Item>();
		
		for (Item i : store.getItems().values())
		{
			itemList.addElement(i);
		}
		
		JList<Item> list = new JList<Item>(itemList);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!list.isSelectionEmpty())
				{
					edit_Button.setEnabled(true);
					if(list.getSelectedValue().isOKToDelete())
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
		list.setBounds(73, 58, 348, 202);
		add(list);
		
		JButton add_Button = new JButton("Add");
		add_Button.setBackground(new Color(192, 192, 192));
		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Item_Edit_Panel(currentFrame, store, new Item(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		add_Button.setBounds(59, 282, 89, 23);
		add(add_Button);
		
		edit_Button = new JButton("Edit");
		edit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Item_Edit_Panel(currentFrame, store, item, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		edit_Button.setBackground(new Color(192, 192, 192));
		edit_Button.setBounds(205, 282, 89, 23);
		edit_Button.setEnabled(false);
		add(edit_Button);
		
		delete_Button = new JButton("Delete");
		delete_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = list.getSelectedValue();
				if (item.isOKToDelete())
				{
					store.removeItem(item);
					itemList.removeElement(item);
				}
			}
		});
		delete_Button.setBackground(new Color(192, 192, 192));
		delete_Button.setBounds(351, 282, 89, 23);
		delete_Button.setEnabled(false);
		add(delete_Button);

	}
}

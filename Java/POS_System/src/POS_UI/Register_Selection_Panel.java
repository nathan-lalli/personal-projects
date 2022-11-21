package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;

import POS_PD.*;

import javax.swing.event.ListSelectionEvent;
import java.awt.Color;

public class Register_Selection_Panel extends JPanel {

	JButton edit_Button;
	JButton delete_Button;
	
	/**
	 * Create the panel.
	 */
	public Register_Selection_Panel(JFrame currentFrame, Register register, Store store) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register List");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(229, 33, 81, 14);
		add(lblNewLabel);
		
		DefaultListModel<Register> registerList = new DefaultListModel<Register>();
		for (Register r : store.getRegisters().values())
		{
			registerList.addElement(r);
		}
		JList<Register> list = new JList<Register>(registerList);
		list.setBackground(new Color(192, 192, 192));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
				if (!list.isSelectionEmpty())
				{
					edit_Button.setEnabled(true);
					if(list.getSelectedValue().isOKToDelete())
					{
						delete_Button.setEnabled(true);
					}
				}
				else
				{
					edit_Button.setEnabled(false);
					delete_Button.setEnabled(false);
				}
			}
		});
		list.setBounds(105, 91, 326, 223);
		add(list);
		
		JButton add_Button = new JButton("Add");
		add_Button.setBackground(new Color(192, 192, 192));
		add_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Register_Edit_Panel(currentFrame, new Register(), store, true));
				currentFrame.getContentPane().revalidate();
			}
		});
		add_Button.setBounds(67, 355, 89, 23);
		add(add_Button);
		
		edit_Button = new JButton("Edit");
		edit_Button.setBackground(new Color(192, 192, 192));
		edit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = list.getSelectedValue();
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new Register_Edit_Panel(currentFrame, register, store, false));
				currentFrame.getContentPane().revalidate();
			}
		});
		edit_Button.setBounds(223, 355, 89, 23);
		edit_Button.setEnabled(false);
		add(edit_Button);
		
		delete_Button = new JButton("Delete");
		delete_Button.setBackground(new Color(192, 192, 192));
		delete_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register register = list.getSelectedValue();
				if (register.isOKToDelete())
				{
					store.removeRegister(register);
					registerList.removeElement(register);
				}
			}
		});
		delete_Button.setBounds(379, 355, 89, 23);
		delete_Button.setEnabled(false);
		add(delete_Button);

	}
}

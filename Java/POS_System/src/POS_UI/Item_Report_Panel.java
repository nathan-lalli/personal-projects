package POS_UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

import POS_PD.*;

public class Item_Report_Panel extends JPanel {
	DatePicker datePicker;
	/**
	 * Create the panel.
	 */
	public Item_Report_Panel(JFrame currentFrame, Store store) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item Report");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(209, 37, 85, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(150, 105, 48, 14);
		add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 148, 378, 150);
		add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setBackground(new Color(192, 192, 192));
		
		JLabel error_Message = new JLabel("No Date Selected!");
		error_Message.setForeground(Color.RED);
		error_Message.setBounds(222, 78, 120, 14);
		error_Message.setVisible(false);
		add(error_Message);
		
		JButton generate_Button = new JButton("Generate");
		generate_Button.setBackground(new Color(192, 192, 192));
		generate_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate date = datePicker.getDate();
				
				if (date != null)
				{
					String text = generateItemReport(store, date);
					textArea.setText(text);
				}
				else
				{
					error_Message.setVisible(true);
				}
			}
		});
		generate_Button.setBounds(119, 332, 89, 23);
		add(generate_Button);
		
		JButton close_Button = new JButton("Close");
		close_Button.setBackground(new Color(192, 192, 192));
		close_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POS_Home_Panel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		close_Button.setBounds(267, 332, 89, 23);
		add(close_Button);
		
		datePicker = new DatePicker();
		datePicker.getComponentToggleCalendarButton().setBackground(new Color(192, 192, 192));
		datePicker.getComponentDateTextField().setBackground(new Color(192, 192, 192));
		datePicker.setBounds(201, 103, 155, 20);
		add(datePicker);
	}
	
	public String generateItemReport(Store store, LocalDate date)
	{
		String report = "";
		String newLine = "\n";
		String tab = "\t";
		int numberItems = 0;
		int counter = 0;
		int temp = 0;
		String itemsSold = "";
		
		for(Item i : store.getItems().values())
		{
			numberItems++;
		}	
		
		String[] itemCount = new String[numberItems*2];
		
		for(Item i : store.getItems().values())
		{
			itemCount[counter] = i.getDescription();
			itemCount[counter+1] = "0";
			counter+=2;
		}	
		
		  for(Session s : store.getSessions()) 
		  { 	
			  for(Sale sale : s.getSales()) 
			  {
				  if(sale.getDateTime().toLocalDate().equals(date))
				  { 
					  for(SaleLineItem sli : sale.getSaleLineItems()) 
					  { 
						  counter = numberItems*2;
						  counter-=1;
						  while(counter >= 0)
						  {
							  if(itemCount[counter].contentEquals(sli.getItem().getDescription()))
							  {
								  temp = Integer.parseInt(itemCount[counter+1]);
								  temp += sli.getQuantity();
								  itemCount[counter+1] = Integer.toString(temp);
							  }
							  counter--;
						  }
					  } 
				  }
			  } 
		  }
		
		report += "Item Report for: "+date.toString()+newLine;
		report += newLine;
		report += "Number"+tab+tab+"Name"+tab+tab+"Amount Sold"+newLine;

		for(Item i : store.getItems().values())
		{
			counter = numberItems*2;
			counter-=1;
			while(counter >= 0)
			{
				if(itemCount[counter].contentEquals(i.getDescription()))
				{
					itemsSold = itemCount[counter+1];
					report += i.getNumber()+tab;
					if(i.getDescription().length()>=13)
					{
						report+=tab+i.getDescription()+tab+itemsSold+newLine;
					}
					else
					{	
						report+=tab+i.getDescription()+tab+tab+itemsSold+newLine;
					}
				}
				counter--;
			}
		}		 
		return report;
	}

}

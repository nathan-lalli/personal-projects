package POS_UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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

public class Sales_Report_Panel extends JPanel {
	DatePicker datePicker;
	/**
	 * Create the panel.
	 */
	public Sales_Report_Panel(JFrame currentFrame, Store store) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sales Report");
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
					String text = generateSalesReport(store, date);
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
	
	public String generateSalesReport(Store store, LocalDate date)
	{
		String report = "";
		String newLine = "\n";
		String tab = "\t";
		String cash = "0.00";
		String check = "0.00";
		String credit = "0.00";
		BigDecimal temp;
		BigDecimal total = new BigDecimal("0.00");
		BigDecimal totalCash = new BigDecimal("0.00");
		BigDecimal totalCheck= new BigDecimal("0.00");
		BigDecimal totalCredit = new BigDecimal("0.00");
		BigDecimal totalOverall = new BigDecimal("0.00");
		String time = "";
		
		report += "Sales Report for: "+date.toString()+newLine;
		report += newLine;
		report += "Time"+tab+"Cash"+tab+"Check"+tab+"Credit"+tab+"Total"+newLine;
		
		for(Session s : store.getSessions())
		{
			for(Sale sale : s.getSales())
			{				
				if(sale.getDateTime().toLocalDate().equals(date))
				{
					for(Payment p : sale.getPayments())
					{
						if(p instanceof Cash)
						{
							temp = new BigDecimal(cash);
							temp = temp.add(p.getAmount());
							total = total.add(temp);
							totalCash = totalCash.add(temp);
							cash = temp.toString();
						}
						else if(p instanceof Check)
						{
							temp = new BigDecimal(check);
							temp = temp.add(p.getAmount());
							total = total.add(temp);
							totalCheck = totalCheck.add(temp);
							check = temp.toString();
						}
						else if(p instanceof Credit)
						{
							temp = new BigDecimal(credit);
							temp = temp.add(p.getAmount());
							total = total.add(temp);
							totalCredit = totalCredit.add(temp);
							credit = temp.toString();
						}
					}
					
					time = Integer.toString(sale.getDateTime().toLocalTime().getHour())+":"
							+Integer.toString(sale.getDateTime().toLocalTime().getMinute());
							
					report += time+tab+cash+tab+check+tab+credit+tab+total.toString()+newLine;
					
					total = new BigDecimal("0.00");
					cash = "0.00";
					credit = "0.00";
					check= "0.00";
				}

			}
		}
		
		report += newLine;
		
		totalOverall = totalOverall.add(totalCash);
		totalOverall = totalOverall.add(totalCheck);
		totalOverall = totalOverall.add(totalCredit);
		
		report += "Total: "+tab+totalCash.toString()+tab+totalCheck.toString()+tab
				+totalCredit.toString()+tab+totalOverall.toString();
		
		return report;
	}

}

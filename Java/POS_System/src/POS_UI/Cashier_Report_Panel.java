package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;

import POS_PD.*;

import java.awt.TextArea;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.github.lgooddatepicker.components.DatePicker;

public class Cashier_Report_Panel extends JPanel {
	DatePicker datePicker;
	/**
	 * Create the panel.
	 */
	public Cashier_Report_Panel(JFrame currentFrame, Store store) {
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cashier Report");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(209, 37, 112, 14);
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
					String text = generateCashierReport(store, date);
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
	
	public String generateCashierReport(Store store, LocalDate date)
	{
		String report = "";
		String newLine = "\n";
		String tab = "\t";
		
		report += "Cashier Report for: "+date.toString()+newLine;
		report += newLine;
		report += "Number"+tab+"Name"+tab+"Count"+tab+"Amount"+tab+"Diff"+newLine;
		
		BigDecimal total = new BigDecimal("0.00");
		BigDecimal totalDiff = new BigDecimal ("0.00");
		
		int totalCount = 0;
		
		for(Cashier c : store.getCashiers().values())
		{
			BigDecimal cashierTotal = c.cashierDollarSales(date);
			BigDecimal cashierDiff = c.calcTotalDiff(date);
			int cashierCount = c.calcNumberSales(date);
			report += c.getNumber()+tab+c.getPerson().getName()+tab+
					cashierCount+tab+cashierTotal.toString()+tab+
					cashierDiff.toString()+newLine;
			total = total.add(cashierTotal);
			totalDiff = totalDiff.add(cashierDiff);
			totalCount += cashierCount;
		}
		report+=newLine;
		report+="Total: "+tab+tab+tab+ total.toString();
		
		return report;
	}
}

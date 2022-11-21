package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POS_PD.Store;
import POS_PD.TaxCategory;
import POS_PD.TaxRate;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Tax_Rate_Edit_Panel extends JPanel {
	private JTextField rate_Text;
	private JTextField date_Text;

	/**
	 * Create the panel.
	 */
	public Tax_Rate_Edit_Panel(JFrame currentFrame, JPanel currentPanel, TaxCategory taxCategory, TaxRate taxRate, Boolean isAdd) {
		setBackground(new Color(139, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tax Rate Edit");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(236, 53, 81, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Rate:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(65, 114, 48, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Effective Date:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(65, 152, 116, 14);
		add(lblNewLabel_2);
		
		String taxString = "";
		if (taxRate.getTaxRate() != null)
		{
			taxString = taxRate.getTaxRate().toPlainString();
		}
		
		rate_Text = new JTextField(taxString);
		rate_Text.setBounds(162, 111, 96, 20);
		add(rate_Text);
		rate_Text.setColumns(10);
		
		String dateString = "";
		if (taxRate.getEffectiveDate() != null)
		{
			dateString = taxRate.getEffectiveDate().toString();
		}
		
		date_Text = new JTextField(dateString);
		date_Text.setBounds(162, 149, 96, 20);
		add(date_Text);
		date_Text.setColumns(10);
		
		JButton save_Button = new JButton("Save");
		save_Button.setBackground(new Color(192, 192, 192));
		save_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxRate.setTaxRate(new BigDecimal (rate_Text.getText()));
				taxRate.setEffectiveDate(date_Text.getText());
				if (isAdd)
				{
					taxCategory.addTaxRate(taxRate);
				}
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		save_Button.setBounds(132, 319, 89, 23);
		add(save_Button);
		
		JButton cancel_Button = new JButton("Cancel");
		cancel_Button.setBackground(new Color(192, 192, 192));
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		cancel_Button.setBounds(353, 319, 89, 23);
		add(cancel_Button);

	}

}

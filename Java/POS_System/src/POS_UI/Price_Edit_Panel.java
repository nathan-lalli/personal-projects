package POS_UI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import POS_PD.*;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.awt.event.ActionEvent;

public class Price_Edit_Panel extends JPanel {
	private JTextField price_Text;
	private JTextField effDate_Text;
	private boolean isPromo = false;
	private PromoPrice promoPrice = null;
	private Price price;
	private JTextField endDate_Text;
	JLabel lblNewLabel_3;

	/**
	 * Create the panel.
	 */
	public Price_Edit_Panel(JFrame currentFrame, JPanel currentPanel, Store store, Item item, Price priceIn, Boolean isAdd) {
		
		price = priceIn;
		
		if (price instanceof PromoPrice)
		{
			isPromo = true;
			promoPrice = (PromoPrice) price;
		}
		
		setBackground(new Color(128, 0, 0));
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Price Edit");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(195, 38, 60, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(76, 119, 83, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Effective Date:");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(76, 166, 112, 14);
		add(lblNewLabel_2);
		
		String thePrice = "";
		if(price.getPrice() != null)
		{
			thePrice = price.getPrice().toPlainString();
		}
		price_Text = new JTextField(thePrice);
		price_Text.setBackground(new Color(192, 192, 192));
		price_Text.setBounds(169, 116, 86, 20);
		add(price_Text);
		price_Text.setColumns(10);
		
		String effDate = "";
		if(price.getEffectiveDate() != null)
		{
			effDate = price.getEffectiveDate().toString();
		}
		effDate_Text = new JTextField(effDate);
		effDate_Text.setBackground(new Color(192, 192, 192));
		effDate_Text.setBounds(169, 163, 86, 20);
		add(effDate_Text);
		effDate_Text.setColumns(10);
		
		JCheckBox promPrice_Box = new JCheckBox("Promo Price");
		promPrice_Box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (promPrice_Box.isSelected())
				{
					isPromo = true;
					price = new PromoPrice();
					promoPrice = new PromoPrice();
					
					lblNewLabel_3.setVisible(true);
					endDate_Text.setVisible(true);
				}
				else
				{
					isPromo = false;
					lblNewLabel_3.setVisible(false);
					endDate_Text.setVisible(false);
					price = new Price();
				}
			}
		});
		promPrice_Box.setBackground(new Color(128, 0, 0));
		promPrice_Box.setForeground(new Color(255, 255, 255));
		promPrice_Box.setBounds(158, 77, 126, 23);
		if(isPromo)
		{
			promPrice_Box.setSelected(true);
		}
		else
		{
			promPrice_Box.setSelected(false);
		}
		if(isAdd)
		{
			promPrice_Box.setEnabled(true);
		}
		else
		{
			promPrice_Box.setEnabled(false);
		}
		add(promPrice_Box);
		
		JButton save_Button = new JButton("Save");
		save_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				price.setPrice(new BigDecimal (price_Text.getText()));
				price.setEffectiveDate(effDate_Text.getText());
				
				if(isAdd)
				{
					item.addPrice(price);
				}
				
				if(isPromo)
				{
					promoPrice.setEndDate(endDate_Text.getText());
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		save_Button.setBackground(new Color(192, 192, 192));
		save_Button.setBounds(110, 253, 89, 23);
		add(save_Button);
		
		JButton cancel_Button = new JButton("Cancel");
		cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		cancel_Button.setBackground(new Color(192, 192, 192));
		cancel_Button.setBounds(243, 253, 89, 23);
		add(cancel_Button);
				
			
			endDate_Text = new JTextField();
			endDate_Text.setBackground(new Color(192, 192, 192));
			endDate_Text.setBounds(169, 202, 86, 20);
			endDate_Text.setVisible(false);
			add(endDate_Text);
			endDate_Text.setColumns(10);
		
			lblNewLabel_3 = new JLabel("End Date:");
			lblNewLabel_3.setForeground(new Color(255, 255, 255));
			lblNewLabel_3.setBackground(new Color(255, 255, 255));
			lblNewLabel_3.setBounds(77, 208, 83, 14);
			lblNewLabel_3.setVisible(false);
			add(lblNewLabel_3);
		
		if(isPromo)
		{
			lblNewLabel_3.setVisible(true);
			
			String endDate = "";
			//SimpleDateFormat sdf1 = new SimpleDateFormat("M/d/yy");
			if (promoPrice.getEndDate() != null)
			{
				endDate = promoPrice.getEndDate().toString();
				//endDate = sdf1.format(promoPrice.getEndDate());
			}
			endDate_Text.setText(endDate);
			endDate_Text.setVisible(true);
		}
		


	}
}

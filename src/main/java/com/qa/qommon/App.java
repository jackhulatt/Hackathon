package com.qa.qommon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.qommon.controller.Action;
import com.qa.qommon.controller.CrudController;
import com.qa.qommon.controller.DriverController;
import com.qa.qommon.controller.OrderController;
import com.qa.qommon.persistence.dao.DriverDAO;
import com.qa.qommon.persistence.dao.OrderDAO;
import com.qa.qommon.persistence.domain.Domain;
import com.qa.qommon.utils.DBUtils;
import com.qa.qommon.utils.Utils;

public class App implements ActionListener{
	
	private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;
    private static JLabel fail;
	

	public static final Logger LOGGER = LogManager.getLogger();

	private final DriverController drivers;
	private final OrderController orders;
	private final Utils utils;
	
	public App() {
		this.utils = new Utils();
		final DriverDAO drivDAO = new DriverDAO();
		this.drivers = new DriverController(drivDAO, utils);
		final OrderDAO ordDAO = new OrderDAO();
		this.orders = new OrderController(ordDAO, utils);
	}
	
	public void qommonSystem() {
		DBUtils.connect();
		 JFrame frame = new JFrame();
	     JPanel panel = new JPanel();

	     frame.setSize(350, 200);
	     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     frame.add(panel);

	     panel.setLayout(null);

	     userLabel = new JLabel("User");
	     userLabel.setBounds(10, 20, 80, 25);
	     panel.add(userLabel);
	     userText = new JTextField(20);
	     userText.setBounds(100, 20, 165, 25);
	     panel.add(userText);
	     passwordLabel = new JLabel("Password");
	     passwordLabel.setBounds(10, 50, 80, 25);
	     panel.add(passwordLabel);
	     passwordText = new JPasswordField();
	     passwordText.setBounds(100, 50, 165, 25);
	     panel.add(passwordText);

	     button = new JButton("Login");
	     button.setBounds(10, 80, 80, 25);
	     button.addActionListener(new  App());
	     {

	         panel.add(button);


	         success = new JLabel("");
	         success.setBounds(10, 110, 300, 25);
	         panel.add(success);
	         fail = new JLabel("");
	         fail.setBounds(10, 110, 300, 25);
	         panel.add(fail);

	         frame.setVisible(true);

	       }
	    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String user = userText.getText();
        String password = passwordText.getText();
        System.out.println(user + ", " + password);
        if(user.equals("Admin") && password.equals("Admin")){
            success.setText("Login successful");

        } else {
            fail.setText("Invalid log in");
        }
	}
}

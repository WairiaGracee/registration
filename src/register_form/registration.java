package register_form;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class registration {

	private JFrame rg_form;
	private JTextField nametxt;
	private JTextField usertxt;
	private JPasswordField passfield;
	private JPasswordField passfield2;
	private JTextField email;
	private JTextField phone;
	private JTextField address;
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/form";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "2004";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		    Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		    // Handle the exception appropriately
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registration window = new registration();
					window.rg_form.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public registration() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		rg_form = new JFrame();
		rg_form.setBounds(100, 100, 580, 428);
		rg_form.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rg_form.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Register Form");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblNewLabel.setBounds(25, 10, 157, 43);
		rg_form.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(" Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(83, 63, 55, 13);
		rg_form.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Username:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(58, 92, 105, 13);
		rg_form.getContentPane().add(lblNewLabel_2);
		
		nametxt = new JTextField();
		nametxt.setBounds(136, 63, 318, 19);
		rg_form.getContentPane().add(nametxt);
		nametxt.setColumns(10);
		
		usertxt = new JTextField();
		usertxt.setBounds(136, 90, 318, 19);
		rg_form.getContentPane().add(usertxt);
		usertxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Password:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_3.setBounds(58, 115, 69, 13);
		rg_form.getContentPane().add(lblNewLabel_3);
		
		passfield = new JPasswordField();
		passfield.setBounds(136, 113, 318, 19);
		rg_form.getContentPane().add(passfield);
		
		JLabel lblNewLabel_4 = new JLabel("Confirm Password:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_4.setBounds(10, 144, 128, 13);
		rg_form.getContentPane().add(lblNewLabel_4);
		
		passfield2 = new JPasswordField();
		passfield2.setBounds(136, 142, 318, 19);
		rg_form.getContentPane().add(passfield2);
		
		JLabel lblNewLabel_5 = new JLabel("Email:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_5.setBounds(83, 177, 45, 13);
		rg_form.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Phone:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_6.setBounds(83, 207, 55, 13);
		rg_form.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Address:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_7.setBounds(58, 230, 69, 13);
		rg_form.getContentPane().add(lblNewLabel_7);
		
		email = new JTextField();
		email.setBounds(136, 171, 318, 19);
		rg_form.getContentPane().add(email);
		email.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(136, 200, 318, 19);
		rg_form.getContentPane().add(phone);
		phone.setColumns(10);
		
		address = new JTextField();
		address.setBounds(137, 228, 317, 19);
		rg_form.getContentPane().add(address);
		address.setColumns(10);
		
		JButton submit = new JButton("Submit");
        submit.setFont(new Font("Tahoma", Font.BOLD, 12));
        submit.setBounds(78, 286, 85, 21);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call a method to handle database insertion
                insertIntoDatabase();
            }
        });
        rg_form.getContentPane().add(submit);
		
        JButton reset = new JButton("Reset");
        reset.setFont(new Font("Tahoma", Font.BOLD, 12));
        reset.setBounds(213, 287, 85, 21);
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Clear the form fields
                clearForm();
            }
        });
        rg_form.getContentPane().add(reset);
		
        JButton close = new JButton("Close");
        close.setFont(new Font("Tahoma", Font.BOLD, 12));
        close.setBounds(358, 287, 85, 21);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Close the application
                System.exit(0);
            }
        });
        rg_form.getContentPane().add(close);
	}
	private void insertIntoDatabase() {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;

	    try {
//	    	Class.forName("com.mysql.cj.jdbc.Driver");
	        // Establish database connection
	        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

	        // Prepare SQL statement with placeholders
	        String sql = "INSERT INTO registration (name, username, password, confirm_password, email, phone, address) VALUES (?, ?, ?, ?, ?, ?, ?)";

	        preparedStatement = connection.prepareStatement(sql);

	        // Set values for placeholders from form fields
	        preparedStatement.setString(1, nametxt.getText());
	        preparedStatement.setString(2, usertxt.getText());
	        preparedStatement.setString(3, new String(passfield.getPassword()));
	        preparedStatement.setString(4, new String(passfield2.getPassword()));
	        preparedStatement.setString(5, email.getText());
	        preparedStatement.setString(6, phone.getText());
	        preparedStatement.setString(7, address.getText());

	        // Execute the SQL statement
	        preparedStatement.executeUpdate();
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        // Close resources in the finally block
	        try {
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	}

	// Method to clear form fields
	private void clearForm() {
	    nametxt.setText("");
	    usertxt.setText("");
	    passfield.setText("");
	    passfield2.setText("");
	    email.setText("");
	    phone.setText("");
	    address.setText("");
	}
}
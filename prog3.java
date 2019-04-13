import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import javax.swing.UIManager;
public class prog3 extends JFrame{


	static
	{
		try
		{
		// loads com.mysql.jdbc.Driver into memory
		Class.forName("com.mysql.jdbc.Driver");
		} 
		catch (ClassNotFoundException cnf) 
		{
		System.out.println("Driver could not be loaded: " + cnf);
		}
	}

	JLabel label;
	JButton admin,student;

	prog3(){
		super("JEE Main Portal");
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new Color(30, 30, 30));
		UI.put("Panel.background", new Color(30, 30, 30));
		UI.put("OptionPane.messageForeground", new Color(102, 252, 241));
		UI.put("OptionPane.minimumSize", new Dimension(100, 100));
		UI.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
		UI.put("Button.focus", new Color(0, 0, 0, 0));
        UI.put("ToggleButton.focus", new Color(0, 0, 0, 0));
		getContentPane().setLayout(
    	new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		label = new JLabel("Welcome To JEE Main Portal");
		label.setFont(new Font("Serif", Font.BOLD, 75));
		getContentPane().setBackground(new Color(190, 219, 218));
		admin = new JButton("Go to Admin Portal  ");
		student = new JButton("Go to Student Portal");
		admin.setFont(new Font("Serif", Font.BOLD, 40));
		admin.setBackground(new Color(11, 12, 16));
		admin.setForeground(new Color(102, 252, 241));
		student.setFont(new Font("Serif", Font.BOLD, 40));
		student.setBackground(new Color(11, 12, 16));
		student.setForeground(new Color(102, 252, 241));


		admin.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Result ob = new Result();
						ob.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						ob.setVisible(true);
						ob.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					}
				}
				);

		student.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						prog2 ob = new prog2();
						ob.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						ob.setVisible(true);
						ob.setExtendedState(JFrame.MAXIMIZED_BOTH); 
					}
				}
				);

		
		
		add(Box.createVerticalStrut(20));
		Box box = Box.createHorizontalBox();
		box.add(new JLabel("                                                                                                                                  "));
		box.add(admin);
		add(label);
		add(Box.createVerticalStrut(140));
		add(Box.createVerticalStrut(40));
		Box box1 = Box.createHorizontalBox();
		box1.add(new JLabel("                                                                                                                                 "));
		box1.add(student);
		//add(admin);
		add(Box.createVerticalStrut(20));
		add(box);
		add(Box.createVerticalStrut(60));  
		add(box1);
		//add(student);

		

	}

	public static void main(String[] args) {
		
		prog3 ob = new prog3();

		ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ob.setVisible(true);
		ob.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
}


import java.awt.*;
import java.awt.Image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import javax.swing.UIManager;
public class Result extends JFrame{

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
	
	JButton b2;
	JLabel label,label1;
	
	
	Result(){
		super("Admin Portal");
		UIManager UI=new UIManager();
 		UI.put("OptionPane.background",new Color(30, 30, 30));
		UI.put("Panel.background",new Color(30, 30, 30));
		UI.put("OptionPane.messageForeground",new Color(102, 252, 241));
		UI.put("OptionPane.minimumSize",new Dimension(100,100)); 
		UI.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
		 UI.put("Button.focus", new Color(0, 0, 0, 0));
        UI.put("ToggleButton.focus", new Color(0, 0, 0, 0));
		
		getContentPane().setLayout(
    	new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		b2 = new JButton("Update or Input record of a student");
		
		b2.setFont(new Font("Serif", Font.BOLD, 25));	
		b2.setAlignmentX(CENTER_ALIGNMENT);
		b2.setBackground(new Color(11, 12, 16));
		b2.setForeground(new Color(102, 252, 241));
		
		
		
		
		b2.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JPanel panel = new JPanel();
						JLabel labelp = new JLabel("Enter a password:");
						JPasswordField pass = new JPasswordField(10);
						pass.setFont(new Font("Serif", Font.BOLD, 25));
						panel.add(labelp);
						panel.add(pass);
						labelp.setForeground(new Color(102, 252, 241));
						labelp.setFont(new Font("Serif", Font.BOLD, 25));
						String[] options = new String[]{"OK", "Cancel"};
						int option = JOptionPane.showOptionDialog(null, panel, "Confirm Password",
						                         JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
						                         null, options, options[1]);
						String passw = new String(pass.getPassword());
						if(option == 0 && passw.compareTo("admin")==0) // pressing OK button
						{
					 	Table ob = new Table();
						ob.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						ob.setExtendedState(JFrame.MAXIMIZED_BOTH); 
						
						ob.setVisible(true);
						}
						else
							JOptionPane.showMessageDialog(null, "Invalid Password");
					}
				}
			);
			

		
		ImageIcon imageIcon = new ImageIcon("admin.jpg"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(400, 400,  Image.SCALE_SMOOTH); // scale it the smooth way  
		add(Box.createVerticalStrut(30));
		imageIcon = new ImageIcon(newimg);  // transform it back
		Box box = Box.createHorizontalBox();
		JLabel label2 = new JLabel("Admin Portal              ");
		label2.setFont(new Font("Serif", Font.BOLD, 55));
		box.add(label2);
		box.add(new JLabel(imageIcon));
		add(box);
		b2.setSize(new Dimension(50,50));
		add(Box.createVerticalStrut(100));

		add(b2);
		add(Box.createVerticalStrut(20));
		
		getContentPane().setBackground(new Color(190, 219, 218));
	}

	
	
	
	public static void main(String[] args) {
		
		Result ob = new Result();

		ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ob.setVisible(true);
		ob.setSize(500,250);

	}

}

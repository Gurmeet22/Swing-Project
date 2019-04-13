import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import java.sql.*;
import java.awt.*;
import java.awt.Image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
import javax.swing.UIManager;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class prog2 extends JFrame {

	static {
		try {
			// loads com.mysql.jdbc.Driver into memory
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException cnf) {
			System.out.println("Driver could not be loaded: " + cnf);
		}
	}

	JButton b2, b1;

	prog2() {
		super("Student Portal");
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new Color(30, 30, 30));
		UI.put("Panel.background", new Color(30, 30, 30));
		UI.put("OptionPane.messageForeground", new Color(102, 252, 241));
		UI.put("OptionPane.minimumSize", new Dimension(100, 100));
		UI.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
		UI.put("Button.focus", new Color(0, 0, 0, 0));
        UI.put("ToggleButton.focus", new Color(0, 0, 0, 0));

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		b1 = new JButton("Register");
		b2 = new JButton("Login");
		b2.setFont(new Font("Serif", Font.BOLD, 25));
		b1.setFont(new Font("Serif", Font.BOLD, 25));
		b2.setBackground(new Color(11, 12, 16));
		b2.setForeground(new Color(102, 252, 241));
		b1.setBackground(new Color(11, 12, 16));
		b1.setForeground(new Color(102, 252, 241));

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Register");
				frame.getContentPane().setBackground(new Color(190, 219, 218));
				frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
				JLabel l1 = new JLabel("Name :");
				JLabel l2 = new JLabel("Father's Name :");
				JLabel l3 = new JLabel("Age :");
				JLabel l7 = new JLabel("DOB :");
				JLabel l8 = new JLabel("Category :");
				JLabel l4 = new JLabel("Username :");
				JLabel l5 = new JLabel("Password :");
				JLabel l6 = new JLabel("Confirm Password :");
				JButton submit = new JButton("Submit");
				submit.setBackground(new Color(11, 12, 16));
				submit.setFont(new Font("Serif", Font.BOLD, 20));
				submit.setForeground(new Color(102, 252, 241));
				JTextField name = new JTextField(25);
				JTextField fname = new JTextField(25);
				JTextField age = new JTextField(25);
				JTextField dob = new JTextField(25);
				JTextField category = new JTextField(25);
				JTextField user = new JTextField(25);
				JPasswordField pass = new JPasswordField(25);
				JPasswordField passc = new JPasswordField(25);
				JCheckBox show = new JCheckBox();
				JCheckBox show1 = new JCheckBox();

				l1.setFont(new Font("Serif", Font.BOLD, 20));
				l8.setFont(new Font("Serif", Font.BOLD, 20));
				category.setFont(new Font("Serif", Font.BOLD, 20));
				name.setFont(new Font("Serif", Font.BOLD, 20));
				l2.setFont(new Font("Serif", Font.BOLD, 20));

				fname.setFont(new Font("Serif", Font.BOLD, 20));
				l3.setFont(new Font("Serif", Font.BOLD, 20));

				dob.setFont(new Font("Serif", Font.BOLD, 20));
				l4.setFont(new Font("Serif", Font.BOLD, 20));

				age.setFont(new Font("Serif", Font.BOLD, 20));
				l5.setFont(new Font("Serif", Font.BOLD, 20));

				user.setFont(new Font("Serif", Font.BOLD, 20));
				l6.setFont(new Font("Serif", Font.BOLD, 20));

				pass.setFont(new Font("Serif", Font.BOLD, 20));
				l7.setFont(new Font("Serif", Font.BOLD, 20));

				passc.setFont(new Font("Serif", Font.BOLD, 20));

				name.setToolTipText("Enter your name");
				category.setToolTipText("Enter your name");
				fname.setToolTipText("Enter your Father's name");
				age.setToolTipText("Enter your age");
				dob.setToolTipText("Enter your Birth Date.");
				user.setToolTipText("Username is case sensitive");
				pass.setToolTipText("Enter your password");
				passc.setToolTipText("Confirm your password");
				show.setToolTipText("Show password");
				show1.setToolTipText("Show password");
				show.setBackground(new Color(190, 219, 218));
				show1.setBackground(new Color(190, 219, 218));
				submit.setToolTipText("Submit details");

				show.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						if (show.isSelected())
							pass.setEchoChar((char) 0);
						else
							pass.setEchoChar('*');
					}
				});

				show1.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						if (show1.isSelected())
							passc.setEchoChar((char) 0);
						else
							passc.setEchoChar('*');
					}
				});

				Box box1 = Box.createHorizontalBox();
				box1.add(l5);
				box1.add(Box.createHorizontalStrut(10));
				box1.add(pass);
				box1.add(Box.createHorizontalStrut(10));
				box1.add(show);
				Box box2 = Box.createHorizontalBox();
				box2.add(l6);
				box2.add(Box.createHorizontalStrut(10));
				box2.add(passc);
				box2.add(Box.createHorizontalStrut(10));
				box2.add(show1);
				// frame.add(l1);
				Box box3 = Box.createHorizontalBox();
				box3.add(l1);
				box3.add(Box.createHorizontalStrut(10));
				box3.add(name);
				// frame.add(name);
				// frame.add(l2);
				Box box4 = Box.createHorizontalBox();
				box4.add(l2);
				box4.add(Box.createHorizontalStrut(10));
				box4.add(fname);
				// frame.add(fname);
				// frame.add(l3);
				Box box5 = Box.createHorizontalBox();
				box5.add(l3);
				box5.add(Box.createHorizontalStrut(10));
				box5.add(age);
				// frame.add(age);
				// frame.add(l7);
				Box box6 = Box.createHorizontalBox();
				box6.add(l7);
				box6.add(Box.createHorizontalStrut(10));
				box6.add(dob);
				// frame.add(roll);
				// frame.add(l4);
				Box box7 = Box.createHorizontalBox();
				box7.add(l4);
				box7.add(Box.createHorizontalStrut(10));
				box7.add(user);
				// frame.add(user);

				Box box8 = Box.createHorizontalBox();
				box8.add(l8);
				box8.add(Box.createHorizontalStrut(10));
				box8.add(category);

				frame.add(box3);
				add(Box.createVerticalStrut(30));
				frame.add(box4);
				add(Box.createVerticalStrut(30));
				frame.add(box8);
				add(Box.createVerticalStrut(30));
				frame.add(box5);
				add(Box.createVerticalStrut(20));
				frame.add(box6);
				add(Box.createVerticalStrut(20));
				frame.add(box7);
				add(Box.createVerticalStrut(20));
				frame.add(box1);
				add(Box.createVerticalStrut(20));
				frame.add(box2);
				frame.add(submit);

				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setVisible(true);
				frame.setSize(500, 350);
				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						String password = new String(pass.getPassword());
						String passwordc = new String(passc.getPassword());
						 if(password.compareTo(passwordc) !=0) 
						 	JOptionPane.showMessageDialog(null, "Passwords do not match"); 
						else{
						 
						char[] chars = { '1', 'A', 'a', 'B', 'b', 'C', 'c', '2', 'D', 'd', 'E', 'e', 'F', 'f', '3', 'G',
								'g', 'H', 'h', 'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', '4', 'M', 'm', 'N', 'n', 'O',
								'o', '5', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't', '6', '7', 'U', 'u', 'V',
								'v', 'U', 'u', 'W', 'w', '8', 'X', 'x', 'Y', 'y', 'Z', 'z', '9' };

						Color[] colors = { Color.red, Color.black, Color.blue };
						String value = "";

						int LENGTH = 6;

						StringBuffer sb = new StringBuffer();

						int index = 0;

						for (int i = 0; i < LENGTH; i++) {

							index = (int) (Math.random() * (chars.length - 1));
							sb.append(chars[index]);
						}

						value = String.valueOf(sb);
						if (value != null && !value.isEmpty()) {

							BufferedImage image = null;

							try {

								image = ImageIO.read(new File("background.jpg")); // Background Image

							} catch (IOException e2) {

								System.out.println(e2.getMessage());
								e2.printStackTrace();
							}

							Graphics g = image.getGraphics();

							g.setFont(g.getFont().deriveFont(30f));

							char[] c = value.toCharArray();

							int x = 20;
							int y = 50;

							for (int i = 0; i < c.length; i++) {
								x = x + 30;
								g.setColor(colors[(int) (Math.random() * 3)]);
								g.drawString(String.valueOf(c[i]), x, y);
							}

							g.dispose();
							try {

								ImageIO.write(image, "png", new File("Output.png")); // Output Image
								System.out.println("Captcha Generated Successfully!!!");
							} catch (IOException e1) {

								System.out.println(e1.getMessage());
								e1.printStackTrace();
							}
						}
						final String val = value;
						JFrame capt = new JFrame("Captcha : ");
						capt.getContentPane().setBackground(new Color(190, 219, 218));
						capt.setLayout(new FlowLayout());

						ImageIcon imageIcon = new ImageIcon("Output.png"); // load the image to a imageIcon
						Image image = imageIcon.getImage(); // transform it
						Image newimg = image.getScaledInstance(400, 200, Image.SCALE_SMOOTH);
						imageIcon = new ImageIcon(newimg);
						capt.add(new JLabel(imageIcon));
						JLabel label2 = new JLabel("Enter Text from the image   ");
						label2.setFont(new Font("Serif", Font.BOLD, 25));
						JTextField ans = new JTextField(10);
						ans.setFont(new Font("Serif", Font.BOLD, 25));
						// Box boxc = Box.createHorizontalBox();
						// boxc.add(label2);boxc.add(ans);
						// capt.add(boxc);
						capt.add(label2);
						capt.add(ans);
						capt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						capt.setVisible(true);
						capt.setSize(500, 350);
						ans.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if (val.compareTo(ans.getText()) == 0) {
									try {

										Connection conn = DriverManager.getConnection(
												"jdbc:mysql://localhost:3306/studentdb?useSSL=false", "Gurmeet",
												"WINDOWSTEN");

										String query = "INSERT INTO student(Name, Father_Name, Age, DOB,Username,Password,Category) VALUES(?,?,?,?,?,?,?)";
										PreparedStatement stmt = conn.prepareStatement(query);
										stmt.setString(1, name.getText());
										stmt.setString(2, fname.getText());
										stmt.setInt(3, Integer.parseInt(age.getText()));
										stmt.setString(4, dob.getText());
										stmt.setString(5, user.getText());
										stmt.setString(6, new String(pass.getPassword()));
										stmt.setString(7, category.getText());

										stmt.executeUpdate();

										stmt.close();
										conn.close();

										conn = DriverManager.getConnection(
							               "jdbc:mysql://localhost:3306/studentdb?useSSL=false", "Gurmeet", "WINDOWSTEN");
								
										query = "INSERT INTO class(Name, Roll, Category, DOB, Physics,Chemistry,Mathematics, Total) VALUES(?,?,?,?,?,?,?,?)";
										stmt = conn.prepareStatement(query);
										stmt.setString(1,name.getText());
										stmt.setInt(2,Integer.parseInt("0"));
										stmt.setString(3,category.getText());
										stmt.setString(4,dob.getText());
										stmt.setInt(5,Integer.parseInt("0"));
										stmt.setInt(6,Integer.parseInt("0"));
										stmt.setInt(7,Integer.parseInt("0"));
										stmt.setInt(8,Integer.parseInt("0"));
								
										stmt.executeUpdate();
										
									    stmt.close();
									    conn.close();

									} catch (SQLException exp) {
										exp.printStackTrace();
									}
									JOptionPane.showMessageDialog(null, "Data Submitted");

								} else {
									JOptionPane.showMessageDialog(null, "Invalid Input");
								}
							}
						});
					}
					}
				});
			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				JLabel label = new JLabel("Enter password:");
				JLabel label1 = new JLabel("Enter Username:");
				JTextField user = new JTextField(10);
				user.setFont(new Font("Serif", Font.BOLD, 25));
				JPasswordField pass = new JPasswordField(10);
				pass.setFont(new Font("Serif", Font.BOLD, 25));
				panel.add(label1);
				panel.add(user);
				panel.add(Box.createHorizontalStrut(5));
				panel.add(label);
				panel.add(pass);

				label.setFont(new Font("Serif", Font.BOLD, 25));
				label1.setFont(new Font("Serif", Font.BOLD, 25));

				label.setForeground(new Color(102, 252, 241));
				label1.setForeground(new Color(102, 252, 241));
				String[] options = new String[] { "OK", "Cancel" };
				int option = JOptionPane.showOptionDialog(null, panel, "Confirm Password", JOptionPane.NO_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
				String username = user.getText();
				String password = new String(pass.getPassword());
				try {

					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb?useSSL=false",
							"Gurmeet", "WINDOWSTEN");

					String query = "SELECT * from student where Username = ? and Password = ?";
					PreparedStatement stmt = conn.prepareStatement(query);
					stmt.setString(1, username);
					stmt.setString(2, password);
					ResultSet rset = stmt.executeQuery();
					if (rset.next() == false) {
						JOptionPane.showMessageDialog(null, "Invalid Username or Password");
					} else {
						String genr = "";
						String catr = "-";
						int roll=0;
						try {


							Connection conn1 = DriverManager.getConnection(
									"jdbc:mysql://localhost:3306/studentdb?useSSL=false", "Gurmeet", "WINDOWSTEN");

							String query4 ="SELECT Roll from class where Name = ?";
							PreparedStatement stmt4 = conn1.prepareStatement(query4);
							stmt4.setString(1, rset.getString("Name"));
							ResultSet rset5 = stmt4.executeQuery();rset5.next();
							roll = rset5.getInt("Roll");
							String query1 = "SELECT FIND_IN_SET( Total, (SELECT GROUP_CONCAT(DISTINCT Total ORDER BY Total DESC) FROM class)) AS \'rank\' FROM class WHERE Roll = "
									+ roll;
							Statement stmt1 = conn1.createStatement();
							Statement stmt2 = conn1.createStatement();
							String query2 = "SELECT Category from class WHERE Roll = " + roll;
							ResultSet rset2 = stmt2.executeQuery(query2);
							rset2.next();
							ResultSet rset1 = stmt1.executeQuery(query1);
							rset1.next();
							genr = rset1.getString("rank");
							String category = rset2.getString("Category");
							String cat = "\"" + category + "\"";
							if (!category.equals("General")) {
								
								String query3 = "SELECT FIND_IN_SET( Total, (SELECT GROUP_CONCAT( DISTINCT Total ORDER BY Total DESC ) FROM (SELECT * FROM class WHERE Category = "+ cat + ") As st )) AS \'rank\' FROM class WHERE Roll = "
										+ roll;

								Statement stmt3 = conn1.createStatement();
								ResultSet rset3 = stmt3.executeQuery(query3);
								rset3.next();
								catr = rset3.getString("rank");
								stmt3.close();
							}
							stmt2.close();
							stmt1.close();
							conn1.close();
						} catch (SQLException exp) {
							exp.printStackTrace();
						}

						String[] options1 = new String[] { "OK", "Cancel" };
						int option1 = JOptionPane.showOptionDialog(null,
								"Name : " + rset.getString("Name") + "\nFather Name : " + rset.getString("Father_Name")
										+ "\nAge : " + rset.getInt("Age") + "\nGeneral Rank : " + genr
										+ "\nCategory Rank : " + catr,
								"Student Details", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1,
								options1[1]);
					}
				} catch (SQLException exp) {
					exp.printStackTrace();
				}
			}
		});

		ImageIcon imageIcon = new ImageIcon("student.jpg"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it
		Image newimg = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH);
		add(Box.createVerticalStrut(30));
		imageIcon = new ImageIcon(newimg); // transform it back
		Box box1 = Box.createHorizontalBox();
		JLabel label2 = new JLabel("Student Portal              ");
		label2.setFont(new Font("Serif", Font.BOLD, 55));
		box1.add(label2);
		box1.add(new JLabel(imageIcon));
		add(box1);
		b2.setSize(new Dimension(50, 50));
		add(Box.createVerticalStrut(100));
		Box box2 = Box.createHorizontalBox();
		box2.add(b1);
		box2.add(new JLabel("                "));
		box2.add(b2);
		add(box2);
		getContentPane().setBackground(new Color(190, 219, 218));

	}

}

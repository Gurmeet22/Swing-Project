import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.event.*;
import javax.swing.table.AbstractTableModel;
import java.util.*;
import java.text.DecimalFormat;
import java.sql.*;
import javax.swing.UIManager;
public class Table extends JFrame {


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


	private boolean DEBUG = false;
	private JTable table;
	private JButton button;
	JScrollPane scrollPane;
	
	class MyTableModel extends AbstractTableModel{
		private String colnames[] = {"Name","Roll", "Category", "DOB", "Physics", "Chemistry", "Mathematics", "Total"};
		private ArrayList<Object[]> data;
		MyTableModel(ArrayList<Object[]> data){
			this.data = data;
		}
		public int getColumnCount() {
            return colnames.length;
        }

        public int getRowCount() {
            return data.size();
        }

        public String getColumnName(int col) {
            return colnames[col];
        }

        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }
        
        public boolean isCellEditable(int row, int col) {
        	return true;
        }
        
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }
        
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
              System.out.println("Setting value at " + row + "," + col
                  + " to " + value + " (an instance of "
                  + value.getClass() + ")");
            }
            int roll = Integer.parseInt(data.get(row)[1].toString());
            data.get(row)[col] = value;
            fireTableCellUpdated(row, col);
            String colname = colnames[col];
            try {
								
								Connection conn = DriverManager.getConnection(
							               "jdbc:mysql://localhost:3306/studentdb?useSSL=false", "Gurmeet", "WINDOWSTEN");
								
								if(colname.compareTo("Physics")==0 || colname.compareTo("Chemistry")==0 || colname.compareTo("Mathematics")==0 || colname.compareTo("Roll")==0){
									
									int total = Integer.parseInt(data.get(row)[4].toString()) + Integer.parseInt(data.get(row)[5].toString()) + Integer.parseInt(data.get(row)[6].toString());
									String query = "UPDATE class SET "+colname+" = (?), Total = "+total+" WHERE Roll = "+roll;
									PreparedStatement stmt = conn.prepareStatement(query);
									stmt.setInt(1,Integer.parseInt(value.toString()));
									stmt.executeUpdate();
									stmt.close();
									data.get(row)[7] = total;
									fireTableCellUpdated(row, 7);
								}
								else{
									String query = "UPDATE class SET "+colname+" = (?) WHERE Roll = "+roll;
									PreparedStatement stmt = conn.prepareStatement(query);
									System.out.println(query);
									stmt.setString(1,value.toString());
									stmt.executeUpdate();
									stmt.close();
								}
								
							    
							    conn.close();
							}catch(SQLException exp) {
						         exp.printStackTrace();
							}

          }
        
        public void addRow(Object[] rowData)
        {
         data.add(rowData);
         fireTableChanged(new TableModelEvent(this));
         				try {
								
								Connection conn = DriverManager.getConnection(
							               "jdbc:mysql://localhost:3306/studentdb?useSSL=false", "Gurmeet", "WINDOWSTEN");
								
								String query = "INSERT INTO class(Name, Roll, Category, DOB, Physics,Chemistry,Mathematics, Total) VALUES(?,?,?,?,?,?,?,?)";
								PreparedStatement stmt = conn.prepareStatement(query);
								stmt.setString(1,rowData[0].toString());
								stmt.setInt(2,Integer.parseInt(rowData[1].toString()));
								stmt.setString(3,rowData[2].toString());
								stmt.setString(4,rowData[3].toString());
								stmt.setInt(5,Integer.parseInt(rowData[4].toString()));
								stmt.setInt(6,Integer.parseInt(rowData[5].toString()));
								stmt.setInt(7,Integer.parseInt(rowData[6].toString()));
								stmt.setInt(8,Integer.parseInt(rowData[7].toString()));
						
								stmt.executeUpdate();
								
							    stmt.close();
							    conn.close();
							}catch(SQLException exp) {
						         exp.printStackTrace();
							}
        }
	}
	
	Table(){
		super("Students");
		UIManager UI=new UIManager();
 		UI.put("OptionPane.background",new Color(30, 30, 30));
		UI.put("Panel.background",new Color(30, 30, 30));
		UI.put("OptionPane.messageForeground",new Color(102, 252, 241));
		UI.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
		UI.put("Button.focus", new Color(0, 0, 0, 0));
        UI.put("ToggleButton.focus", new Color(0, 0, 0, 0));
		
		setLayout(new FlowLayout());
		
		DecimalFormat df1 = new DecimalFormat(".#");
		//TableSorter sorter = new TableSorter(new MyTableModel());
		//Object[][] data = new Object[100][7];
		ArrayList<Object[]> data = new ArrayList<Object[]>();
		Random rand = new Random();
		try {
								
								Connection conn = DriverManager.getConnection(
							               "jdbc:mysql://localhost:3306/studentdb?useSSL=false", "Gurmeet", "WINDOWSTEN");
								String query = "SELECT * FROM class";
								Statement st = conn.createStatement();
								ResultSet rs = st.executeQuery(query);
								while(rs.next()){
									String name = rs.getString("Name");
									int roll = rs.getInt("Roll");
									int p = rs.getInt("Physics");
									int c = rs.getInt("Chemistry");
									int m = rs.getInt("Mathematics");
									int t = rs.getInt("Total");
									String cat = rs.getString("Category");
									String dob = rs.getString("DOB");
									data.add(new Object[]{name, roll, cat, dob, p, c, m, t});
								}
							    st.close();
							    conn.close();
							}catch(SQLException exp) {
						         exp.printStackTrace();
							}
	
		MyTableModel ob = new MyTableModel(data);
		table = new JTable(ob) {
			
			DefaultTableCellRenderer renderleft = new DefaultTableCellRenderer();
			{renderleft.setHorizontalAlignment(SwingConstants.CENTER);}
			
			public TableCellRenderer getCellRenderer (int arg0, int arg1) {
			        return renderleft;
			}
			
			public String getToolTipText(MouseEvent e) {
				String tip = null;
		        Point p = e.getPoint();
		        int rowIndex = rowAtPoint(p);
		        int colIndex = columnAtPoint(p);
		        if(colIndex==0)
		        	tip = (String)getValueAt(rowIndex, colIndex);
		        else
		        	tip = super.getToolTipText(e);
		        return tip;}
		}; 
		
		table.getTableHeader().setToolTipText("Click to sort");
		scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		TableColumn column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(200);
		for(int i=1;i<8;i++) {
			column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(100);
		}
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getModel().addTableModelListener(
				new TableModelListener() {
					public void tableChanged(TableModelEvent e) {
						
					}
				}
			);
		button = new JButton("Input");
		button.setSize(5, 5);
		table.setFont(new Font("Serif", Font.BOLD, 20));
		table.setRowHeight(table.getRowHeight()+10);
		table.setForeground(new Color(102, 252, 241));
		table.setBackground(new Color(11, 12, 16));
		table.setSelectionForeground(Color.BLACK);
		table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 30));
		table.getTableHeader().setBackground(new Color(69, 162, 158));
		table.getTableHeader().setForeground(Color.BLACK);
		table.setAutoCreateRowSorter(true);
		button.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTextField name = new JTextField(10);
						name.setFont(new Font("Serif", Font.BOLD, 25));
					    JTextField roll = new JTextField(10);
					    roll.setFont(new Font("Serif", Font.BOLD, 25));
					    JTextField dob = new JTextField(10);
					    dob.setFont(new Font("Serif", Font.BOLD, 25));
					    JTextField phy = new JTextField(5);
					    phy.setFont(new Font("Serif", Font.BOLD, 25));
					    JTextField chem = new JTextField(5);
					    chem.setFont(new Font("Serif", Font.BOLD, 25));
					    JTextField maths = new JTextField(5);
					    maths.setFont(new Font("Serif", Font.BOLD, 25));
					    String[] cat= { "General", "SC", "ST", "OBC" };
					    JComboBox cate = new JComboBox(cat);

					    JPanel myPanel = new JPanel();
					    JLabel namel = new JLabel("Name :");
					    namel.setFont(new Font("Serif", Font.BOLD, 25));
					    namel.setForeground(new Color(102, 252, 241));
					    myPanel.add(namel);
					    myPanel.add(name);
					    myPanel.add(Box.createHorizontalStrut(15));
					    JLabel rolll = new JLabel("Roll :");
					    rolll.setFont(new Font("Serif", Font.BOLD, 25));
					    rolll.setForeground(new Color(102, 252, 241));
					    myPanel.add(rolll);
					    myPanel.add(roll);
					    myPanel.add(Box.createHorizontalStrut(15));
					    JLabel catll = new JLabel("Category :");
					    catll.setFont(new Font("Serif", Font.BOLD, 25));
					    catll.setForeground(new Color(102, 252, 241));
					    myPanel.add(catll);
					    myPanel.add(cate);
					    myPanel.add(Box.createHorizontalStrut(15));
					    JLabel dobl = new JLabel("DOB :");
					    dobl.setFont(new Font("Serif", Font.BOLD, 25));
					    dobl.setForeground(new Color(102, 252, 241));
					    myPanel.add(dobl);
					    myPanel.add(dob);
					    myPanel.add(Box.createHorizontalStrut(15));
					    JLabel pl = new JLabel("Physics :");
					    pl.setFont(new Font("Serif", Font.BOLD, 25));
					    pl.setForeground(new Color(102, 252, 241));
					    myPanel.add(pl);
					    myPanel.add(phy);
					    myPanel.add(Box.createHorizontalStrut(15));
					    JLabel cl = new JLabel("Chemistry :");
					    cl.setFont(new Font("Serif", Font.BOLD, 25));
					    cl.setForeground(new Color(102, 252, 241));
					    myPanel.add(cl);
					    myPanel.add(chem);
					    myPanel.add(Box.createHorizontalStrut(15));
					    JLabel ml = new JLabel("Maths :");
					    ml.setFont(new Font("Serif", Font.BOLD, 25));
					    ml.setForeground(new Color(102, 252, 241));
					    myPanel.add(ml);
					    myPanel.add(maths);
					    int result = JOptionPane.showConfirmDialog(null, myPanel,
					            "Enter Values", JOptionPane.OK_CANCEL_OPTION);
					    if(result==JOptionPane.OK_OPTION) {
					    int p = Integer.parseInt(phy.getText());
					    int c = Integer.parseInt(chem.getText());
					    int m = Integer.parseInt(maths.getText());
					    ob.addRow(new Object[] {name.getText(),Integer.parseInt(roll.getText()),cat[cate.getSelectedIndex()],dob.getText(),p,c,m,p+c+m});}
					}
				}
			);
		button.setBackground(new Color(11, 12, 16));
		button.setFont(new Font("Serif", Font.BOLD, 20));
		button.setForeground(new Color(102, 252, 241));
		
		scrollPane.setPreferredSize(new Dimension(1800,1000));
		add(scrollPane);
		add(button);
	}
	
	public static void main(String[] args) {
		Table ob = new Table();
		ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ob.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		ob.setUndecorated(true);
		ob.setVisible(true);
		ob.setSize(1800,650);
	}
}

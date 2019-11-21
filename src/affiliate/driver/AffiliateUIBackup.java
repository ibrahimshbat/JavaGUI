package affiliate.driver;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.wb.swt.SWTResourceManager;

import affiliate.affiliate.Affiliate;
import affiliate.referral.Referral;
import connection.ConnectToSQL;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Combo;
public class AffiliateUIBackup {


	protected Shell shlUpdate;
	Display display = null;
	private Table usersTable;
	private ArrayList<TableItem> items;
	private ConnectToSQL connection= new ConnectToSQL();
	Calendar curentDate = Calendar.getInstance(); 


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AffiliateUI window = new AffiliateUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {

		//Display display = Display.getDefault();
		createContents();
		shlUpdate.open();
		shlUpdate.pack();
		shlUpdate.layout();

		while (!shlUpdate.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		items = new ArrayList<TableItem> ();
		//shlUpdate = new Shell();
		this.display = new Display();
		shlUpdate = new Shell(display);
		shlUpdate.setBackground(SWTResourceManager.getColor(255, 140, 0));
		shlUpdate.setLayout(new GridLayout(2, false));
		GridData gridData = new GridData();



		//shlUpdate.setSize(1639, 1076);
		shlUpdate.setText("Affiliate");
		//shlUpdate.setMaximized(true);

		Combo period = new Combo(shlUpdate, SWT.NONE);
		period.setItems(new String[] {"All Quarters", "First Quarter (JAN-MAR)", "Second Quarter (APR-JUN)", "Third Quarter (JUL-SEP)", "Fourth Quarter (OCT-DEC)"});
		//groupNames.setTouchEnabled(true);
		period.setText("All Quarters");
		//period.setBounds(675, 105, 180, 23);
		gridData = new GridData();
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		period.setLayoutData(gridData);
		period.setToolTipText("Select Quarter from Here");

		//groupNames.setVisible(false);

		// Retrieve all groupName and add them to combo
		Map<Integer,String> groups = new HashMap<Integer,String>();
		Map<Integer,String> courses = new HashMap<Integer,String>();

		//	new AutoCompleteField(groupNames, new ComboContentAdapter());

		Label lblNewLabel = new Label(shlUpdate, SWT.NONE);
		//lblNewLabel.setBackground(SWTResourceManager.getColor());
		lblNewLabel.setFont(SWTResourceManager.getFont("Futura LT", 18, SWT.BOLD));
		lblNewLabel.setAlignment(SWT.CENTER);
		//lblNewLabel.setBounds(443, 10, 500, 52);
		lblNewLabel.setText("Evidence Based Education - Affiliate");
		lblNewLabel.setBackground(shlUpdate.getBackground());
		gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		lblNewLabel.setLayoutData(gridData);


		Label lblSelectNewExpiry = new Label(shlUpdate, SWT.NONE);
		lblSelectNewExpiry.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		lblSelectNewExpiry.setBackground(shlUpdate.getBackground());
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		lblSelectNewExpiry.setLayoutData(gridData);
		lblSelectNewExpiry.setText("Quarter of the Year");
		//lblSelectNewExpiry.setBounds(675, 88, 179, 15);

		Combo affiliateNames = new Combo(shlUpdate, SWT.NONE);
		affiliateNames.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		affiliateNames.setToolTipText("Select Affiliate from Here");
		affiliateNames.setBounds(479, 105, 175, 23);
		affiliateNames.setText("All Affiliates");

		//	new AutoCompleteField(courseNames, new ComboContentAdapter());



		Label lblSelectCourseName = new Label(shlUpdate, SWT.NONE);
		lblSelectCourseName.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		lblSelectCourseName.setBackground(shlUpdate.getBackground());
		//lblSelectCourseName.setBounds(479, 88, 175, 15);
		gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		lblSelectCourseName.setLayoutData(gridData);
		lblSelectCourseName.setText("Affiliate (*)");

		Label count = new Label(shlUpdate, SWT.NONE);
		count.setAlignment(SWT.LEFT);
		count.setFont(SWTResourceManager.getFont("Futura LT", 8, SWT.NORMAL));
		count.setBackground(shlUpdate.getBackground());
		//count.setBounds(1087, 585, 99, 25);
		//lblEmailContains.setVisible(false);
		count.setVisible(false);
		gridData = new GridData();
		gridData.verticalAlignment = SWT.TOP;
		count.setLayoutData(gridData);

		// Add affiliate names to affiliate combo
		Map<Long, Affiliate> affiliates = new HashMap<Long, Affiliate>();
		ArrayList<String> affNames = new ArrayList<String>();
		try {
			affiliates = connection.fetchAffiliates();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connection.getData();
		for(Map.Entry entry:affiliates.entrySet()){
			//System.out.print(entry.getKey() + " : " + entry.getValue());
			affNames.add(((Affiliate) entry.getValue()).getName());
		}
		affNames.add("All Affiliates");
		Collections.sort(affNames);
		for (String name: affNames)  
			affiliateNames.add(name);

		usersTable = createTable(usersTable, shlUpdate);

		Button button = new Button(shlUpdate, SWT.NONE);
		
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				exportUsersAsCSV();
			}
		});
		button.setText("Export Table as CSV");
		button.setVisible(false);
		button.setBounds(619, 639, 130, 39);



		Label year = new Label(shlUpdate, SWT.NONE);
		year.setBounds(860, 88, 83, 15);
		year.setText("Year");
		year.setBackground(shlUpdate.getBackground());


		Combo yearCombo = new Combo(shlUpdate, SWT.NONE);
		yearCombo.setItems(new String[] {"All Years", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"});
		//yearCombo.setBounds(861, 105, 82, 23);
		yearCombo.setToolTipText("Select Year from Here");
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		yearCombo.setLayoutData(gridData);
		yearCombo.setText("All Years");
		lblNewLabel.setBackground(shlUpdate.getBackground());



		affiliateNames.setFocus();

		Button button_1 = new Button(shlUpdate, SWT.NONE);
		button_1.setText("Display");
		button_1.setFont(SWTResourceManager.getFont("Futura LT", 10, SWT.BOLD));
		gridData = new GridData();
		gridData.horizontalSpan = 2;
		gridData.horizontalAlignment = SWT.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.verticalAlignment = SWT.FILL;
		gridData.grabExcessVerticalSpace = true;
		button_1.setLayoutData(gridData);
		//button_1.setBounds(667, 150, 82, 52);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//createTable(usersTable, shlUpdate);
				//addItems(usersTable, true);
				int year = curentDate.get(Calendar.YEAR);

				if(affiliateNames.getText().equals("All Affiliates") && period.getText().equals("All Quarters") 
						&& yearCombo.getText().equals("All Years")) {
					connection.emptyFilteredReferrals();
					connection.assignAllReferrals();
					if(connection.getFilteredReferrals().isEmpty()) {
						button.setVisible(false);
						usersTable.removeAll();
						count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);
						count.setVisible(true);
						count.setText("Total = "+connection.findTotalCommission());
						button.setVisible(true);
						//System.out.println("Check 1");
					}
				}else if(!affiliateNames.getText().equals("All Affiliates") && (period.getText().equals("All Quarters") 
						&& yearCombo.getText().equals("All Years"))) {
					connection.emptyFilteredReferrals();
					connection.findReferralByAffiliateName(affiliateNames.getText());
					if(connection.getFilteredReferrals().isEmpty()) {
						button.setVisible(false);
						usersTable.removeAll();
						count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);				
						count.setVisible(true);
						count.setText("Total = "+connection.findTotalCommission());
						button.setVisible(true);
						//System.out.println("Check 2");
					}				
				}else if(affiliateNames.getText().equals("All Affiliates") && (period.getText().equals("All Quarters") 
						&& !yearCombo.getText().equals("All Years"))) {
					connection.emptyFilteredReferrals();
					year = Integer.parseInt(yearCombo.getText());
					connection.findReferralsByGivenYear(year);
					if(connection.getFilteredReferrals().isEmpty()) {
						button.setVisible(false);
						usersTable.removeAll();
						count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);
						count.setVisible(true);
						count.setText("Total = "+connection.findTotalCommission());
						button.setVisible(true);
						//System.out.println("Check 3");
					}
				}else if(!affiliateNames.getText().equals("All Affiliates") && (period.getText().equals("All Quarters") 
						&& !yearCombo.getText().equals("All Years"))) {
					connection.emptyFilteredReferrals();
					year = Integer.parseInt(yearCombo.getText());
					connection.findReferralsByGivenYear(year);
					connection.findReferralsByGivenYearAndName(affiliateNames.getText());
					if(connection.getFilteredReferrals().isEmpty()) {
						button.setVisible(false);
						usersTable.removeAll();
						count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);
						count.setVisible(true);
						count.setText("Total = "+connection.findTotalCommission());
						button.setVisible(true);
						//System.out.println("Check 4");
					}				
				}else if((affiliateNames.getText().equals("All Affiliates") || !affiliateNames.getText().equals("All Affiliates")) && (!period.getText().equals("All Quarters") 
						&& yearCombo.getText().equals("All Years"))) {
					button.setVisible(false);
					usersTable.removeAll();
					count.setVisible(false);
					JOptionPane.showMessageDialog(null, "Please select the year", "Missing the year!",JOptionPane.INFORMATION_MESSAGE);
				}else if((affiliateNames.getText().equals("All Affiliates") || !affiliateNames.getText().equals("All Affiliates")) && (!period.getText().equals("All Quarters") 
						&& !yearCombo.getText().equals("All Years"))) {
					connection.emptyFilteredReferrals();
					year = Integer.parseInt(yearCombo.getText());
					//LocalDate date = LocalDate.of(year, month, dayOfMonth)
					connection.findReferralsByGivenQuarter(affiliateNames.getText(),  period.getText(), year);
					if(connection.getFilteredReferrals().isEmpty()) {
						button.setVisible(false);
						usersTable.removeAll();
						count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);
						count.setVisible(true);
						count.setText("Total = "+connection.findTotalCommission());
						button.setVisible(true);
						//System.out.println("Check 5");
					}	
				}
			}
		});

	}

	/**
	 * select and deselect all the users in the table
	 * @param isChecked
	 */
	public void checkUncheckItems(boolean isChecked) {

		for (int i = 0; i < usersTable.getItemCount(); i++) {
			usersTable.getItem(i).setChecked(isChecked);
			//users.add(api.getThinkificUsers().get(i));
		}
	}

	/**
	 * sort users by expiry date in the table
	 */
	public void sortUsersByExpiryDate() {

		//Collections.sort(api.getThinkificUsers(), new UserComparator());
		addItems(usersTable, true);

	}

	/**
	 * sort users by percentage completed in the table
	 */
	public void sortUsersBypercentageCompleted() {
		//Collections.sort(api.getThinkificUsers(), new UserComparator());
		//Collections.sort(api.getThinkificUsers(), Collections.reverseOrder());
		addItems(usersTable, true);		
	}

	/**
	 * Create table with four column
	 * @param usersTable
	 * @param shlUpdate
	 * @return
	 */
	public Table createTable(Table usersTable, Shell shlUpdate) {
		// Table table = new Table(shell, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);

		usersTable = new Table(shlUpdate, SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION);
		usersTable.setBackground(SWTResourceManager.getColor(192, 192, 192));
		//usersTable.setHeaderBackground(SWTResourceManager.getColor(128, 128, 128));
		usersTable.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		usersTable.setBounds(166, 231, 1031, 348);
		usersTable.setHeaderVisible(true);
		usersTable.setLinesVisible(true);

		TableColumn date  = new TableColumn(usersTable, SWT.CENTER);
		TableColumn affiliateName = new TableColumn(usersTable, SWT.CENTER);
		TableColumn customerName = new TableColumn(usersTable, SWT.CENTER);
		TableColumn productNameQuantity = new TableColumn(usersTable, SWT.CENTER);
		TableColumn total = new TableColumn(usersTable, SWT.CENTER);
		TableColumn commission  = new TableColumn(usersTable, SWT.CENTER);

		//usersTable.deselectAll();

		affiliateName.setWidth(100);
		date.setText("Date/Time");
		date.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sortUsersBypercentageCompleted();
			}
		});
		affiliateName.setText("Affiliate Name");
		affiliateName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//System.out.println("Slected !!!!!!!!!!!!!!");

				//	checkUncheckItems(filpedBoolean);
				//	filpedBoolean = !filpedBoolean;
			}
		});
		customerName.setText("Customer Name");
		productNameQuantity.setText("Product/Quantity");
		//		expiry_Date.addSelectionListener(new SelectionAdapter() {
		//			@Override
		//			public void widgetSelected(SelectionEvent e) {
		//				sortUsersByExpiryDate();
		//			}
		//		});
		total.setText("Total(£ ex. VAT)");
		date.setText("Date/Time");
		date.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				sortUsersBypercentageCompleted();
			}
		});
		commission.setText("Commission (£)");

		affiliateName.setWidth(180);
		customerName.setWidth(180);
		productNameQuantity.setWidth(250);
		total.setWidth(100);
		date.setWidth(200);
		commission.setWidth(100);

		affiliateName.setResizable(true);
		customerName.setResizable(true);
		productNameQuantity.setResizable(true);
		total.setResizable(true);
		date.setResizable(true);
		commission.setResizable(true);

		usersTable.setHeaderVisible(true);
		return usersTable;
	}

	/**
	 * Add items to table from users Map
	 * @param userTable
	 * @param select
	 */
	public void addItems(Table userTable, boolean select) {
		userTable.clearAll();
		ArrayList<TableItem> items = new ArrayList<TableItem>();
		TableItem item =null;
		userTable.removeAll();

		ArrayList<Referral> referrals = connection.getFilteredReferrals();
		//System.out.println("thinkificUsers Size === "+referrals.size());
		for (Referral referral : referrals) {
			item = new TableItem(usersTable, SWT.NONE);
			item.setText(new String[] {referral.getDate() + " "+ referral.getTime(), referral.getAffiliateName(),
					referral.getCustomerName(),referral.getCourse()+" ("+referral.getQuantity()+")",
					referral.getTotal()+"",
					referral.getCommission()+""});
			item.setChecked(select);
			items.add(item);
		}
		this.items=items;

	}

	/**
	 * find users who has been checked
	 */
	public void findUsersChecked() {
		//ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < usersTable.getItemCount(); i++) {
			if(usersTable.getItem(i).getChecked()){
				//users.add(api.getThinkificUsers().get(i));
			}
		}
		//api.setThinkificUsersRenewExpiryDate(users);
	}
	/**
	 * find Group Names
	 * @return ArrayList<String>
	 */
	public ArrayList<String> findGroupNames(){
		ArrayList<String> groupNames = new ArrayList<String>();
		return groupNames;
	}

	/**
	 * 
	 */
	public void exportUsersAsCSV() {
		ArrayList<Referral> referrals = new ArrayList<Referral>();
		for (int i = 0; i < usersTable.getItemCount(); i++) {
			if(usersTable.getItem(i).getChecked()){
				referrals.add(connection.getFilteredReferrals().get(i));
			}
		}
		PrintWriter referralsData = null;
		try {
			//System.out.println("Who is this:"+users.get(0));
			referralsData = new PrintWriter(new BufferedWriter(new FileWriter(
					System.getProperty("user.home")+"/Downloads/Affiliates.csv",false)));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		referralsData.println("Date/Time"+","+"Affiliate Name"+","+"Customer Name"+ "," + "Product/Quantity"+ 
				","+ "Total(£ ex. VAT)" + ","+"Commission (£)");
		for(int i=0;i<referrals.size();i++)
			referralsData.println(referrals.get(i).getDate()+"/"+ referrals.get(i).getTime()+","+
					referrals.get(i).getAffiliateName()+","+referrals.get(i).getCustomerName()+
					","+referrals.get(i).getCourse() +"("+ referrals.get(i).getQuantity()+")"+","+referrals.get(i).getTotal()+
					","+referrals.get(i).getCommission());
		referralsData.close();	
		//MessageDialog.openInformation(shlUpdate, "Informations", "The table has been exported to your Downloads folder named Affiliates");
		JOptionPane.showMessageDialog(null, "The table has been exported to your Downloads folder named Affiliates", "Dowloaded",JOptionPane.INFORMATION_MESSAGE);

	}
}

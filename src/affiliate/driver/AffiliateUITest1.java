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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import affiliate.affiliate.Affiliate;
import affiliate.referral.Referral;
import connection.ConnectToSQL;

import org.eclipse.swt.widgets.Control;

public class AffiliateUITest1 {

	protected Shell shell;
	private Table usersTable;
	private ArrayList<TableItem> items;
	private ConnectToSQL connection;//= new ConnectToSQL();
	Calendar curentDate = Calendar.getInstance(); 

	public AffiliateUITest1() {
		
		Display display = new Display();
		Shell shell = new Shell(display);
		//shell.setLayout(new GridLayout(1, false));

		//Group outerGroup = new Group(shell, SWT.NONE);

		// Tell the group to stretch in all directions
		//outerGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		//GridLayout layout1 = new GridLayout(1, true);
		//		 layout1.verticalSpacing=0;
		//		 layout1.marginBottom=0;

		//outerGroup.setLayout(layout1);
		//outerGroup.setText("Group");


		Label lblNewLabel = new Label(shell, SWT.SINGLE);
		//lblNewLabel.setBackground(SWTResourceManager.getColor());
		lblNewLabel.setFont(SWTResourceManager.getFont("Futura LT", 18, SWT.BOLD));
		//lblNewLabel.setAlignment(SWT.CENTER);
		GridData gridData = new GridData();
		gridData.horizontalSpan = 3;
		gridData.horizontalAlignment = GridData.FILL;
		lblNewLabel.setLayoutData(gridData);
		//lblNewLabel.setBounds(443, 10, 500, 52);
		lblNewLabel.setText("Evidence Based Education - Affiliate");
		//lblNewLabel.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));

		//Group outerGroup1 = new Group(outerGroup, SWT.RESIZE);

		// Tell the group to stretch in all directions
		//outerGroup1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
//		GridLayout layout2 = new GridLayout(3, true);
//		layout2.verticalSpacing=200;
//		layout2.marginBottom=0;
//		layout2.marginHeight=0;
//		layout2.marginTop=0;

		//outerGroup1.setLayout(layout2);
		//outerGroup1.setText("Group");

		//Group tableGroup = new Group(outerGroup, SWT.RESIZE);


		// Tell the group to stretch in all directions
//		GridData gd_tableGroup = new GridData(SWT.CENTER, SWT.CENTER, true, true);
//		gd_tableGroup.widthHint = 559;
//		//tableGroup.setLayoutData(gd_tableGroup);
//		GridLayout layout3 = new GridLayout(1, true);
//		layout3.verticalSpacing=200;
//		layout3.marginTop=0;
//		layout2.verticalSpacing=0;
//		layout2.marginBottom=0;
//		layout2.marginHeight=0;
		//tableGroup.setLayout(layout3);
		//tableGroup.setText("Report");

		Label lblSelectCourseName = new Label(shell, SWT.NONE);
		lblSelectCourseName.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		//lblSelectCourseName.setBackground(shlUpdate.getBackground());
		//lblSelectCourseName.setBounds(479, 88, 175, 15);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, false, false);
		gd.widthHint = 80;
		gd.heightHint = 80;
		lblSelectCourseName.setLayoutData(gd);
		lblSelectCourseName.setText("Affiliate (*)");
		//lblSelectCourseName.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));

		Label lblSelectNewExpiry = new Label(shell, SWT.NONE);
		lblSelectNewExpiry.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		//lblSelectNewExpiry.setBackground(shlUpdate.getBackground());
		GridData gd1 = new GridData(SWT.FILL, SWT.FILL, false, false);
		gd1.widthHint = 80;
		gd1.heightHint = 80;
		lblSelectNewExpiry.setLayoutData(gd1);
		lblSelectNewExpiry.setText("Quarter of the Year");
		//lblSelectNewExpiry.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));

		Label year = new Label(shell, SWT.NONE);
		year.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		year.setText("Year");
		GridData gd2 = new GridData(SWT.FILL, SWT.FILL, false, false);
		gd2.widthHint = 80;
		gd2.heightHint = 80;
		year.setLayoutData(gd2);
		//year.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));


		Combo affiliateNames = new Combo(shell, SWT.NONE);
		affiliateNames.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		affiliateNames.setToolTipText("Select Affiliate from Here");
		//affiliateNames.setBounds(479, 105, 175, 23);
		GridData gd3 = new GridData(SWT.FILL, SWT.FILL, false, false);
		gd3.widthHint = 80;
		gd3.heightHint = 80;
		affiliateNames.setLayoutData(gd3);
		affiliateNames.setText("All Affiliates");
		//affiliateNames.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));

		Map<Long, Affiliate> affiliates = new HashMap<Long, Affiliate>();
		ArrayList<String> affNames = new ArrayList<String>();
		try {
			affiliates = connection.fetchAffiliates();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//connection.getData();
		for(Map.Entry entry:affiliates.entrySet()){
			//System.out.print(entry.getKey() + " : " + entry.getValue());
			affNames.add(((Affiliate) entry.getValue()).getName());
		}
		affNames.add("All Affiliates");
		Collections.sort(affNames);
		for (String name: affNames)  
			affiliateNames.add(name);

		Combo period = new Combo(shell, SWT.NONE);
		period.setItems(new String[] {"All Quarters", "First Quarter (JAN-MAR)", "Second Quarter (APR-JUN)", "Third Quarter (JUL-SEP)", "Fourth Quarter (OCT-DEC)"});
		//groupNames.setTouchEnabled(true);
		period.setText("All Quarters");
		//period.setBounds(675, 105, 180, 23);
		GridData gd4 = new GridData(SWT.FILL, SWT.FILL, false, false);
		gd4.widthHint = 80;
		gd4.heightHint = 80;
		period.setLayoutData(gd4);
		period.setToolTipText("Select Quarter from Here");
		period.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
		//outerGroup1.setTabList(new Control[]{lblSelectCourseName, affiliateNames, period});

		Combo yearCombo = new Combo(shell, SWT.NONE);
		yearCombo.setItems(new String[] {"All Years", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"});
		//yearCombo.setBounds(861, 105, 82, 23);
		yearCombo.setToolTipText("Select Year from Here");
		yearCombo.setText("All Years");
		GridData gd5 = new GridData(SWT.FILL, SWT.FILL, false, false);
		gd5.widthHint = 80;
		gd5.heightHint = 80;
		yearCombo.setLayoutData(gd5);
		yearCombo.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));

		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("Display");
		button_1.setFont(SWTResourceManager.getFont("Futura LT", 10, SWT.BOLD));
		//button_1.setBounds(667, 150, 82, 52);
		button_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);

		usersTable = createTable(usersTable, shell);


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
						//button.setVisible(false);
						usersTable.removeAll();
						//count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);
						//count.setVisible(true);
						//count.setText("Total = "+connection.findTotalCommission());
						//	button.setVisible(true);
						//System.out.println("Check 1");
					}
				}else if(!affiliateNames.getText().equals("All Affiliates") && (period.getText().equals("All Quarters") 
						&& yearCombo.getText().equals("All Years"))) {
					connection.emptyFilteredReferrals();
					connection.findReferralByAffiliateName(affiliateNames.getText());
					if(connection.getFilteredReferrals().isEmpty()) {
						//button.setVisible(false);
						usersTable.removeAll();
						//count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);				
						//	count.setVisible(true);
						//	count.setText("Total = "+connection.findTotalCommission());
						//	button.setVisible(true);
						//System.out.println("Check 2");
					}				
				}else if(affiliateNames.getText().equals("All Affiliates") && (period.getText().equals("All Quarters") 
						&& !yearCombo.getText().equals("All Years"))) {
					connection.emptyFilteredReferrals();
					year = Integer.parseInt(yearCombo.getText());
					connection.findReferralsByGivenYear(year);
					if(connection.getFilteredReferrals().isEmpty()) {
						//	button.setVisible(false);
						usersTable.removeAll();
						//	count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);
						//count.setVisible(true);
						//count.setText("Total = "+connection.findTotalCommission());
						//button.setVisible(true);
						//System.out.println("Check 3");
					}
				}else if(!affiliateNames.getText().equals("All Affiliates") && (period.getText().equals("All Quarters") 
						&& !yearCombo.getText().equals("All Years"))) {
					connection.emptyFilteredReferrals();
					year = Integer.parseInt(yearCombo.getText());
					connection.findReferralsByGivenYear(year);
					connection.findReferralsByGivenYearAndName(affiliateNames.getText());
					if(connection.getFilteredReferrals().isEmpty()) {
						//button.setVisible(false);
						usersTable.removeAll();
						//	count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);
						//	count.setVisible(true);
						//	count.setText("Total = "+connection.findTotalCommission());
						//	button.setVisible(true);
						//System.out.println("Check 4");
					}				
				}else if((affiliateNames.getText().equals("All Affiliates") || !affiliateNames.getText().equals("All Affiliates")) && (!period.getText().equals("All Quarters") 
						&& yearCombo.getText().equals("All Years"))) {
					//button.setVisible(false);
					usersTable.removeAll();
					//count.setVisible(false);
					JOptionPane.showMessageDialog(null, "Please select the year", "Missing the year!",JOptionPane.INFORMATION_MESSAGE);
				}else if((affiliateNames.getText().equals("All Affiliates") || !affiliateNames.getText().equals("All Affiliates")) && (!period.getText().equals("All Quarters") 
						&& !yearCombo.getText().equals("All Years"))) {
					connection.emptyFilteredReferrals();
					year = Integer.parseInt(yearCombo.getText());
					//LocalDate date = LocalDate.of(year, month, dayOfMonth)
					connection.findReferralsByGivenQuarter(affiliateNames.getText(),  period.getText(), year);
					if(connection.getFilteredReferrals().isEmpty()) {
						//	button.setVisible(false);
						usersTable.removeAll();
						//	count.setVisible(false);
						JOptionPane.showMessageDialog(null, "There is no referrals for selected period", "No Referrals",JOptionPane.INFORMATION_MESSAGE);
					}else {
						addItems(usersTable, true);
						//	count.setVisible(true);
						//	count.setText("Total = "+connection.findTotalCommission());
						//	button.setVisible(true);
						//System.out.println("Check 5");
					}	
				}
			}
		});


		shell.setSize(1000,400);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		AffiliateUITest1 affiliateUITest = new AffiliateUITest1();
	}



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
	public Table createTable(Table usersTable, Shell shell) {
		// Table table = new Table(shell, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);

		usersTable = new Table(shell, SWT.CHECK | SWT.BORDER | SWT.FULL_SELECTION |  SWT.NONE);

		usersTable.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		//affiliateNames.setBounds(479, 105, 175, 23);
		GridData gd_usersTable = new GridData(SWT.CENTER, SWT.FILL, true, true);
		gd_usersTable.widthHint = 520;
		usersTable.setLayoutData(gd_usersTable);
		//usersTable.setBackground(SWTResourceManager.getColor(192, 192, 192));
		//usersTable.setHeaderBackground(SWTResourceManager.getColor(128, 128, 128));
		usersTable.setFont(SWTResourceManager.getFont("Futura LT", 9, SWT.NORMAL));
		//usersTable.setBounds(166, 231, 1031, 348);
		usersTable.setHeaderVisible(true);
		usersTable.setLinesVisible(true);

		TableColumn date  = new TableColumn(usersTable, SWT.CENTER);
		TableColumn affiliateName = new TableColumn(usersTable, SWT.CENTER);
		TableColumn customerName = new TableColumn(usersTable, SWT.CENTER);
		TableColumn productNameQuantity = new TableColumn(usersTable, SWT.CENTER);
		TableColumn total = new TableColumn(usersTable, SWT.CENTER);
		TableColumn commission  = new TableColumn(usersTable, SWT.CENTER);
		commission.setWidth(-101);

		//usersTable.deselectAll();

		//affiliateName.setWidth(100);
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

		//affiliateName.setWidth(180);
		//customerName.setWidth(180);
		//productNameQuantity.setWidth(250);
		//total.setWidth(100);
		//date.setWidth(200);
		//commission.setWidth(100);

		//		affiliateName.setResizable(true);
		//		customerName.setResizable(true);
		//		productNameQuantity.setResizable(true);
		//		total.setResizable(true);
		//		date.setResizable(true);
		//		commission.setResizable(true);

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

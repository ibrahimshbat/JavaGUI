package connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import affiliate.affiliate.Affiliate;
import affiliate.referral.Referral;


/*
 * Class connection uses for set up connection to database
 */
public class ConnectToSQL {

	private static Connection connection = null;
	private Statement sqlStatmenet = null;
	private ResultSet result = null;
	private String query=null;
	private final String URLCONNECTION = "jdbc:mysql://evidencebased.education/inj-wp-SLsR3AQ6";
	private ArrayList<Referral> referrals = new ArrayList<Referral>();
	private ArrayList<Referral> filteredReferrals = new ArrayList<Referral>();
//jdbc:mysql://localhost:3306/Peoples?autoReconnect=true&useSSL=false

	Map<Long, Affiliate> affiliates = null;
	private final String DB_USERNAME = "psOSji09o16C";
	private final String DB_PASSWORD = "Mikado5039541!";
	private final double AE_COST = 95;
	private final String ALP_NAME = "Assessment Lead Programme";
	private final String AE_NAME = "Assessment Essentials";

	public ConnectToSQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection(URLCONNECTION, DB_USERNAME, DB_PASSWORD);
			this.sqlStatmenet = this.connection.createStatement();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			affiliates = fetchAffiliates();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void getData() {
		Referral referral;
		
		query = "SELECT r.referral_id, r.affiliate_id, r.description, r.products, r.status, a.rate_type,"
				+ " r.date, ac.first_name, ac.last_name, o.price, u.user_nicename "
				+ "FROM wp_49a5c55d60_3_affiliate_wp_referrals r JOIN wp_49a5c55d60_3_affiliate_wp_customers ac"
				+ " ON r.customer_id =ac. customer_id JOIN wp_49a5c55d60_3_affiliate_wp_affiliates a"
				+ " ON r.affiliate_id=a.affiliate_id\r\n" + 
				"JOIN wp_49a5c55d60_users u ON a.user_id=u.ID JOIN wp_49a5c55d60_3_followup_customer_orders o"
				+ " ON r.reference=o.order_id;\r\n";
		try {
			result = sqlStatmenet.executeQuery(query);

			while(result.next()) {
				referral = new Referral();
				referral.setId(result.getLong("r.referral_id"));
				referral.setAffiliate_id(result.getLong("r.affiliate_id"));
				referral.setAffiliateName(result.getString("u.user_nicename"));
				referral.setCustomerName(result.getString("ac.first_name")+" "+result.getString("ac.last_name"));
				referral.setCourse(findCouceName(result.getString("r.description")));
				referral.setAffiliateRateType(result.getString("a.rate_type"));
				referral.setQuantity(findQuantity(findCouceNameWithQuantity(result.getString("r.description")), result.getString("r.products")));
				//referral.setTotal(result.getDouble("o.price"));
				referral.setTotal(findTotal(result.getString("r.products")));
				referral.setDate(result.getString("r.date").split(" ")[0]);
				referral.setTime(result.getString("r.date").split(" ")[1]);
				referral.setCommission(getCommission(referral, affiliates));
				//System.out.println(result.getString("r.products"));
				//System.out.println(referral.getTotal());
				//System.out.println(result.getString("r.date"));

			//	System.out.println("-----------------------");
				referrals.add(referral);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		filteredReferrals = new ArrayList<Referral>(referrals);
//		LocalDate date = LocalDate.of(2019,01, 28);
//		ArrayList<Referral> filteredReferral = findReferralsByGivenQuarter(date);
//		if (filteredReferral.isEmpty()) {
//			//System.out.println("No Referral in requested Quarter");
//		}else {
////			System.out.println(filteredReferral);
////			System.out.println(filteredReferral.size());
//		}
//		ArrayList<Referral> fr = findReferralByAffiliateName("impactwalesaffiliate");
//		System.out.println(fr);
//		System.out.println(fr.size());
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}
	/*
	 * This method fetches AL(s) who has finished the ALP
	 */
//	public ArrayList<Referral> getReferrals() {
//		ArrayList<Referral> referrals = new ArrayList<Referral>();
//		Referral referral;
//		Course course;
//		query = "SELECT * from wp_49a5c55d60_3_affiliate_wp_referrals";
//		try {
//			result = sqlStatmenet.executeQuery(query);
//
//			while(result.next()) {
//				referral = new Referral();
//				course = new Course();
//				referral.setId(result.getLong("referral_id"));
//				referral.setDescription(result.getString("description"));
//				course.setName(findCouceName(referral.getDescription()));
//				//referral.setCourse(course);
//				referral.setStatus(result.getString("status"));
//				referral.setProduct(result.getString("products"));
//				referral.setDate(result.getString("date"));
//				referral.setQuantity(findQuantity(course.getName(), referral.getProduct()));
//				referral.setTotal(findTotal(referral.getProduct()));
//				referrals.add(referral);
//			}
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//
//		return referrals;
//	}

	public String findCouceNameWithQuantity(String description) {
		String courseName;
		if (description.split(" ").length>2) {
			courseName = description.split("\\(")[0];
			courseName = courseName.substring(0, courseName.length()-1);
			return courseName;
		}else {
			return description;
		}
	}

	public String findCouceName(String description) {
		String courseName;
		if (description.split(" ").length>2) {
			courseName = description.split("-")[0];
			courseName = courseName.substring(0, courseName.length()-1);
			return courseName;
		}else {
			return description;
		}
	}


	public int findQuantity(String courseName, String product) {
		String [] parts;
		//System.out.println("ALP "+product);
		if(courseName.split(" ").length>2) {
			//System.out.println("ALP "+Integer.parseInt(courseName.split(" ")[4]));
			return Integer.parseInt(courseName.split(" ")[4]);
		}else if (courseName.split(" ").length>1) {
			parts = product.split("\"");
			return   (int)(((double) Integer.parseInt(parts[10]))/AE_COST);
		}
		else {
			return 0;
		}
	}

	public double findTotal(String product) {

			double total = 0;
			String [] parts;
			parts = product.split("\"");
			total = Double.parseDouble(parts[10]);
			//System.out.println(total);
			return total;
	}

	public void findReferralsByGivenQuarter(String affiliateName, String quarter, int year){
		LocalDate referralDate;
		LocalDate DateInGivenQuarter = findDateWithinQuarter(quarter, year);

		LocalDate firstDayOfQuarter = findFirstDayOfQuarter(DateInGivenQuarter);
		LocalDate lastDayOfQuarter = findLastDayOfQuarter(firstDayOfQuarter);

		//System.out.println(firstDayOfQuarter);
		//System.out.println(lastDayOfQuarter);
		ArrayList<Referral> filtteredReferral = new ArrayList<Referral>();
		if (affiliateName.equals("All Affiliates")) {
			for(Referral referral:referrals) {
				referralDate = LocalDate.parse(referral.getDate());
				//System.out.println("referralDate="+referralDate);
				if (referralDate.compareTo(firstDayOfQuarter)>=	0 &&
						referralDate.compareTo(lastDayOfQuarter)<=0){
					filtteredReferral.add(referral);
				}
			}
		}
		else {
			for(Referral referral:referrals) {
				referralDate = LocalDate.parse(referral.getDate());
				//System.out.println("referralDate="+referralDate);
				if ((referralDate.compareTo(firstDayOfQuarter)>=	0 &&
						referralDate.compareTo(lastDayOfQuarter)<=0) && affiliateName.equals(referral.getAffiliateName())){
					filtteredReferral.add(referral);
				}
			}
		}
		this.filteredReferrals = new ArrayList<Referral>(filtteredReferral);
		//System.out.println("Filtered Referral="+referralFilttered);
	}
	
	public void findReferralsByGivenYear(int year){
		LocalDate referralDate;
		LocalDate firstDayOfQuarter = findFirstDayOfQuarter(LocalDate.of(year, 02, 01));
		LocalDate lastDayOfQuarter = findLastDayOfQuarter(LocalDate.of(year, 10, 01));

		//System.out.println(firstDayOfQuarter);
		//System.out.println(lastDayOfQuarter);
		ArrayList<Referral> filtReferral = new ArrayList<Referral>();

		for(Referral referral:referrals) {
			referralDate = LocalDate.parse(referral.getDate());
			//System.out.println("referralDate="+referralDate);
			if (referralDate.compareTo(firstDayOfQuarter)>=	0 &&
					referralDate.compareTo(lastDayOfQuarter)<=0){
				filtReferral.add(referral);
			}
		}
		this.filteredReferrals = new ArrayList<Referral>(filtReferral);
	}
	
	
	public void findReferralByAffiliateName(String affiliateName){
		ArrayList<Referral> filtReferral = new ArrayList<Referral>();
		for(Referral referral:referrals) {
			if(referral.getAffiliateName().equals(affiliateName)) {
				filtReferral.add(referral);
				//System.out.println(filtReferral);
			}
		}
		this.filteredReferrals = new ArrayList<Referral>(filtReferral);
	}
	
	public void findReferralsByGivenYearAndName(String affiliateName){
		ArrayList<Referral> filtReferral = new ArrayList<Referral>();
		for(Referral referral:filteredReferrals) {
			if(referral.getAffiliateName().equals(affiliateName)) {
				filtReferral.add(referral);
				//System.out.println(filtReferral);
			}
		}
		this.filteredReferrals = new ArrayList<Referral>(filtReferral);
	}

	public LocalDate findDateWithinQuarter(String quarter, int year) {
		LocalDate date;
		String firstQuarter = "First Quarter (JAN-MAR)";
		String secondQuarter = "Second Quarter (APR-JUN)";
		String thirdQuarter = "Third Quarter (JUL-SEP)";
		String fourthQuarter = "Fourth Quarter (OCT-DEC)";
		if(quarter.equals(firstQuarter)) {
			date = LocalDate.of(year, 02, 01);
		}else if(quarter.equals(secondQuarter)) {
			date = LocalDate.of(year, 05, 01);
		}else if(quarter.equals(thirdQuarter)) {
			date = LocalDate.of(year, 8, 01);
		}else {
			date = LocalDate.of(year, 11, 01);
		}

		return date;
	}
	public LocalDate findFirstDayOfQuarter(LocalDate date) {
		LocalDate firstDayOfQuarter = date.with(date.getMonth().firstMonthOfQuarter())
				.with(TemporalAdjusters.firstDayOfMonth());

		return firstDayOfQuarter;
	}

	public LocalDate findLastDayOfQuarter(LocalDate firstDayOfQuarter) {
		LocalDate lastDayOfQuarter = firstDayOfQuarter.plusMonths(2)
				.with(TemporalAdjusters.lastDayOfMonth());

		return lastDayOfQuarter;
	}

	public double getCommission(Referral referral, Map<Long,Affiliate> affiliates) {
		Affiliate affiliate = affiliates.get(referral.getAffiliate_id());

		double commsion = 0;
		if(referral.getAffiliateRateType().equals("flat")) {
			if(referral.getCourse().equals(ALP_NAME)) {
				if (affiliate.getRateALP() !=0) {
					commsion = referral.getQuantity() * affiliate.getRateALP();
				}else{
					System.out.println("ALP rate for affiliate "+referral.getAffiliateName() 
					+ " hasn't been set");
				}
			}else {
				commsion = referral.getQuantity() * affiliate.getRateAE();
			}
		}else {
			if(referral.getCourse().equals(ALP_NAME)) {
				if (affiliate.getRateALP() !=0) {
					commsion = (affiliate.getRateALP()/100) * referral.getTotal();
				}else{
					System.out.println("ALP rate for affiliate "+referral.getAffiliateName() 
					+ " hasn't been set");
				}
			}else {
				commsion = (affiliate.getRateAE()/100) * referral.getTotal();
			}
		}
		return commsion;
	}



	/**
	 * Fetching Affiliates details from affiliate file
	 * @return
	 */
	public static Map<Long,Affiliate> fetchAffiliates() throws IOException {
		String [] parts;
		Map<Long, Affiliate> affiliates = new HashMap<Long, Affiliate>();
		Affiliate affiliate;
//		//System.out.println(System.getProperty("user.dir"));
//		BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+
//				"/src/resources/affiliate.txt"));
//		
		InputStream input = ConnectToSQL.class.getResourceAsStream("/affiliate.txt");

		BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
		String aff = reader.readLine();
		while (aff != null) {
			parts = aff.split(" ");
			affiliate = new Affiliate((long) Long.parseLong(parts[0]), parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
			affiliates.put(Long.parseLong(parts[0]), affiliate);
			// read next line
			aff = reader.readLine();
		}
		reader.close();
		return affiliates;
	}
	
	public double findTotalCommission() {
		double totlaCommission = 0;
		for(Referral referral : filteredReferrals) {
			totlaCommission+=referral.getCommission();
		}
		return totlaCommission;
		
		
	}
	public void assignAllReferrals() {
		this.filteredReferrals = new ArrayList<Referral>(referrals);
	}
	public static Connection getConnection() {
		return connection;
	}

	public Statement getSqlStatmenet() {
		return sqlStatmenet;
	}

	public ResultSet getResult() {
		return result;
	}

	public ArrayList<Referral> getFilteredReferrals() {
		return filteredReferrals;
	}

	public void emptyFilteredReferrals() {
		this.filteredReferrals.clear();;
	}
	
	public void setFilteredReferrals(ArrayList<Referral> filteredReferrals) {
		this.filteredReferrals = filteredReferrals;
	}

	public void setReferrals(ArrayList<Referral> referrals) {
		this.referrals = referrals;
	}
	public ArrayList<Referral> getReferrals() {
		return referrals;
	}
	
	


}
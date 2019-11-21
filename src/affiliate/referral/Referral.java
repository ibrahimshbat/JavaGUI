package affiliate.referral;


public class Referral {

	private Long id;
	private Long affiliate_id;
	private String affiliateName;
	private String affiliateRateType;
	private String customerName;
	private String description;
	private String product;
	private  String course;
	private int quantity;
	private double total;
	private String status;
	private String date;
	private String time;
	private double commission;
	
	public Referral() {
		
	}
	public Referral(Long id, Long affiliate_id, String affiliateName, String affiliateRateType, String customerName,
			String description, String product, String course, int quantity, double total, String status, String date, String time,
			double commission) {
		this.id = id;
		this.affiliate_id = affiliate_id;
		this.affiliateName = affiliateName;
		this.affiliateRateType = affiliateRateType;
		this.customerName = customerName;
		this.description = description;
		this.product = product;
		this.course = course;
		this.quantity = quantity;
		this.total = total;
		this.status = status;
		this.date = date;
		this.time = time;
		this.commission = commission;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAffiliate_id() {
		return affiliate_id;
	}
	public void setAffiliate_id(Long affiliate_id) {
		this.affiliate_id = affiliate_id;
	}
	public String getAffiliateName() {
		return affiliateName;
	}
	public void setAffiliateName(String affiliateName) {
		this.affiliateName = affiliateName;
	}
	public String getAffiliateRateType() {
		return affiliateRateType;
	}
	public void setAffiliateRateType(String affiliateRateType) {
		this.affiliateRateType = affiliateRateType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((affiliateName == null) ? 0 : affiliateName.hashCode());
		result = prime * result + ((affiliateRateType == null) ? 0 : affiliateRateType.hashCode());
		result = prime * result + ((affiliate_id == null) ? 0 : affiliate_id.hashCode());
		result = prime * result + ((course == null) ? 0 : course.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Referral other = (Referral) obj;
		if (affiliateName == null) {
			if (other.affiliateName != null)
				return false;
		} else if (!affiliateName.equals(other.affiliateName))
			return false;
		if (affiliateRateType == null) {
			if (other.affiliateRateType != null)
				return false;
		} else if (!affiliateRateType.equals(other.affiliateRateType))
			return false;
		if (affiliate_id == null) {
			if (other.affiliate_id != null)
				return false;
		} else if (!affiliate_id.equals(other.affiliate_id))
			return false;
		if (course == null) {
			if (other.course != null)
				return false;
		} else if (!course.equals(other.course))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Referral [id=" + id + ", affiliate_id=" + affiliate_id + ", affiliateName=" + affiliateName
				+ ", affiliateRateType=" + affiliateRateType + ", customerName=" + customerName + ", description="
				+ description + ", product=" + product + ", course=" + course + ", quantity=" + quantity + ", total="
				+ total + ", status=" + status + ", date=" + date + ", time=" + time + ", commission=" + commission
				+ "]";
	}
	
	
}

package affiliate.affiliate;

public class Affiliate {

	private Long id;
	private String name;
	private double rateALP;
	private double rateAE;
	
	public Affiliate() {
		// TODO Auto-generated constructor stub
	}

	public Affiliate(Long id, String name, double rateALP, double rateAE) {
		super();
		this.id = id;
		this.name = name;
		this.rateALP = rateALP;
		this.rateAE = rateAE;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRateALP() {
		return rateALP;
	}

	public void setRateALP(double rateALP) {
		this.rateALP = rateALP;
	}

	public double getRateAE() {
		return rateAE;
	}

	public void setRateAE(double rateAE) {
		this.rateAE = rateAE;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Affiliate other = (Affiliate) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Affiliate [id=" + id + ", name=" + name + ", rateALP=" + rateALP + ", rateAE=" + rateAE + "]";
	}
	
	

}

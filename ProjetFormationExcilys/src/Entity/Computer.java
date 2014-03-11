package Entity;
import java.sql.Date;

import DAO.ComputerDAO;

public class Computer {
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int company_id;
	
	private Company company = null;
	

	public Computer(int id, String name, Date introduced, Date discontinued,
			int company_id) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
	}
	
	public Computer(int id, String name, Date introduced, Date discontinued,
			int company_id, String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company_id = company_id;
		
		company = new Company(company_id, companyName);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntroduced() {
		return introduced;
	}

	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	public Date getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	public int getCompany_id() {
		return company_id;
	}

	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String toString(){
		return "(id: "+id+", name: "+name+", "
				+ "introduced: "+introduced+", discontinued: "+discontinued+", "
						+ "company_id:"+company_id+")";
	}
		
}

import DAO.ComputerDAO;
import Entity.Company;


public class Test {
	public static void main(String[] args){
		ComputerDAO cdao = ComputerDAO.getInstance();
		System.out.println(cdao.selectAllComputer());
		
		//370 --> 13 (IBM)
		//ComputerDAO cdao2 = Computer
		//Company c = cdao.selectCompanyByComputerId(370);
		//System.out.println(c.getName());
	}
}
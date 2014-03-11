import DAO.ComputerDAO;


public class Test {
	public static void main(String[] args){
		ComputerDAO cdao = ComputerDAO.getInstance();
		System.out.println(cdao.selectAllComputer());
	}
}

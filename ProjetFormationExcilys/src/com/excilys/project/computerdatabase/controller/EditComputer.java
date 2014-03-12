package com.excilys.project.computerdatabase.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.project.computerdatabase.domain.Company;
import com.excilys.project.computerdatabase.domain.Computer;
import com.excilys.project.computerdatabase.persistence.CompanyDAO;
import com.excilys.project.computerdatabase.persistence.ComputerDAO;

/**
 * Servlet implementation class AddComputer
 */
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CompanyDAO companyDao = CompanyDAO.getInstance();
	ComputerDAO computerDao = ComputerDAO.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputer() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("computerId");
		
		if(idString != null && idString.length()>0){
			long id = Long.parseLong(idString);
			Computer computer = computerDao.retrieveByComputerId(id);
			if(computer!=null){
				request.setAttribute("computer",computer);
			}
		}
		
		List<Company> allCompany = null;
		allCompany = companyDao.selectAllCompany();
		request.setAttribute("allCompany", allCompany);
		request.getRequestDispatcher("editComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Searching for all companies		
		List<Company> allCompany = null;
		allCompany = companyDao.selectAllCompany();
		request.setAttribute("allCompany", allCompany);
		
		// Parameters searching
		String idString = request.getParameter("idComputer");
		String name = request.getParameter("name");
		String introducedDateString =  request.getParameter("introducedDate");
		String discontinuedDateString =  request.getParameter("discontinuedDate");
		String companyIdString =  request.getParameter("company");
		
		Date introducedDate = null;
		Date discontinuedDate = null;
		Company company = null;
		
		long companyId = Long.parseLong(companyIdString);
		company = companyDao.retrieveByCompanyId(companyId);
		
		long id = Long.parseLong(idString);
		
		String error = ""; 
		if(introducedDateString!=null && introducedDateString.length()>0){
			introducedDate = UsefulFunctions.stringToDate(introducedDateString);
			if(introducedDate == null){
				error += "Introduced date is not correct ("+introducedDateString+")<br/>";
			}
		}
		if(discontinuedDateString!=null && discontinuedDateString.length()>0){
			discontinuedDate = UsefulFunctions.stringToDate(discontinuedDateString);
			if(introducedDate == null){
				error += "Discontinued date is not correct ("+discontinuedDateString+")<br/>";
			}
		}
		
		if( name!=null && name.length()>0 ){
			if(error.length()==0){
				Computer computer = new Computer(id,name,introducedDate,discontinuedDate,company);
				computerDao.updateComputer(computer);
				String message = "Computer modified";
				request.setAttribute("message", message);
			}
		}else{
			error += "Computer name is required.";
		}
		
		if(error.length()>0){
			request.setAttribute("error", error);
		}
		
		if(idString != null && idString.length()>0){
			long idRetrieve = Long.parseLong(idString);
			Computer computer = computerDao.retrieveByComputerId(idRetrieve);
			if(computer!=null){
				request.setAttribute("computer",computer);
			}
		}
		
		request.getRequestDispatcher("editComputer.jsp").forward(request, response);
	}

}

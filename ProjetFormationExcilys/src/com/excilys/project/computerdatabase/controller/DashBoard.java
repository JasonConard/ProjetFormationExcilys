package com.excilys.project.computerdatabase.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.project.computerdatabase.domain.Computer;
import com.excilys.project.computerdatabase.persistence.ComputerDAO;

/**
 * Servlet implementation class DashBoard
 */
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	ComputerDAO computerDao = ComputerDAO.getInstance();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoard() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Deleting managment */
		String idString = request.getParameter("computer");
		String delete = request.getParameter("delete");
		if(idString != null && delete!=null && delete.equals("delete")){
			long id = Long.parseLong(idString);
			computerDao.delete(id);
		}
		
		/* Searching managment */
		String search = request.getParameter("search");
		List<Computer> allComputer = null;
		if(search == null || search.length()==0){
			allComputer = computerDao.selectAllComputerWithCompanyName();
		}else{
			allComputer = computerDao.selectAllComputerWithCompanyNameLike(search);
		}
		//System.out.println(allComputer);
		request.setAttribute("allComputer", allComputer);
		request.setAttribute("nbComputer", allComputer.size());
		
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
}

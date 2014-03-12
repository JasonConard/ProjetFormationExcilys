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

	ComputerDAO cdao = ComputerDAO.getInstance();
	
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
		String search = request.getParameter("search");
		List<Computer> allComputer = null;
		if(search.length()==0 || search == null){
			allComputer = cdao.selectAllComputerWithCompanyName();
		}else{
			allComputer = cdao.selectAllComputerWithCompanyNameLike(search);
		}
		//System.out.println(allComputer);
		request.setAttribute("allComputer", allComputer);
		request.setAttribute("nbComputer", allComputer.size());
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}
}

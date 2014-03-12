package com.excilys.project.computerDatabase.servlets;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.project.computerDatabase.dao.ComputerDAO;
import com.excilys.project.computerDatabase.domain.Computer;

/**
 * Servlet implementation class MaServletTest
 */
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req = request.getParameter("search");
		ArrayList<Computer> allComputer = null;
		if(req == null){
			ComputerDAO cdao = ComputerDAO.getInstance();
			allComputer = cdao.selectAllComputerWithCompanyName();
		}else{
			ComputerDAO cdao = ComputerDAO.getInstance();
			allComputer = cdao.selectAllComputerWithCompanyNameLike(req);
		}
		//System.out.println(allComputer);
		request.setAttribute("allComputer", allComputer);
		request.setAttribute("nbComputer", allComputer.size());
		request.getRequestDispatcher("dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}

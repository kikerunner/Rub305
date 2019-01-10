package es.salesianos.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

import es.salesianos.model.Persona;
import es.salesianos.repository.ActoresRepository;

public class InsertarPersona extends HttpServlet{
	
	ActoresRepository repository = new ActoresRepository();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		Persona persona = new Persona();
		persona.setNombre(req.getParameter("name"));
		String dateInString = req.getParameter("anonacimiento");
		java.util.Date date = new java.util.Date();
		java.util.Date parse = SimpleDateFormat.parse(dateInString);
		date = new java.sql.Date(date.getTime());
		persona.setAnonacimiento(date);

		persona.setCurso(Integer.parseInt(req.getParameter("curso")));
		repository.insert(persona);
		redirect(req, resp, "/insertar.jsp");
	}
	
	protected void redirect(HttpServletRequest req, HttpServletResponse resp, String jsp) throws IOException, ServletException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(req, resp);
	}

}

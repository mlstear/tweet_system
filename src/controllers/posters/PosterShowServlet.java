package controllers.posters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Poster;
import utils.DBUtil;

/**
 * Servlet implementation class PosterShowServlet
 */
@WebServlet("/posters/show")
public class PosterShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PosterShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Poster p = em.find(Poster.class,Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("poster", p);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posters/show.jsp");
        rd.forward(request, response);
    }

}

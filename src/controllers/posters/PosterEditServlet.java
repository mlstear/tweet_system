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
 * Servlet implementation class PosterEditServlet
 */
@WebServlet("/posters/edit")
public class PosterEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PosterEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Poster p = em.find(Poster.class, Integer.parseInt(request.getParameter("id")));

        em.close();

        request.setAttribute("poster", p);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("poster_id", p.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posters/edit.jsp");
        rd.forward(request, response);
    }

}

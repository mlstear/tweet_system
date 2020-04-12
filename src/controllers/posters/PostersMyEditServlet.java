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
 * Servlet implementation class PostersMyEditServlet
 */
@WebServlet("/posters/myEdit")
public class PostersMyEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostersMyEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Poster login_poster = (Poster)request.getSession().getAttribute("login_poster");

        em.close();

        request.setAttribute("login_poster", login_poster);
        request.setAttribute("_token", request.getSession().getId());
        request.getSession().setAttribute("login_poster_id", login_poster.getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/posters/myEdit.jsp");
        rd.forward(request, response);
    }

}

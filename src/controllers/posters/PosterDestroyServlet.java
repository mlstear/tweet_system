package controllers.posters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Poster;
import utils.DBUtil;

/**
 * Servlet implementation class PosterDestroyServlet
 */
@WebServlet("/posters/destroy")
public class PosterDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PosterDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token=(String)request.getParameter("_token");
        if(_token!=null&&_token.equals(request.getSession().getId())){
            EntityManager em=DBUtil.createEntityManager();

            Poster p =em.find(Poster.class , (Integer)(request.getSession().getAttribute("poster_id")));

            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "削除が完了しました。");
            request.getSession().removeAttribute("poster_id");

            response.sendRedirect(request.getContextPath()+"/posters/index");

        }
    }

}

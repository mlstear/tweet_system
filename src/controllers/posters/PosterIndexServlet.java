package controllers.posters;

import java.io.IOException;
import java.util.List;

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
 * Servlet implementation class PosterIndexServlet
 */
@WebServlet("/posters/index")
public class PosterIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PosterIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        int page=1;
        try{
            page=Integer.parseInt(request.getParameter("page"));
        }catch(NumberFormatException e){}

        List<Poster>posters=em.createNamedQuery("getAllPosters",Poster.class)
                              .setFirstResult(20*(page-1))
                              .setMaxResults(20)
                              .getResultList();

        long posters_count=(long)em.createNamedQuery("getPostersCount",Long.class)
                                   .getSingleResult();
        em.close();

        request.setAttribute("posters", posters);
        request.setAttribute("posters_count", posters_count);
        request.setAttribute("page", page);
        if(request.getSession().getAttribute("flush")!=null){
            request.setAttribute("flush",request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/posters/index.jsp");
        rd.forward(request,response);



    }

}

package controllers.tweets;

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
import models.Tweet;
import utils.DBUtil;

/**
 * Servlet implementation class TweetsUserIndexServlet
 */
@WebServlet("/tweets/userIndex")
public class TweetsUserIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsUserIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em=DBUtil.createEntityManager();

        Poster p=em.find(Poster.class, Integer.parseInt(request.getParameter("id")));

        int page;
        try{
            page=Integer.parseInt(request.getParameter("page"));
        }catch(Exception t){
            page=1;
        }
        List<Tweet> tweets=em.createNamedQuery("getUserAllTweets",Tweet.class)
                             .setParameter("poster", p)
                             .setFirstResult(30*(page-1))
                             .setMaxResults(30)
                             .getResultList();

        long tweets_count=(long)em.createNamedQuery("getUserTweetsCount",Long.class)
                                  .setParameter("poster", p)
                                  .getSingleResult();

        em.close();
        request.getSession().setAttribute("posterName", p.getName());
        request.setAttribute("tweets", tweets);
        request.setAttribute("tweets_count", tweets_count);
        request.setAttribute("page", page);
        request.getSession().removeAttribute("poster_id");

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/tweets/userIndex.jsp");
        rd.forward(request, response);
    }

}

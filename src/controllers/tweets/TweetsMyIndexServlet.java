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
 * Servlet implementation class TweetsMyIndexServlet
 */
@WebServlet("/tweets/myIndex")
public class TweetsMyIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsMyIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em=DBUtil.createEntityManager();

        Poster login_poster=(Poster)request.getSession().getAttribute("login_poster");

        int page;
        try{
            page=Integer.parseInt(request.getParameter("page"));
        }catch(Exception e){
            page=1;
        }
        List<Tweet>tweets=em.createNamedQuery("getUserAllTweets",Tweet.class)
                            .setParameter("poster",login_poster)
                            .setFirstResult(30*(page-1))
                            .setMaxResults(30)
                            .getResultList();

        long tweets_count=(long)em.createNamedQuery("getUserTweetsCount",Long.class)
                                  .setParameter("poster", login_poster)
                                  .getSingleResult();

        em.close();

        request.setAttribute("tweets",tweets);
        request.setAttribute("tweets_count", tweets_count);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/tweets/myIndex.jsp");
        rd.forward(request, response);
    }

}

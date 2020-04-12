package controllers.tweets;

import java.io.IOException;

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
 * Servlet implementation class TweetsUserShowServlet
 */
@WebServlet("/tweets/userShow")
public class TweetsUserShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsUserShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();

        Tweet t=em.find(Tweet.class,Integer.parseInt(request.getParameter("id")));

        em.close();

        Poster login_poster=(Poster)request.getSession().getAttribute("login_poster");
        if(t!=null&&login_poster.getId()==t.getPoster().getId()){
            request.setAttribute("tweet", t);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("tweet_id2",t.getId());
            request.getSession().setAttribute("poster_user", t.getPoster());
        }

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/tweets/userShow.jsp");
        rd.forward(request, response);


    }

}

package controllers.tweets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Poster;
import models.Tweet;
import utils.DBUtil;

/**
 * Servlet implementation class TweetsUserDestroyServlet
 */
@WebServlet("/tweets/userDestroy")
public class TweetsUserDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsUserDestroyServlet() {
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

            Tweet t=em.find(Tweet.class, (Integer)(request.getSession().getAttribute("tweet_id2")));
            Poster p=(Poster)request.getSession().getAttribute("poster_user");



            em.getTransaction().begin();;
            em.remove(t);
            em.getTransaction().commit();
            em.close();
            request.getSession().removeAttribute("tweet_id2");

            request.getSession().setAttribute("flush", "削除が完了しました。");

            response.sendRedirect(request.getContextPath()+"/tweets/userIndex?id="+p.getId());
        }
    }

}

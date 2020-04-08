package controllers.tweets;

import java.io.IOException;
import java.sql.Timestamp;
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
import models.validators.TweetValidator;
import utils.DBUtil;

/**
 * Servlet implementation class TweetsCreateServlet
 */
@WebServlet("/tweets/create")
public class TweetsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token=(String)request.getParameter("_token");
        if(_token!=null&&_token.equals(request.getSession().getId())){
            EntityManager em =DBUtil.createEntityManager();

            Tweet t=new Tweet();

            t.setPoster((Poster)request.getSession().getAttribute("login_poster"));

            t.setContent(request.getParameter("content"));

            t.setCreated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors=TweetValidator.validate(t);
            if(errors.size()>0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("tweet", t);
                request.setAttribute("errors", errors);

                RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/tweets/new.jsp");
                rd.forward(request, response);
            }else{
                em.getTransaction().begin();
                em.persist(t);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "投稿が完了しました。");

                response.sendRedirect(request.getContextPath()+"/index.html");
            }
        }
    }

}

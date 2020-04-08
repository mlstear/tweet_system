package controllers.tweets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Tweet;

/**
 * Servlet implementation class TweetsNewServlet
 */
@WebServlet("/tweets/new")
public class TweetsNewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TweetsNewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("_token", request.getSession().getId());

        Tweet t = new Tweet();
        request.setAttribute("tweet", t);

        RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/tweets/new.jsp");
        rd.forward(request, response);
    }

}

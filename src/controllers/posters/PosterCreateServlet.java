package controllers.posters;

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
import models.validators.PosterValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class PosterCreateServlet
 */
@WebServlet("/posters/create")
public class PosterCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PosterCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token =(String)request.getParameter("_token");
        if(_token!=null&&_token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Poster p =new Poster();

            p.setName(request.getParameter("name"));
            p.setPassword(
                    EncryptUtil.getPasswordEncrypt(
                            request.getParameter("password"),
                            (String)this.getServletContext().getAttribute("salt")
                            )
                    );
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            p.setCreated_at(currentTime);
            p.setUpdated_at(currentTime);

            List<String> errors=PosterValidator.validate(p, true, true);
            if(errors.size()>0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("poster", p);
                request.setAttribute("errors",errors);

                RequestDispatcher rd =request.getRequestDispatcher("WEB-INF/views/posters/new.jsp");
                rd.forward(request, response);

            }else{
                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "登録が完了しました。");
                request.getSession().setAttribute("login_poster", p);
                response.sendRedirect(request.getContextPath()+"/index.html");
            }


        }
    }

}

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
 * Servlet implementation class PosterUpdateServlet
 */
@WebServlet("/posters/update")
public class PosterUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PosterUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token=(String)request.getParameter("_token");
        if(_token !=null&&_token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Poster p = em.find(Poster.class , (Integer)(request.getSession().getAttribute("poster_id")));

            Boolean name_duplicate_check_flag=true;
            if(p.getName().equals(request.getParameter("name"))){
                name_duplicate_check_flag=false;
            }else{
                p.setName(request.getParameter("name"));
            }

            Boolean password_check_flag=true;
            String password=request.getParameter("password");
            if(password==null||password.equals("")){
                password_check_flag=false;
            }else{
                p.setPassword(
                        EncryptUtil.getPasswordEncrypt(password, (String)this.getServletContext().getAttribute("salt")
                        )
                        );
            }
            p.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            List<String> errors=PosterValidator.validate(p, name_duplicate_check_flag, password_check_flag);
            if(errors.size()>0){
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("poster",p);
                request.setAttribute("errors", errors);

                RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/posters/edit.jsp");
                rd.forward(request, response);

            }else{
                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();
                request.getSession().setAttribute("flush", "更新が完了しました。");
                request.getSession().removeAttribute("poster_id");

                response.sendRedirect(request.getContextPath()+"/posters/index");
            }

        }
    }

}

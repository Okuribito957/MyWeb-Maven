package servlet;

import dao.ClazzDao;
import dao.DaoFactory;
import entity.ClazzBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ManageClazzessServlet",urlPatterns = "/manageclazzesservlet")
public class ManageClazzessServlet extends HttpServlet {

    private ClazzDao dao = DaoFactory.getClazzDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hidOperationType = request.getParameter("hidOperationType");
        if (hidOperationType != null && hidOperationType.compareTo("add") == 0){
            String txtNumber = request.getParameter("txtNumber");
            String txtName = request.getParameter("txtName");
            Date txtBeginDate = Date.valueOf(request.getParameter("txtBeginDate"));
            Date txtEndDate = Date.valueOf(request.getParameter("txtEndDate"));
            int txtStudentCount = Integer.valueOf(request.getParameter("txtStudentCount"));
            ClazzBean clazz = new ClazzBean();
            clazz.setNumber(txtNumber);
            clazz.setName(txtName);
            clazz.setBeginDate(txtBeginDate);
            clazz.setEndDate(txtEndDate);
            clazz.setStudentCount(txtStudentCount);
            if (dao.insert(clazz) > 0){
                request.setAttribute("message","添加成功！");
            }else {
                request.setAttribute("message","添加失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("remove") == 0){
            int clazzId = Integer.valueOf(request.getParameter("hidClazzId"));
            if (dao.delete(clazzId) > 0){
                request.setAttribute("message2","删除成功！");
            }else{
                request.setAttribute("message2","删除失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("modify") == 0){
            int clazzId = Integer.valueOf(request.getParameter("hidClazzId"));
            ClazzBean clazz = dao.select(clazzId);

            if (clazz != null){
                request.setAttribute("modifyClazz",clazz);
            }else{
                request.setAttribute("message","未找到班级信息，修改失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("cancelSave") == 0){
        }else if (hidOperationType != null && hidOperationType.compareTo("save") == 0){
            int hidClazzId = Integer.valueOf(request.getParameter("hidClazzId"));
            String txtNumber = request.getParameter("txtNumber");
            String txtName = request.getParameter("txtName");
            Date txtBeginDate = Date.valueOf(request.getParameter("txtBeginDate"));
            Date txtEndDate = Date.valueOf(request.getParameter("txtEndDate"));
            int txtStudentCount = Integer.valueOf(request.getParameter("txtStudentCount"));

            ClazzBean clazz = new ClazzBean();
            clazz.setClazzId(hidClazzId);
            clazz.setNumber(txtNumber);
            clazz.setName(txtName);
            clazz.setBeginDate(txtBeginDate);
            clazz.setEndDate(txtEndDate);
            clazz.setStudentCount(txtStudentCount);

            if (dao.update(clazz) > 0){
                request.setAttribute("message","修改成功！");
            }else {
                request.setAttribute("message","修改失败！");
            }
        }
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hidFieldName = "ClazzId";
        if (request.getParameter("hidFieldName") != null){
            hidFieldName = request.getParameter("hidFieldName");
        }
        request.setAttribute("fieldName",hidFieldName);
        String txtSearchNumber = "";
        String txtSearchName = "";
        if (request.getParameter("txtSearchNumber") != null){
            txtSearchNumber = request.getParameter("txtSearchNumber");
        }
        if (request.getParameter("txtSearchName") != null){
            txtSearchName = request.getParameter("txtSearchName");
        }
        request.setAttribute("searchNumber",txtSearchNumber);
        request.setAttribute("searchName",txtSearchName);
        List<ClazzBean> clazzes = dao.select(txtSearchNumber,txtSearchName,hidFieldName);
        //分页
        int rowCount = clazzes.size();
        int pageSize = 5;
        int pageCount = rowCount / pageSize + (rowCount % pageSize == 0 ? 0 :1);
        int currentPageIndex = 1;
        int begin = 0;
        int end = 0;

        if(request.getParameter("hidCurrentPageIndex") != null){
            currentPageIndex = Integer.valueOf(request.getParameter("hidCurrentPageIndex"));
        }
        if (pageCount == 0){
            pageCount = 1;
        }
        if (currentPageIndex < 1){
            currentPageIndex = 1;
        }
        if (currentPageIndex > pageCount){
            currentPageIndex = pageCount;
        }
        begin = pageSize * (currentPageIndex - 1) + 1;
        end = pageSize * currentPageIndex;
        request.setAttribute("rowCount",rowCount);
        request.setAttribute("pageSize",pageSize);
        request.setAttribute("pageCount",pageCount);
        request.setAttribute("currentPageIndex",currentPageIndex);
        request.setAttribute("begin",begin);
        request.setAttribute("end",end);

        request.setAttribute("clazzes",clazzes);
        request.getRequestDispatcher("ManageClazzes.jsp").forward(request,response);
    }
}

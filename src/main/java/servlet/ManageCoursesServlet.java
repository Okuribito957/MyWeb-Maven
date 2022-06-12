package servlet;

import dao.DaoFactory;
import entity.ClazzBean;
import entity.CourseBean;
import dao.CourseDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ManageCoursesServlet",urlPatterns = "/managecoursesservlet")
public class ManageCoursesServlet extends HttpServlet {
    private CourseDao dao = DaoFactory.getCourseDao();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hidOperationType = request.getParameter("hidOperationType");

        if (hidOperationType != null && hidOperationType.compareTo("add") == 0){
            String txtNumber = request.getParameter("txtNumber");
            String txtName = request.getParameter("txtName");
            int txtTerm = Integer.valueOf(request.getParameter("txtTerm"));
            float txtCredit = Float.valueOf(request.getParameter("txtCredit"));
            int txtHourse = Integer.valueOf(request.getParameter("txtHourse"));
            String txtDescription = request.getParameter("txtDescription");

            CourseBean course = new CourseBean();
            course.setNumber(txtNumber);
            course.setName(txtName);
            course.setTerm(txtTerm);
            course.setCredit(txtCredit);
            course.setHourse(txtHourse);
            course.setDescription(txtDescription);

            if (dao.insert(course) > 0){
                request.setAttribute("message","添加成功！");
            }else {
                request.setAttribute("message","添加失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("remove") == 0){
            int courseId = Integer.valueOf(request.getParameter("hidCourseId"));
            if (dao.delete(courseId) > 0){
                request.setAttribute("message2","删除成功！");
            }else{
                request.setAttribute("message2","删除失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("modify") == 0){
            int courseId = Integer.valueOf(request.getParameter("hidCourseId"));
            CourseBean course = dao.select(courseId);

            if (course != null){
                request.setAttribute("modifyCourse",course);
            }else{
                request.setAttribute("message","未找到课程信息，修改失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("cancelSave") == 0){
        }else if (hidOperationType != null && hidOperationType.compareTo("save") == 0){
            int hidCourseId = Integer.valueOf(request.getParameter("hidCourseId"));
            String txtNumber = request.getParameter("txtNumber");
            String txtName = request.getParameter("txtName");
            int txtTerm = Integer.valueOf(request.getParameter("txtTerm"));
            float txtCredit = Float.valueOf(request.getParameter("txtCredit"));
            int txtHourse = Integer.valueOf(request.getParameter("txtHourse"));
            String txtDescription = request.getParameter("txtDescription");

            CourseBean course = new CourseBean();
            course.setCourseId(hidCourseId);
            course.setNumber(txtNumber);
            course.setName(txtName);
            course.setTerm(txtTerm);
            course.setCredit(txtCredit);
            course.setHourse(txtHourse);
            course.setDescription(txtDescription);

            if (dao.update(course) > 0){
                request.setAttribute("message","修改成功！");
            }else {
                request.setAttribute("message","修改失败！");
            }
        }
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hidFieldName = "CourseId";
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
        List<CourseBean> courses = dao.select(txtSearchNumber,txtSearchName,hidFieldName);
        //分页
        int rowCount = courses.size();
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

        request.setAttribute("courses",courses);
        request.getRequestDispatcher("ManageCourses.jsp").forward(request,response);




//        List<CourseBean> courses = dao.select();
//        request.setAttribute("courses",courses);
//
//        request.getRequestDispatcher("ManageCourses.jsp").forward(request,response);
    }
}

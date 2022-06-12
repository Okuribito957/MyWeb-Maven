package servlet;

import dao.DaoFactory;
import dao.StudentDao;
import dao.TeacherDao;
import entity.ClazzBean;
import entity.TeacherBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ManageTeachersServlet",urlPatterns = "/manageteachersservlet")
public class ManageTeachersServlet extends HttpServlet {

    private TeacherDao dao = DaoFactory.getTeacherDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hidOperationType = request.getParameter("hidOperationType");
        if (hidOperationType != null && hidOperationType.compareTo("add") == 0){
            String txtNumber = request.getParameter("txtNumber");
            String txtName = request.getParameter("txtName");
            String radSex = request.getParameter("radSex");
            Date txtBirthday = Date.valueOf(request.getParameter("txtBirthday"));
            String txtPhoneNumber = request.getParameter("txtPhoneNumber");
            String txtAddress = request.getParameter("txtAddress");
            int selClazzId = Integer.valueOf(request.getParameter("selClazzId"));

            TeacherBean teacher = new TeacherBean();
            teacher.setNumber(txtNumber);
            teacher.setName(txtName);
            teacher.setSex(radSex);
            teacher.setBirthday(txtBirthday);
            teacher.setPhoneNumber(txtPhoneNumber);
            teacher.setAddress(txtAddress);
            teacher.setClazzId(selClazzId);
            if (dao.insert(teacher) >0){
                request.setAttribute("message","添加成功！");
            }else {
                request.setAttribute("message","添加失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("remove") == 0){
            int teacherId = Integer.valueOf(request.getParameter("hidTeacherId"));
            if (dao.delete(teacherId) > 0){
                request.setAttribute("message","删除成功！");
            }else {
                request.setAttribute("message","删除失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("modify") == 0){
            int teacherId = Integer.valueOf(request.getParameter("hidTeacherId"));
            TeacherBean teacher = dao.select(teacherId);
            if (teacher != null){
                request.setAttribute("modifyTeacher",teacher);
            }else {
                request.setAttribute("message","未找到教师信息无法修改");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("save") == 0){
            int hidTeacherId = Integer.valueOf(request.getParameter("hidTeacherId"));
            String txtNumber = request.getParameter("txtNumber");
            String txtName = request.getParameter("txtName");
            String radSex = request.getParameter("radSex");
            Date txtBirthday = Date.valueOf(request.getParameter("txtBirthday"));
            String txtPhoneNumber = request.getParameter("txtPhoneNumber");
            String txtAddress = request.getParameter("txtAddress");
            int selClazzId = Integer.valueOf(request.getParameter("selClazzId"));

            TeacherBean teacher = new TeacherBean();
            teacher.setTeacherId(hidTeacherId);
            teacher.setNumber(txtNumber);
            teacher.setName(txtName);
            teacher.setSex(radSex);
            teacher.setBirthday(txtBirthday);
            teacher.setPhoneNumber(txtPhoneNumber);
            teacher.setAddress(txtAddress);
            teacher.setClazzId(selClazzId);

            if (dao.update(teacher) > 0){
                request.setAttribute("message","修改成功");
            }else{
                request.setAttribute("message","修改失败");
            }
        }
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hidFieldName = "TeacherId";
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
        List<TeacherBean> teachers = dao.select(txtSearchNumber,txtSearchName,hidFieldName);
        //分页
        int rowCount = teachers.size();
        int pageSize = 5;
        int pageCount = rowCount / pageSize + (rowCount % pageSize == 0 ? 0 :1);
        int currentPageIndex = 1;
        int begin = 0;
        int end = 0;

        if(request.getParameter("hidCurrentPageIndex") != null){
            currentPageIndex = Integer.valueOf(request.getParameter("hidCurrentPageIndex"));
        }
        if (pageCount < 1){
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

        request.setAttribute("teachers",teachers);
        request.setAttribute("clazzes",DaoFactory.getClazzDao().select());
        request.getRequestDispatcher("ManageTeachers.jsp").forward(request,response);
    }
}

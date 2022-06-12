package servlet;

import dao.DaoFactory;
import dao.StudentDao;
import entity.StudentBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "ManageStudentsServlet",urlPatterns = "/managestudentsservlet")
public class ManageStudentsServlet extends HttpServlet {

    private StudentDao dao = DaoFactory.getStudentDao();

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

            StudentBean student = new StudentBean();
            student.setNumber(txtNumber);
            student.setName(txtName);
            student.setSex(radSex);
            student.setBirthday(txtBirthday);
            student.setPhoneNumber(txtPhoneNumber);
            student.setAddress(txtAddress);
            student.setClazzId(selClazzId);
            if (dao.insert(student) >0){
                request.setAttribute("message","添加成功！");
            }else {
                request.setAttribute("message","添加失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("remove") == 0){
            int studentId = Integer.valueOf(request.getParameter("hidStudentId"));
            if (dao.delete(studentId) > 0){
                request.setAttribute("message","删除成功！");
            }else {
                request.setAttribute("message","删除失败！");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("modify") == 0){
            int studentId = Integer.valueOf(request.getParameter("hidStudentId"));
            StudentBean student = dao.select(studentId);
            if (student != null){
                request.setAttribute("modifyStudent",student);
            }else {
                request.setAttribute("message","未找到学生信息无法修改");
            }
        }else if (hidOperationType != null && hidOperationType.compareTo("save") == 0){
            int hidStudentId = Integer.valueOf(request.getParameter("hidStudentId"));
            String txtNumber = request.getParameter("txtNumber");
            String txtName = request.getParameter("txtName");
            String radSex = request.getParameter("radSex");
            Date txtBirthday = Date.valueOf(request.getParameter("txtBirthday"));
            String txtPhoneNumber = request.getParameter("txtPhoneNumber");
            String txtAddress = request.getParameter("txtAddress");
            int selClazzId = Integer.valueOf(request.getParameter("selClazzId"));

            StudentBean student = new StudentBean();
            student.setStudentId(hidStudentId);
            student.setNumber(txtNumber);
            student.setName(txtName);
            student.setSex(radSex);
            student.setBirthday(txtBirthday);
            student.setPhoneNumber(txtPhoneNumber);
            student.setAddress(txtAddress);
            student.setClazzId(selClazzId);

            if (dao.update(student) > 0){
                request.setAttribute("message","修改成功");
            }else{
                request.setAttribute("message","修改失败");
            }
        }
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String hidFieldName = "StudentId";
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
        List<StudentBean> students = dao.select(txtSearchNumber,txtSearchName,hidFieldName);
        //分页
        int rowCount = students.size();
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

        request.setAttribute("students",students);
        request.setAttribute("clazzes",DaoFactory.getClazzDao().select());
        request.getRequestDispatcher("ManageStudents.jsp").forward(request,response);
    }
}

<%@page contentType="text/xml" pageEncoding="UTF-8" import="source.*"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<!DOCTYPE html>
<page title="Index Page">
    <%
            String answer = request.getParameter("delete");
            if (answer != null) {
                String filePath;
                if (session.getAttribute("user") instanceof Student) {
                    filePath = application.getRealPath("WEB-INF/students.xml");
        %>
        <jsp:useBean id="StudentApp" class="source.StudentApp" scope="application">
            <jsp:setProperty name="StudentApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
                    Students students = StudentApp.getStudents();
                    students.removeStudent((Student) session.getAttribute("user"));
                    StudentApp.updateStudents(students, filePath);
                }
                else {
                    filePath = application.getRealPath("WEB-INF/tutors.xml");
        %>
        <jsp:useBean id="TutorApp" class="source.TutorApp" scope="application">
            <jsp:setProperty name="TutorApp" property="filePath" value="<%=filePath%>"/>
        </jsp:useBean>
        <%
                    Tutors tutors = TutorApp.getTutors();
                    tutors.removeTutor((Tutor) session.getAttribute("user"));
                    TutorApp.updateTutors(tutors, filePath);
                }
            }
            session.invalidate();
        %>
    <link to="register.jsp">Register</link>
    <link to="login.jsp">Login</link>
</page>

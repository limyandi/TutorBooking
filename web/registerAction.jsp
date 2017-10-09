<%@page contentType="text/xml" pageEncoding="UTF-8" import="source.*"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<%-- 
    Document   : registerAction
    Created on : Sep 13, 2017, 3:06:10 PM
    Author     : limyandivicotrico
--%>
<page title="Process Register Page">
    <navnonauth/>
        <%
            String fname = request.getParameter("Fname");
            String lname = request.getParameter("Lname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String dob = request.getParameter("dob");
            String userType = request.getParameter("usertype");
        %>
        <jsp:useBean id="registerHandler" class="uts.wsd.registerlogin.Register" scope="request">
        </jsp:useBean>
        <%
            registerHandler.setFirstname(fname);
            registerHandler.setLastname(lname);
            registerHandler.setEmail(email);
            registerHandler.setPassword(password);
            if(!registerHandler.validateRegister()) {
                session.setAttribute("registerData", registerHandler);
                response.sendRedirect("register.jsp");
            }
            else {
                String studentFilePath = application.getRealPath("WEB-INF/students.xml");
                String tutorFilePath = application.getRealPath("WEB-INF/tutors.xml");
        %>
        <jsp:useBean id="userApp" type="source.UserDao" class="source.UserApp" scope="application">
            <jsp:setProperty name="userApp" property="studentFilePath" value="<%=studentFilePath%>"/>
            <jsp:setProperty name="userApp" property="tutorFilePath" value="<%=tutorFilePath%>"/>
        </jsp:useBean>      
        <% 
                Student student = userApp.readStudent(email);
                Tutor tutor = userApp.readTutor(email);
                if(student == null && tutor == null) {
                    if(userType.equals("student")) {
                        student = new Student(fname, lname, email, password, dob, userType);
                        session.setAttribute("user", student);
                        userApp.addStudent(student); 
                    }
                    else {
                        String specialty = request.getParameter("specialty");
                        tutor = new Tutor(fname, lname, email, password, dob, userType, specialty, "available");
                        session.setAttribute("user", tutor);
                        userApp.addTutor(tutor);
                    }
                    response.sendRedirect("main.jsp");
                }
                else {
        %>
        <link to="register.jsp" label="User with that email already exists, Click to ">Register again</link>
        <%      } 
            }
        %>
</page>

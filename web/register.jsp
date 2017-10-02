<%-- 
    Document   : register
    Created on : Oct 1, 2017, 6:59:04 PM
    Author     : limyandivicotrico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="uts.wsd.registerlogin.Register"%>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript">
            function userType(type) {
                var specialty = document.getElementById('specialty');
                var specialtylabel = document.getElementById('specialtylabel');
                if(type === 'tutor') {
                    specialty.style.display='block';
                    specialtylabel.style.display='block';
                }
                else {
                    specialty.style.display='none';
                    specialtylabel.style.display='none';
                }
            }
        </script>
        <title>Register Page</title>
    </head>
    <body>
        <% 
            Register register = new Register();
            if(session.getAttribute("registerData") != null) {
                register = (Register) session.getAttribute("registerData");
            }
        %>
        <h2>Register Page</h2>
        <form method="post" action="registerAction.jsp">
            <table>
                <tr>
                    <td>First Name:</td><td><input type="text" name="Fname" value="<%=register.getFirstname()%>"></td>
                    <td><%=register.getErrorMessage("firstname")%></td>
                </tr>
                <tr>
                    <td>Last Name:</td><td><input type="text" name="Lname" value="<%=register.getLastname()%>"></td>
                    <td><%=register.getErrorMessage("lastname")%></td>
                </tr>
                <tr>
                    <td>Email:</td><td><input type="email" name="email" value="<%=register.getEmail()%>"></td>
                    <td><%=register.getErrorMessage("email")%></td>
                </tr>
                <tr>
                    <td>Password:</td><td><input type="password" name="password" value="<%=register.getPassword()%>"></td>
                    <td><%=register.getErrorMessage("password")%></td>
                </tr>
                <tr>
                    <td>Date of Birth:</td><td><input type="date" name="dob"></td>
                </tr>
                <tr>
                    <td>User type:</td><td>
                        <select onchange='userType(this.value);' name="usertype">
                            <option value="student">Student</option>
                            <option value="tutor">Tutor</option>
                        </select></td>
                </tr>
                <tr>
                    <td><p id= "specialtylabel" style='display:none;'>Specialty:</p></td><td>
                        <select name="specialty" id="specialty" style='display:none;'>
                            <option value='WSD'>Web Services Development</option>
                            <option value='USP'>Unix Systems Programming</option>
                            <option value='SEP'>Software Engineering Practice</option>
                            <option value='AppProg'>Application Programming</option>
                            <option value='MobileApp'>Mobile Applications Development</option>
                        </select></td>
                </tr>
                <tr>
                    <td></td><td><input type="submit" value="Register"></td>
                </tr>
            </table>            
        </form>
        <p>If you already have an account, Click <u><a href="login.jsp">here</a></u>.</p>
    </body>
</html>


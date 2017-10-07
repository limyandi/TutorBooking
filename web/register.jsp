<%@page contentType="text/xml" pageEncoding="UTF-8" import="uts.wsd.registerlogin.Register"%><?xml version="1.0" encoding="UTF-8"?>
<?xml-stylesheet type="text/xsl" href="./xsl/styles.xsl"?>
<!DOCTYPE html>
        <% 
            Register register = new Register();
            if(session.getAttribute("registerData") != null) {
                register = (Register) session.getAttribute("registerData");
            }
        %>
        <page title="Register">
            <navnonauth/>
            <inputs action='registerAction.jsp'  value="Register">
                <input type='text' label='First Name' name='Fname'><%=register.getFirstname()%></input>
                <error><%=register.getErrorMessage("firstname")%></error>
                <input type='text' label='Last Name' name='Lname'><%=register.getLastname()%></input>
                <error><%=register.getErrorMessage("lastname")%></error>
                <input type='email' label='Email' name='email'><%=register.getEmail()%></input>
                <error><%=register.getErrorMessage("email")%></error>
                <input type='password' label='Password' name='password'><%=register.getPassword()%></input>
                <error><%=register.getErrorMessage("firstname")%></error>
                <input type='date' label='Date of Birth' name='dob'></input>
                <select label='User type' onchange='userType(this.value)' name='usertype'>
                    <option value='student'>Student</option>
                    <option value='tutor'>Tutor</option>
                </select>
                <para id='specialtylabel' style='display:none;'>Specialty</para>
                <select id='specialty' name='specialty' style='display:none;'>
                    <option value='WSD'>Web Services Development</option>
                    <option value='USP'>Unix Systems Programming</option>
                    <option value='SEP'>Software Engineering Practice</option>
                    <option value='AppProg'>Application Programming</option>
                    <option value='MobileApp'>Mobile Applications Development</option>
                </select>
            </inputs>
        </page>
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author limyandivicotrico
 */
public class Try {

    private static Students students = new Students();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String email = in.next();
        String password = in.next();
        Student student = students.login(email, password);
    }

    public Student login(String email, String password) {
        validateEmail(email);
        validatePassword(password);
        Student student = new Student();
        student.setEmail(email);
        student.setPassword(password);
        if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
            System.out.println("GOT STUDENT!");
            return student;
        }
        return null;
    }
    
    private void validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("([A-Za-z\\._]+)@(([a-z-]+)\\.)+([a-z-]+)");
        Matcher emailMatcher = emailPattern.matcher(email);
        if(!emailMatcher.find()) {
            System.out.println("Email does not match");
        }
    }
    
    private void validatePassword(String password) {
        Pattern passwordPattern = Pattern.compile("([A-Za-z0-9!@#$%^*\\?]{6,16})");
        
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if(!passwordMatcher.find()) {
            System.out.println("Password does not match");
        }
    }
}

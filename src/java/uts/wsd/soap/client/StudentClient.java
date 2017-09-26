/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts.wsd.soap.client;

import java.util.Scanner;

/**
 *
 * @author limyandivicotrico
 */
public class StudentClient {
    private static final StudentApp locator = new StudentApp();
    private static final StudentSOAP students = locator.getStudentSOAPPort();
    private static final Scanner in = new Scanner(System.in);
    private static boolean running = true;
    private static Student student;
    public static void main(String[] args) {
        do{
            System.out.println("Main Menu");
            System.out.println("1. Login");
            System.out.println("2. Logout");
            System.out.println("3. Create a booking");
            System.out.println("4. View all bookings");
            System.out.println("5. Cancel a booking");
            System.out.println("6. Exit");
            String choice = in.next();
            switch(choice) {
                case "1":
                case "Login":
                    login();
                    break;
                case "2":
                    //TODO: LOGOUT
                    break;
                case "3":
                    //TODO: CREATE A BOOKING
                    break;
                case "4":
                    //TODO: VIEW ALL BOOKINGS
                    break;
                case "5":
                    //TODO: CANCEL A BOOKING
                case "6":
                    exit();
                    break;
                default:
                    System.out.println("No matching choice found, go again!");
            }
        }
        while(running);
        
    }
    
    private static void login() {
        student = null;
        System.out.println("Please Enter your email!");
        String email = in.next();
        System.out.println("Please enter your password!");
        String password = in.next();
        student = students.studentLogin(email,password);
        if(student != null) 
            System.out.println("Login successfully!");
        else 
            System.out.println("No matching user with that password found");
    }
    
    private static void createBooking() {
    }
    
    private static void exit() {
        System.out.println("Are you sure you want to exit? (Y/N)");
        char choice = in.next().charAt(0);
        if(choice == 'Y' || choice == 'y') {
            running = false;
            System.out.println("Bye bye!");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.soap.client;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

/**
 *
 * @author Jason
 */
public class BookingServiceClient {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception_Exception, IOException_Exception {
        BookingServiceA_Service locator = new BookingServiceA_Service();
        BookingServiceA bookingService = locator.getBookingServiceAPort();
        boolean running = true;
        Student student = null;
        Tutor tutor = null;
        String userType;
        boolean valid = true;
        while (running) {
            System.out.println("Welcome to the UTS Tutor SOAP Client");
            System.out.println("Please login");
            System.out.println("Email: ");
            String email = scanner.next();
            System.out.println("Password: ");
            String password = scanner.next();
            student = bookingService.studentLogin(email, password);
            userType = "student";
            if (student == null) {
                tutor = bookingService.tutorLogin(email, password);
                if (tutor != null) {
                    userType = "tutor";
                } else {
                    userType = "invalid";
                }
            }
            switch (userType) {
                case "student":
                    //studentMenu(student);
                    boolean keep = true;
                    //keeps running until logout
                    while (keep == true) {
                        System.out.println("You are logged in as " + student.getEmail() + " as a Student");
                        System.out.println("Main Menu");
                        System.out.println("1. Logout");
                        System.out.println("2. Create a booking");
                        System.out.println("3. View current bookings");
                        System.out.println("4. View past bookings");
                        System.out.println("5. Cancel a booking");
                        System.out.println("6. Exit");
                        System.out.println("99. ||Cancel Account||");
                        String choice = scanner.next();
                        switch (choice) {
                            case "1":
                                keep = false;
                                break;
                            case "2":
                                System.out.println("Please select a tutor");
                                int i = 1;
                                int tutorid;
                                //hashmap to be used for actual booking process
                                HashMap<Integer, String> tutorlist = new HashMap<Integer, String>();
                                for (Tutor tutora : bookingService.getTutorsByStatus("available")) {
                                    String s;
                                    s = (i + " " + tutora.getFirstName() + " " + tutora.getLastName() + ", Subject: " + tutora.getSubject());
                                    tutorlist.put(i, s);
                                    System.out.println(s);
                                    tutorlist.put(i, tutora.getEmail());
                                    i++;
                                }
                                valid = true;
                                try {
                                    String pre = scanner.next();
                                    tutorid = parseInt(pre);
                                } catch (NumberFormatException e) {
                                    System.out.println("Please enter a valid number");
                                    tutorid = 0;
                                }
                                if (tutorid <= 0 || tutorid > i && valid) {
                                    System.out.println("Please enter a valid number");
                                    valid = false;
                                }
                                if (valid) {
                                    bookingService.makeEmailBooking(student, tutorlist.get(tutorid));
                                }
                                break;
                            case "3":
                                for (Booking booking : bookingService.getBookingByStatus("active", student)) {
                                    String s;
                                    s = ("You have a booking with " + booking.getTutorfirstname() + " " + booking.getTutorlastname() + " for " + booking.getSubjectname());
                                    System.out.println(s);
                                }
                                break;
                            case "4":
                                for (Booking booking : bookingService.getBookingByStatus("cancelled", student)) {
                                    String s;
                                    s = ("You had a booking with " + booking.getTutorfirstname() + " " + booking.getTutorlastname() + " for " + booking.getSubjectname() + " - cancelled");
                                    System.out.println(s);
                                }
                                for (Booking booking : bookingService.getBookingByStatus("completed", student)) {
                                    String s;
                                    s = ("You had a booking with " + booking.getTutorfirstname() + " " + booking.getTutorlastname() + " for " + booking.getSubjectname() + " - completed");
                                    System.out.println(s);
                                }
                                break;
                            case "5":
                                System.out.println("Please select a booking");
                                i = 1;
                                //hashmap to be used for actual booking process
                                HashMap<Integer, Integer> bookingList = new HashMap<Integer, Integer>();
                                bookingList.put(0, null);
                                System.out.println("0 - Select this to cancel");
                                for (Booking booking : bookingService.getBookingByStatus("active", student)) {
                                    String s;
                                    s = (i + " " + booking.getTutorfirstname() + " " + booking.getTutorlastname() + ", Subject: " + booking.getSubjectname());
                                    System.out.println(s);
                                    bookingList.put(i, booking.getId());
                                    i++;
                                }
                                int bookingId;
                                valid = true;
                                try {
                                    String pre = scanner.next();
                                    bookingId = parseInt(pre);
                                } catch (NumberFormatException e) {
                                    System.out.println("Please enter a valid number");
                                    bookingId = 0;
                                }
                                if (bookingId <= 0 || bookingId > i && valid) {
                                    System.out.println("Please enter a valid number");
                                    valid = false;
                                }
                                if (valid) {
                                    bookingService.cancelBooking(student, bookingList.get(bookingId));
                                }
                                break;
                            case "6":
                                keep = false;
                                running = false;
                                System.out.println("See you next time!");
                                break;
                            case "99":
                                System.out.println("Are you sure you want to cancel your account?");
                                System.out.println("1. No");
                                System.out.println("2. Yes");
                                choice = scanner.next();
                                switch (choice) {
                                    case "2":
                                        System.out.println("Please relogin to confirm");
                                        System.out.println("Email: ");
                                        email = scanner.next();
                                        System.out.println("Password: ");
                                        password = scanner.next();
                                        Student canceller = bookingService.studentLogin(email, password);
                                        if(student.equals(canceller)){canceller = null;}
                                        if (canceller != null) {
                                            bookingService.removeStudent(student);
                                            System.out.println("Your account has been succesfully deleted. You will now be logged out");
                                            keep = false;
                                        }
                                        break;
                                    default:
                                        System.out.println("Confirmation cancelled");
                                }
                                break;
                            default:
                                System.out.println("Please use a valid number and try again");
                                break;
                        }
                    }
                    break;
                case "tutor":
                    keep = true;
                    while (keep) {
                        //tutorMenu(tutor);
                        System.out.println("You are logged in as " + tutor.getEmail() + " as a Tutor");
                        System.out.println("Main Menu");
                        System.out.println("1. Logout");
                        System.out.println("2. View current booking");
                        System.out.println("3. View past bookings");
                        System.out.println("4. Cancel a booking");
                        System.out.println("5. Complete current booking");
                        System.out.println("6. Exit");
                        String choice = scanner.next();
                        switch (choice) {
                            case "1":
                                keep = false;
                                break;
                            case "2":
                                for (Booking booking : bookingService.getStatusTutorBooking("active", tutor)) {
                                    String s;
                                    s = ("You have a booking with " + booking.getStudentfirstname() + " " + booking.getStudentlastname());
                                    System.out.println(s);
                                }
                                break;
                            case "3":
                                for (Booking booking : bookingService.getStatusTutorBooking("completed", tutor)) {
                                    String s;
                                    s = ("You had a booking with " + booking.getStudentfirstname() + " " + booking.getStudentlastname() + " - completed");
                                    System.out.println(s);
                                }
                                for (Booking booking : bookingService.getStatusTutorBooking("cancelled", tutor)) {
                                    String s;
                                    s = ("You had a booking with " + booking.getStudentfirstname() + " " + booking.getStudentlastname() + " - cancelled");
                                    System.out.println(s);
                                }
                                break;
                            case "4":
                                System.out.println("Please select a booking");
                                int i = 1;
                                //hashmap to be used for actual booking process
                                HashMap<Integer, Integer> bookingList = new HashMap<Integer, Integer>();
                                bookingList.put(0, null);
                                System.out.println("0 - Select this to cancel");
                                for (Booking booking : bookingService.getStatusTutorBooking("active", tutor)) {
                                    String s;
                                    s = (i + " " + booking.getStudentfirstname() + " " + booking.getStudentlastname() + ", Subject: " + booking.getSubjectname());
                                    System.out.println(s);
                                    bookingList.put(i, booking.getId());
                                    i++;
                                }
                                int bookingId;
                                valid = true;
                                try {
                                    String pre = scanner.next();
                                    bookingId = parseInt(pre);
                                } catch (NumberFormatException e) {
                                    System.out.println("Please enter a valid number");
                                    bookingId = 0;
                                }
                                if (bookingId <= 0 || bookingId > i && valid) {
                                    System.out.println("Please enter a valid number");
                                    valid = false;
                                }
                                if (valid) {
                                    bookingService.cancelBooking(student, bookingList.get(bookingId));
                                }
                                break;
                            case "5":
                                int completeId = 0;
                                for (Booking booking : bookingService.getStatusTutorBooking("active", tutor)) {
                                    String s;
                                    s = ("Your current booking is with " + booking.getStudentfirstname() + " " + booking.getStudentlastname());
                                    System.out.println(s);
                                    completeId = booking.getId();
                                }
                                System.out.println("Are you sure you wish to continue?");
                                System.out.println("1. No");
                                System.out.println("2. Yes");
                                valid = true;
                                int confirm = 0;
                                try {
                                    String pre = scanner.next();
                                    confirm = parseInt(pre);
                                } catch (NumberFormatException e) {
                                    confirm = 0;
                                    valid = false;
                                }
                                if (valid) {
                                    if (confirm == 2) {
                                        bookingService.completeBooking(tutor, completeId);
                                    } else {
                                        System.out.println("Confirmation cancelled");
                                    }
                                } else {
                                    System.out.println("Please enter a valid number");
                                }

                                break;
                            case "6":
                                keep = false;
                                running = false;
                                System.out.println("See you next time!");
                                break;
                            case "99":
                                System.out.println("Are you sure you want to cancel your account?");
                                System.out.println("1. No");
                                System.out.println("2. Yes");
                                choice = scanner.next();
                                switch (choice) {
                                    case "2":
                                        System.out.println("Please relogin to confirm");
                                        System.out.println("Email: ");
                                        email = scanner.next();
                                        System.out.println("Password: ");
                                        password = scanner.next();
                                        Tutor canceller = bookingService.tutorLogin(email, password);
                                        if (tutor.equals(canceller)){canceller = null;}
                                        if (canceller != null) {
                                            bookingService.removeTutor(tutor);
                                            System.out.println("Your account has been succesfully deleted. You will now be logged out");
                                            keep = false;
                                        }
                                        break;
                                    default:
                                        System.out.println("Confirmation cancelled");
                                }
                                break;
                            default:
                                System.out.println("Please use a valid number and try again");
                                break;

                        }
                    }
                    break;
                case "invalid":
                    //invalidMenu();
                    System.out.println("Please input a valid username and password");
                    System.out.println("Would you like to exit instead?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    String choice = scanner.next();
                    switch (choice) {
                        case "1":
                            System.out.println("See you next time!");
                            running = false;
                            break;
                        default:
                            System.out.println("Redirecting back to login");
                    }
                    break;
                default:
            }
        }
    }
}

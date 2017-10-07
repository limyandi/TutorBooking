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
                        System.out.println("Main Menu");
                        System.out.println("1. Logout");
                        System.out.println("2. Create a booking");
                        System.out.println("3. View all bookings");
                        System.out.println("4. Cancel a booking");
                        System.out.println("5. Exit");
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
                                System.out.println("Please select a tutor");
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
                            case "5":
                                keep = false;
                                running = false;
                                System.out.println("See you next time!");
                                break;

                            default:
                                System.out.println("Please use a valid number and try again");
                                break;
                        }
                    }
                    break;

                case "tutor":
                    //tutorMenu(tutor);
                    break;
                case "invalid":
                    //invalidMenu();
                    break;
                default:

            }

            //more code here
        }
    }

}

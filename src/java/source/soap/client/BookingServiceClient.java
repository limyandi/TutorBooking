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
                                try {
                                    String pre = scanner.next();
                                    tutorid = parseInt(pre);
                                } catch (NumberFormatException e) {
                                    System.out.println("Please enter a valid number");
                                    tutorid = 0;
                                }
                                
                                bookingService.makeEmailBooking(student, tutorlist.get(tutorid));
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

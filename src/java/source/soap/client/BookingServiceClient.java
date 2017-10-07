/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source.soap.client;

import java.util.Scanner;

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
            System.out.println("Username: ");
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

            //more code here
        }
    }

    private void studentMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Login");
        System.out.println("2. Logout");
        System.out.println("3. Create a booking");
        System.out.println("4. View all bookings");
        System.out.println("5. Cancel a booking");
        System.out.println("6. Exit");
        String choice = scanner.next();
    }
}

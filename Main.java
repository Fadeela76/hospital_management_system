//Hospital Management System using java oops, jdbc and mysql.
package org.example;

import org.example.Patient;

import java.sql.*;
import java.util.Scanner;

public class Main{
    // Database connection details
    private static final String url = "jdbc:mysql://localhost:3306/hospital_management_system";
    private static final String username = "root";
    private static final String password = "Watermelon1@";

    public static void main(String[] args) {
         // Load MySQL JDBC Driver
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        try{
            // Establish database connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create Patient and Doctor objects
            Patient patient = new Patient(connection, scanner);
            Doctor doctor = new Doctor(connection);

            // Main menu loop
            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM ");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. View Doctors");
                System.out.println("4. Book Appointment");
                System.out.println("5. Exit");
                System.out.println("Enter your choice: ");
                int choice = scanner.nextInt();

                switch(choice){
                    case 1:
                        // Add Patient
                        patient.addPatient();
                        System.out.println();
                        break;
                    case 2:
                        // View Patient
                        patient.viewPatients();
                        System.out.println();
                        break;
                    case 3:
                        // View Doctors
                        doctor.viewDoctors();
                        System.out.println();
                        break;
                    case 4:
                        // Book Appointment
                        bookAppointment(patient, doctor, connection, scanner);
                        System.out.println();
                        break;
                    case 5:
                        // Exit the program
                        System.out.println("THANK YOU! FOR USING HOSPITAL MANAGEMENT SYSTEM!!");
                        return;
                    default:
                        // Handle invalid input
                        System.out.println("Enter valid choice!!!");
                        break;
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

 // Method to book an appointment
    public static void bookAppointment(Patient patient, Doctor doctor, Connection connection, Scanner scanner){
        // Get appointment details from user
        System.out.print("Enter appointment_id: ");
        int appointment_id = scanner.nextInt();
        System.out.print("Enter Patient Id: ");
        int p_id = scanner.nextInt();
        System.out.print("Enter Doctor Id: ");
        int d_id = scanner.nextInt();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointment_date = scanner.next();

        // Validate doctor existence
        if (!doctor.getDoctorById(d_id)) {
            System.out.println("Doctor not found!");
            return;
        }
        // Validate patient existence
        if (!patient.getPatientById(p_id)) {
            System.out.println("Patient not found!");
            return;
        }
        
       // Insert appointment into the database
        String appointmentQuery = "INSERT INTO appointments(appointment_id, p_id, d_id, appointment_date) VALUES(?, ?, ?, ?)";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                    preparedStatement.setInt(1, appointment_id);
                    preparedStatement.setInt(2, p_id);
                    preparedStatement.setInt(3, d_id);
                    preparedStatement.setString(4, appointment_date);

                    // Execute insert query
                    int rowsAffected = preparedStatement.executeUpdate();
                    if(rowsAffected>0){
                        System.out.println("Appointment Booked!");
                    }else{
                        System.out.println("Failed to Book Appointment!");
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }


    }


}

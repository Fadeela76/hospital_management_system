package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
    // Connection object to interact with the database
    private Connection connection;

    // Constructor that takes a database connection
    public Doctor(Connection connection){
        this.connection = connection;
    }

    /**
     * This method fetches and displays all doctors from the 'doctors' table.
     */
    public void viewDoctors(){
        String query = "SELECT * FROM doctors";
        try {
            // Prepare and execute the SQL query
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            // Print formatted doctor list
            System.out.println("Doctors: ");
            System.out.println("+------------+--------------------+------------------+");
            System.out.println("| Doctor Id  | Name               | Specialization   |");
            System.out.println("+------------+--------------------+------------------+");

            // Loop through each doctor and print their details
            while(resultSet.next()){
                int id = resultSet.getInt("d_id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");

                // Display one row of doctor info
                System.out.printf("| %-10s | %-18s | %-16s |\n", id, name, specialization);
                System.out.println("+------------+--------------------+------------------+");
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print error if query fails
        }
    }

    /**
     * This method checks if a doctor with the given ID exists in the 'doctors' table.
     * 
     * @param d_id Doctor ID to search for
     * @return true if doctor exists, false otherwise
     */
    public boolean getDoctorById(int d_id){
        String query = "SELECT * FROM doctors WHERE d_id = ?";
        try {
            // Prepare the query with the doctor ID
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, d_id);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Return true if doctor is found
            if(resultSet.next()){
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print error if something goes wrong
        }

        // If an exception occurs or doctor is not found
        return false;
    }
}

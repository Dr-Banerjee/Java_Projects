package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctors {
    private Connection connection;

    public Doctors(Connection connection){
        this.connection = connection;

    }

    public void viewDoctors(){
        String query = "select * from doctors";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Doctors : ");
            System.out.println("+-----------+----------------+-----------------------+");
            System.out.println("|Doctor Id| Name           | Specialization    |");
            System.out.println("+-----------+----------------+-----------------------+");
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                System.out.printf("|% -12s|% -20s| % -10s|\n", id, name, specialization);
                System.out.printf("+-----------+----------------+-----------------------+");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public boolean getdoctorbyId(int id){
        try{
            String query  = "SELECT * FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}

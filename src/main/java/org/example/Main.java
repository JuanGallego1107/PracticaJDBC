package org.example;

import org.example.conexion.ConexionBD;
import org.example.domain.models.Producto;
import org.example.repository.Repository;
import org.example.repository.impl.RepositoryImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "admin";
        try (Connection conn = DriverManager.getConnection(url, user,
            password);
    Statement statement = conn.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM productos");
             ) {
        while (resultSet.next()) {
            System.out.print(resultSet.getInt("id"));
            System.out.print("|");
            System.out.print(resultSet.getString("nombre"));
            System.out.print("|");
            System.out.print(resultSet.getDouble("precio"));
            System.out.print("|");
            System.out.print(resultSet.getDate("fecha_registro"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

        try(Connection conn = ConexionBD.getInstance()){
            Repository<Producto> repository = new RepositoryImpl();
            listProducts(repository);
            getProductById(repository);
//addProduct(repository);
//updateProduct(repository);
            deleteProduct(repository);
        }catch (SQLException e) {
            e.printStackTrace();
        }
}
}
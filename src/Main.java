import java.sql.*;

public class Main {
    static final String DB_NAME = "newdb";
    static final String URL = "jdbc:mysql://localhost:3306/"+DB_NAME;
    static final String USER = "developer";
    static final String PASSWORD = "password";



    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = conn.createStatement();
             PreparedStatement preparedStatement = conn.prepareStatement("UPDATE students SET country = ? WHERE student_id = ?")) {

            statement.executeUpdate("ALTER TABLE students ADD country VARCHAR(30) NOT NULL");

            insertCountry(preparedStatement, "Italy",1);
            insertCountry(preparedStatement,"Italy",2);
            insertCountry(preparedStatement,"France",3);
            insertCountry(preparedStatement,"Germany",4);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void insertCountry(PreparedStatement preparedStatement, String country, int studentId) throws SQLException {
        preparedStatement.setString(1, country);
        preparedStatement.setInt(2, studentId);
        preparedStatement.executeUpdate();
    }
}
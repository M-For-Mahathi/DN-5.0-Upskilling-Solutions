import java.sql.*;

// Exercise 31 — JDBC Connection and SELECT query
// Exercise 32 — INSERT and UPDATE using PreparedStatement

public class StudentDAO {

    private static final String URL      = "jdbc:mysql://localhost:3306/school";
    private static final String USER     = "root";
    private static final String PASSWORD = "password";

    // Exercise 31
    public void fetchStudents() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery("SELECT * FROM students")) {

            System.out.println("Student List:");
            while (rs.next())
                System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
        }
    }

    // Exercise 32 — insert
    public void insertStudent(int id, String name) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT INTO students(id, name) VALUES (?, ?)")) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
            System.out.println("Inserted: " + name);
        }
    }

    // Exercise 32 — update
    public void updateStudent(int id, String newName) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(
                 "UPDATE students SET name = ? WHERE id = ?")) {

            ps.setString(1, newName);
            ps.setInt(2, id);
            ps.executeUpdate();
            System.out.println("Updated ID " + id + " to: " + newName);
        }
    }

    public static void main(String[] args) throws SQLException {
        StudentDAO dao = new StudentDAO();
        dao.fetchStudents();
        dao.insertStudent(3, "Charlie");
        dao.updateStudent(1, "Alice Updated");
        dao.fetchStudents();
    }
}
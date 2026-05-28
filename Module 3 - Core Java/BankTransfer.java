import java.sql.*;

// Exercise 33

public class BankTransfer {

    private static final String URL      = "jdbc:mysql://localhost:3306/school";
    private static final String USER     = "root";
    private static final String PASSWORD = "password";

    public void transfer(int fromId, int toId, double amount) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {

            conn.setAutoCommit(false);

            try {
                PreparedStatement debit = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance - ? WHERE id = ?");
                debit.setDouble(1, amount);
                debit.setInt(2, fromId);
                debit.executeUpdate();

                PreparedStatement credit = conn.prepareStatement(
                    "UPDATE accounts SET balance = balance + ? WHERE id = ?");
                credit.setDouble(1, amount);
                credit.setInt(2, toId);
                credit.executeUpdate();

                conn.commit();
                System.out.println("Transfer of Rs." + amount +
                    " from account " + fromId + " to account " + toId + " successful.");

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transfer failed. Transaction rolled back. Reason: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        BankTransfer bt = new BankTransfer();
        bt.transfer(1, 2, 500.00);
    }
}
package test;

import dao.baseDAO;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class testbaseDAO {

    @Test
    public void testDatabaseConnection() {
        try (Connection connection = baseDAO.getConnection()) {
            assertNotNull(connection, "Koneksi ke database harus berhasil.");
        } catch (Exception e) {
            throw new RuntimeException("Koneksi database gagal: " + e.getMessage());
        }
    }
}

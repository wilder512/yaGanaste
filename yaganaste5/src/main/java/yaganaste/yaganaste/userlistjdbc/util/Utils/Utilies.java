package yaganaste.yaganaste.userlistjdbc.util.Utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Configuration
public  class Utilies {

    private static final String URL = "jdbc:postgresql://localhost:5432/atenea";
    private static final String USER = "postgres";
    private static final String PASS = "wilder123";

    @Bean
    public Connection getConnexion(){

       Connection conn = null;
          try {
         // Obtener la conexión
            conn = DriverManager.getConnection(URL, USER, PASS);
     } catch (
     SQLException e) {
         e.getMessage();
     }
        return conn;
    }

    public void closeConnexion( Connection conn) {
        // Cerrar la conexión
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }
}

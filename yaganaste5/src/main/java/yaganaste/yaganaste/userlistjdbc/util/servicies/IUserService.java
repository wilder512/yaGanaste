package yaganaste.yaganaste.userlistjdbc.util.servicies;

import yaganaste.yaganaste.userlistjdbc.model.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface IUserService {

    String saveCliente(User user);
    String deleteCliente(Integer idCliente);
    Optional<User> findUserByid(Integer idCliente);
    void transferencia(Connection conn, BigDecimal importe, int cliente1, int cliente2) throws SQLException;

}

package yaganaste.yaganaste.userlistjdbc.util.servicies;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

import yaganaste.yaganaste.userlistjdbc.model.User;
import yaganaste.yaganaste.userlistjdbc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplements implements IUserService{

	@Autowired
	public UserRepository userRepository;

	
	
	private static final Logger log = LoggerFactory.getLogger(UserServiceImplements.class);

	@Override
	public String saveCliente(User user)
	{

		try {
			LocalDateTime time =LocalDateTime.now();
			user.setCreated_on(time);
			userRepository.save(user);
			log.warn("registro exitoso");
			return "OK";
		} catch (Exception e) {

			log.error("error insertando datos en users");
			return "NOK";

		}
	}
	@Override
	public String deleteCliente(Integer idCliente)
	{

		try {
			userRepository.deleteById(idCliente);
			log.warn(" registro exitoso");
			return "OK";
		} catch (Exception e) {
			log.error( "error eliminando datos en users");
			return "NOK";
		}
	}

	@Override
	public Optional<User> findUserByid(Integer idCliente) {
		Optional<User> user =userRepository.findById(idCliente);
		return user;

	}
	@Override
	public void transferencia(Connection conn, BigDecimal importe, int cliente1, int cliente2) throws SQLException {
		// Inicio de la transacción
		conn.setAutoCommit(false);

		try {
			// Obtener los saldos actuales de los clientes
			BigDecimal saldo1;
			BigDecimal saldo2;

			PreparedStatement stmt = conn.prepareStatement("SELECT saldo_cliente FROM users WHERE id_cliente = ?");
			stmt.setInt(1, cliente1);
			ResultSet rs = stmt.executeQuery();

			if (!rs.next()) {
				throw new SQLException("No se encontró el cliente1 en la base de datos.");
			}

			saldo1 = rs.getBigDecimal(1);

			stmt = conn.prepareStatement("SELECT saldo_cliente FROM users WHERE id_cliente = ?");
			stmt.setInt(1, cliente2);
			rs = stmt.executeQuery();

			if (!rs.next()) {
				throw new SQLException("No se encontró el cliente2 en la base de datos.");
			}

			saldo2 = rs.getBigDecimal(1);

			// Restar el importe al saldo del cliente1
			saldo1 = saldo1.subtract(importe);

			// Sumar el importe al saldo del cliente2
			saldo2 = saldo2.add(importe);

			// Actualizar los saldos de los clientes
			stmt = conn.prepareStatement("UPDATE users SET saldo_cliente = ? WHERE id_cliente = ?");
			stmt.setBigDecimal(1, saldo1);
			stmt.setInt(2, cliente1);
			stmt.executeUpdate();

			stmt = conn.prepareStatement("UPDATE users SET saldo_cliente = ? WHERE id_cliente = ?");
			stmt.setBigDecimal(1, saldo2);
			stmt.setInt(2, cliente2);
			stmt.executeUpdate();

			// Commit de la transacción
			conn.commit();

		} catch (SQLException e) {
			// Rollback de la transacción en caso de excepción
			conn.rollback();
			throw e;
		} finally {
			// Reestablecer el modo de auto-commit
			conn.setAutoCommit(true);
		}
	}

}

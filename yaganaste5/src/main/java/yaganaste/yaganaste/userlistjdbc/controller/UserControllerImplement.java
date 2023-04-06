package yaganaste.yaganaste.userlistjdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import yaganaste.yaganaste.userlistjdbc.Exeption.UserExecption;
import yaganaste.yaganaste.userlistjdbc.model.TransferenciaDto;
import yaganaste.yaganaste.userlistjdbc.model.User;
import yaganaste.yaganaste.userlistjdbc.util.Utils.Utilies;
import yaganaste.yaganaste.userlistjdbc.util.servicies.UserServiceImplements;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UserControllerImplement  {
	private static final String URL = "jdbc:postgresql://localhost:5432/atena";
	private static final String USER = "postgres";
	private static final String PASS = "wilder123";

	@Autowired
	public UserServiceImplements userServiceImplements;

	@Autowired
	public Utilies utilies;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PostMapping("/save")
	public ResponseEntity<User> saveCliente(@RequestBody User request) throws UserExecption {
		try {
		String mensaje = userServiceImplements.saveCliente(request);
		
		if (mensaje.equals("OK"))
		{
			return new ResponseEntity<>(request,HttpStatus.CREATED);
		}
		else
		{
			return new ResponseEntity<>(request,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		} catch (Exception e) {
			throw new UserExecption("Error al Guardar Usuario", e);
		}
	}
	
	@PostMapping("/delete")
	public ResponseEntity<Integer> deleteCliente(@RequestBody Integer requestId) throws UserExecption {
		try {
		String mensaje = userServiceImplements.deleteCliente(requestId);
		if (mensaje.equals("OK"))
		{
			return new ResponseEntity<>(requestId,HttpStatus.NO_CONTENT);
		}
		else
		{
			return new ResponseEntity<>(requestId,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		} catch (Exception e) {
			throw new UserExecption("Error al Eliminar Usuario", e);
		}
	}


	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsuarios() throws UserExecption {
		try {

			String sql = "SELECT id_cliente, apellido_cliente, created_on, direccion_cliente, email_cliente, nombre_cliente, saldo_cliente  FROM users";
		List<User> userssers = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
		return userssers;
		} catch (Exception e) {
			throw new UserExecption("Error al listar usuarios", e);
		}
	}


	@PostMapping("/transferencia")
	public ResponseEntity<String> transferencia(@RequestBody TransferenciaDto request) throws UserExecption,NullPointerException {

		try {

			Connection conn = utilies.getConnexion();
			userServiceImplements.transferencia(conn,request.getImporte(),request.getId_cliente1(), request.getId_cliente2());

		} catch (Exception e) {
			throw new UserExecption("error realizando transferencia", e);
		}
		return new ResponseEntity<>("Transferencia realizada con exito",HttpStatus.OK);
	}

}

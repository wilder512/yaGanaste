package yaganaste.yaganaste.userlistjdbc.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id_cliente")
	 private Integer id_cliente ;
	 
	 @Column(name = "nombre_cliente")
	 private  String nombre_cliente ;
	 
	@Column(name = "apellido_cliente")
	 private String apellido_cliente;
	 
	 @Column(name ="direccion_cliente")
	 private String direccion_cliente;
	 
	 @Column(name = "email_cliente")
	 private String email_cliente ;

	 @Column(name = "created_on")
	 private LocalDateTime created_on;

	@Column(name = "saldo_cliente")
	private BigDecimal saldo_cliente ;

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNombre_cliente() {
		return nombre_cliente;
	}

	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}

	public String getApellido_cliente() {
		return apellido_cliente;
	}

	public void setApellido_cliente(String apellido_cliente) {
		this.apellido_cliente = apellido_cliente;
	}

	public String getDireccion_cliente() {
		return direccion_cliente;
	}

	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}

	public String getEmail_cliente() {
		return email_cliente;
	}

	public void setEmail_cliente(String email_cliente) {
		this.email_cliente = email_cliente;
	}
	
	public LocalDateTime getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDateTime date) {
		this.created_on = date;
	}

	public BigDecimal  getSaldo_cliente() {
		return saldo_cliente;
	}

	public void setSaldo_cliente(BigDecimal  saldo_cliente) {
		this.saldo_cliente = saldo_cliente;
	}
}

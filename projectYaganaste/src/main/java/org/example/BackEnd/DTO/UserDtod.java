package org.example.BackEnd.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor

public class UserDtod {
    private String userid;
    private String nombre;
    private String username;
    private  String password;

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserid() {
        return userid;
    }

    public String getNombre() {
        return nombre;
    }
}

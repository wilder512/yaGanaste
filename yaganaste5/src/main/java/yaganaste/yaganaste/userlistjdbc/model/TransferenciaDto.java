package yaganaste.yaganaste.userlistjdbc.model;

import java.math.BigDecimal;

public class TransferenciaDto {

    private Integer id_cliente1;
    private Integer id_cliente2;
    private BigDecimal importe;

    public Integer getId_cliente1() {
        return id_cliente1;
    }

    public void setId_cliente1(Integer id_cliente1) {
        this.id_cliente1 = id_cliente1;
    }

    public Integer getId_cliente2() {
        return id_cliente2;
    }

    public void setId_cliente2(Integer id_cliente2) {
        this.id_cliente2 = id_cliente2;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }
}

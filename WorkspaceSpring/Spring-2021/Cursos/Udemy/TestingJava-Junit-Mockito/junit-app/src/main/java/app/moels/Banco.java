package app.moels;

import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Banco {
    private List<Cuenta> cuentas;
    private String nombre;

    public Banco() {
        cuentas = new ArrayList<>();
    }

    public void addCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
        cuenta.setBanco(this);
    }

    public void transferir(Cuenta origen, Cuenta destino, BigDecimal monto) {
        origen.debidoCuenta(monto);
        destino.creditoCuenta(monto);
    }
}

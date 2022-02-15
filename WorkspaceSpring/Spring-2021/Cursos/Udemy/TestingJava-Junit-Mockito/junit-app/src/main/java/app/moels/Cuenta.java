package app.moels;

import app.exceptions.DineroInsuficienteException;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@ToString
public class Cuenta {
    private String persona;
    private BigDecimal saldo;
    private Banco banco;

    public Cuenta() {
    }

    public Cuenta(String persona, BigDecimal saldo) {
        this(saldo);
        this.persona = persona;
    }

    public Cuenta(BigDecimal saldo) {
        this.saldo = saldo;
    }

     public void debidoCuenta(BigDecimal monto) {
        BigDecimal nuevoSaldo = this.saldo.subtract(monto);
        if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new DineroInsuficienteException("Dinero insuficiente en su cuenta");
        }
        this.saldo = nuevoSaldo;
     }

     public void creditoCuenta(BigDecimal monto) {
        this.saldo = this.saldo.add(monto);
     }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Cuenta))
            return false;
        Cuenta cuenta = (Cuenta) o;
        if (this.persona == null || saldo == null)
            return false;

        return this.persona.equals(cuenta.getPersona()) && this.saldo.equals(cuenta.getSaldo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(persona, saldo);
    }
}

package com.reservation.business.repository;

import com.reservation.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, String> {
    @Query("select r from Reserva r where r.fechaIngresoReserva = :fechaIngresoReserva and r.fechaSalidaReserva = :fechaSalidaReserva")
    List<Reserva> find(@Param("fechaIngresoReserva") Date fechaIngresoReserva, @Param("fechaSalidaReserva") Date fechaSalidaReserva);
    //List<Reserva> find(Date fechaIngresoReserva, Date fechaSalidaReserva);
}

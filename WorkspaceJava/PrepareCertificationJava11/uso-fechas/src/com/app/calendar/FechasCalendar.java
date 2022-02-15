package com.app.calendar;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class FechasCalendar {

	public static void main(String[] args) throws InterruptedException 
	{
		
		int i = 4;
		
		if(i == 0) {
			/*
			 * EN calendar los mese empiezan en mes 0
			 */
			Calendar fecha1 = Calendar.getInstance();
			Calendar fecha2 = Calendar.getInstance();
			
			fecha1.set(1993, 06, 29);
			fecha2.set(2021, 02, 25);
			
			System.out.println(fecha1);
			System.out.println(fecha2);
			
			System.out.println(fecha1.after(fecha2)); // despues
			System.out.println(fecha1.before(fecha2)); // antes
			
		}
		else if (i == 1) {
			// localdate
			
			/*
			 * En localdate los meses empiezan en 1
			 */
			
			LocalDate fecha1 = LocalDate.of(1993, 6, 29);
			LocalDate fecha2 = fecha1.withMonth(12);
			
			System.out.println(fecha1);
			System.out.println(fecha2);
			
			System.out.println(fecha1.isBefore(fecha2));
		}
		else if (i == 2) {
			LocalTime fecha1 = LocalTime.of(19, 20);
			
			
			System.out.println(fecha1);
		}
		else if(i == 3) {
			// También podemos usar la clase Period de JavaTime
			
			Instant init = Instant.now();
			System.out.println(init);
			
			Thread.sleep(2000);
			
			Instant end = Instant.now();
			System.out.println(end);
			System.out.println();
			
			System.out.println(Duration.between(init, end));
			System.out.println(Duration.between(init, end).toHoursPart());
		} 
		else if(i == 4){
			String fecha = "01/03/2020";
			
			// Nuevo formateador
			DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			
			LocalDate fechaLocal = LocalDate.parse(fecha, formateador);
			System.out.println(fechaLocal);
			System.out.println(formateador.format(fechaLocal));
		}

	}

}

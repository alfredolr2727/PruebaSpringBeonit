package com.prueba.Prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PruebaApplication {

	/*
	* Por algún problema con la version de Maven y los plugins que utiliza a la hora de ejecutar los test no he podido
	* hacer que los ejecute todos automáticamente a través de Maven como me hubiera gustado. Y no he tenido tiempo
	* de investigar porque ocurría esto, los he ido ejecutando uno a uno desde la propia clase de test.
	* */
	public static void main(String[] args) {
		SpringApplication.run(PruebaApplication.class, args);
	}

}

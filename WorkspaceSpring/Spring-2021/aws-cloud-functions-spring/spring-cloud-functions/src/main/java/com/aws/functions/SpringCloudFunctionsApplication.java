package com.aws.functions;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.aws.functions.model.Book;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudFunctionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudFunctionsApplication.class, args);
	}
	
	@Bean
	public Function<String, String> reverse()
	{
		return (value) -> new StringBuilder(value).reverse().toString();
	}

	@Bean
	public Supplier<Book> getBook()
	{
		return () -> new Book(101, "Core Java");
	}

	@Bean
	public Consumer<String> printMessage()
	{
		return (input) -> System.out.println(input);
	}

}

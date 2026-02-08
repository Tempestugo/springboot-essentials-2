package org.example.springboot_2.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableAutoConfiguration
//@ComponentScan(basePackages = "academy.devodojo.springboot2")
//@Configuration

@SpringBootApplication
//Tem tudo que tem acima e mais
// Ou arrastar para a raíz da pasta que ai não precisa especificar o pacote
public class ApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);

    }
}

package academy.devodojo.springboot2.start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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

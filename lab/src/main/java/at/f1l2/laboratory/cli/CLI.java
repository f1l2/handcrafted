package at.f1l2.laboratory.cli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "at.f1l2.laboratory.cli", "at.f1l2.laboratory.encryption" })
public class CLI {

    public static void main(String[] args) {
        SpringApplication.run(CLI.class, args);
    }

}

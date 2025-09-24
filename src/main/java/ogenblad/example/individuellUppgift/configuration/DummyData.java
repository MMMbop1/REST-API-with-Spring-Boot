package ogenblad.example.individuellUppgift.configuration;

import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.repository.MemberDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyData {

    @Bean
    public CommandLineRunner initLoad(MemberDAO repo) {
        return args -> {
            Address address1 = new Address("Humblegatan 1B", "17239", "Sundbyberg");
            Address address2 = new Address("Blåmesvägen 54", "17254", "Karlskrona");

            repo.save(new Member("Ludvig", "Ogenblad", address1, "ludvigkaskogenblad@gmail.com", "0730251405", "199106051111"));
/*            repo.save(new Member("Eymi", "Chikito", address1, "chikito@gmail.com", "0730251422", "199106052222"));
            repo.save(new Member("Ture", "Turesson", address2, "turesson.ture@hotmail.com", "072737271", "200104040123"));*/

        };
    }
}

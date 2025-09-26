package ogenblad.example.individuellUppgift.configuration;

import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.repository.DaoMember;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class DummyData {

    @Bean
    public CommandLineRunner initLoad(DaoMember repo) {
        return args -> {
            List<Member> members = new ArrayList<>();

            Address address1 = new Address("Humblegatan 1B", "17239", "Sundbyberg");
            Address address2 = new Address("Blåmesvägen 54", "17254", "Karlskrona");
            Address address3 = new Address("Ällingavägen 5", "12312", "Lund");
            Address address4 = new Address("Rullstensgatan 12", "17254", "Umeå");

            Member member1 = new Member("Ludvig", "Ogenblad", address1, "ludvigkaskogenblad@gmail.com", "0730251405", "199106051111");
            Member member2 = new Member("Eymi", "Chikito", address1, "chikito@gmail.com", "0730251422", "199106052222");
            Member member3 = new Member("Ture", "Turesson", address2, "turesson.ture@hotmail.com", "072737271", "200104040123");
            Member member4 = new Member("Sven", "Jansson", address3, "Janne.sven@hotmail.com", "071231231", "199202018101231");
            Member member5 = new Member("Elin", "Benjaminsson", address4, "turesson.ture@hotmail.com", "072737271", "182323230102");

            members.add(member1);
            members.add(member2);
            members.add(member3);
            members.add(member4);
            members.add(member5);

            repo.saveAll(members);
        };
    }
}

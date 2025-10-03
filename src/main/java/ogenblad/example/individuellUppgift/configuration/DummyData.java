package ogenblad.example.individuellUppgift.configuration;

import ogenblad.example.individuellUppgift.entity.Address;
import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.repository.DaoMember;
import ogenblad.example.individuellUppgift.security.AppUser;
import ogenblad.example.individuellUppgift.security.Role;
import ogenblad.example.individuellUppgift.service.ServiceMember;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

@Configuration
public class DummyData {

    @Bean
    public CommandLineRunner initLoad(ServiceMember serviceMember, PasswordEncoder passwordEncoder) {
        return args -> {
            List<Member> members = new ArrayList<>();

            Address address1 = new Address("Humblegatan 1B", "17239", "Sundbyberg");
            Address address2 = new Address("Blåmesvägen 54", "17254", "Karlskrona");
            Address address3 = new Address("Ällingavägen 5", "12312", "Lund");
            Address address4 = new Address("Rullstensgatan 12", "17254", "Umeå");

            Member member1 = new Member("Ludvig", "Ogenblad", address1, "ludvigkaskogenblad@gmail.com", "0730251405", "199107081111");
            Member member2 = new Member("Eymi", "Chikito", address1, "chikito@gmail.com", "0730251422", "199106052222");
            Member member3 = new Member("Ture", "Turesson", address2, "turesson.ture@hotmail.com", "072737271", "200104040123");
            Member member4 = new Member("Sven", "Jansson", address3, "Janne.sven@hotmail.com", "071231231", "199202018101");
            Member member5 = new Member("Elin", "Benjaminsson", address4, "turesson.ture@hotmail.com", "072737271", "197202040151");


            AppUser appUser1 = new AppUser();
            appUser1.setUsername("admin");
            appUser1.setPassword(passwordEncoder.encode("admin"));
            appUser1.addRole(Role.ADMIN);
            appUser1.setMember(member1);

            member1.setAppUser(appUser1);

            AppUser appUser2 = new AppUser();
            appUser2.setUsername("user");
            appUser2.setPassword(passwordEncoder.encode("user"));
            appUser2.addRole(Role.USER);
            appUser2.setMember(member2);

            member2.setAppUser(appUser2);

            members.add(member1);
            members.add(member2);
            members.add(member3);
            members.add(member4);
            members.add(member5);

            serviceMember.saveAll(members);
        };
    }
}

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

            Address address1 = new Address("Tatooine Desert 1", "00001", "Tatooine");
            Address address2 = new Address("Alderaan Palace 54", "00002", "Alderaan");
            Address address3 = new Address("Dagobah Swamp 5", "00003", "Dagobah");
            Address address4 = new Address("Death Star Corridor 12", "00004", "Death Star");

            Member member1 = new Member("Luke", "Skywalker", address1, "luke@rebels.com", "0101010101", "198001010001");
            Member member2 = new Member("Leia", "Organa", address1, "leia@rebels.com", "0202020202", "197501010002");
            Member member3 = new Member("Yoda", "JediMaster", address2, "yoda@jedi.com", "0303030303", "199001010003");
            Member member4 = new Member("Darth", "Vader", address3, "vader@sith.com", "0404040404", "197711170004");
            Member member5 = new Member("Han", "Solo", address4, "han@rebels.com", "0505050505", "197703110005");

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

            AppUser appUser3 = new AppUser();
            appUser3.setUsername("user3");
            appUser3.setPassword(passwordEncoder.encode("user3"));
            appUser3.addRole(Role.USER);
            appUser3.setMember(member3);
            member3.setAppUser(appUser3);

            AppUser appUser4 = new AppUser();
            appUser4.setUsername("user4");
            appUser4.setPassword(passwordEncoder.encode("user4"));
            appUser4.addRole(Role.USER);
            appUser4.setMember(member4);
            member4.setAppUser(appUser4);

            AppUser appUser5 = new AppUser();
            appUser5.setUsername("user5");
            appUser5.setPassword(passwordEncoder.encode("user5"));
            appUser5.addRole(Role.USER);
            appUser5.setMember(member5);
            member5.setAppUser(appUser5);


            members.add(member1);
            members.add(member2);
            members.add(member3);
            members.add(member4);
            members.add(member5);

            serviceMember.saveAll(members);

        };
    }
}

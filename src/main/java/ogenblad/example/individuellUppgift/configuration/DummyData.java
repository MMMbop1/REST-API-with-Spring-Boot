package ogenblad.example.individuellUppgift.configuration;

import ogenblad.example.individuellUppgift.entity.Member;
import ogenblad.example.individuellUppgift.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DummyData {

    @Bean
    public CommandLineRunner dummyData(MemberRepository repo) {
        return args -> {
            repo.save(new Member("Ludvig", "Ogenblad", null, "ludvigkaskogenblad@gmail.com", "0730251405", "199106050157"));
        };
    }
}

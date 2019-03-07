package com.sqli.stories;

import com.sqli.stories.dao.EquipeMemberRepository;
import com.sqli.stories.dao.EquipeRepository;
import com.sqli.stories.dao.MemberRepository;
import com.sqli.stories.dao.RoleRepository;
import com.sqli.stories.entities.Equipe;
import com.sqli.stories.entities.EquipeMember;
import com.sqli.stories.entities.Member;
import com.sqli.stories.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoriesApplication implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EquipeRepository equipeRepository;
    @Autowired
    private EquipeMemberRepository equipeMemberRepository;

    public static void main(String[] args) {
        SpringApplication.run(StoriesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(new Role("Concepteur"));
        Role _role=roleRepository.save(new Role("Developer"));

        roleRepository.findAll().stream()
                .forEach(System.out::println);

        //*************
        Member _member=memberRepository.save(new Member("ussefshahid"));
        memberRepository.findAll().stream()
                .forEach(System.out::println);

        //*************
        Equipe _equipe=equipeRepository.save(new Equipe("Atlas"));
        equipeRepository.findAll().stream()
                .forEach(System.out::println);

        //*************
        equipeMemberRepository.save(new EquipeMember(_equipe, _member, _role));
        equipeMemberRepository.findAll().stream()
                .forEach(equipeMember -> System.out.println(equipeMember));
    }



}

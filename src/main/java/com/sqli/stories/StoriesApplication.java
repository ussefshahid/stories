package com.sqli.stories;

import com.sqli.stories.dao.TeamMemberRepository;
import com.sqli.stories.dao.TeamRepository;
import com.sqli.stories.dao.MemberRepository;
import com.sqli.stories.dao.RoleRepository;
import com.sqli.stories.entities.Team;
import com.sqli.stories.entities.TeamMember;
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
    private TeamRepository teamRepository;
    @Autowired
    private TeamMemberRepository teamMemberRepository;

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
        Team _team = teamRepository.save(new Team("Atlas"));
        _team.getMembers().forEach(System.out::println);
        teamRepository.findAll().stream()
                .forEach(System.out::println);

        //*************
        teamMemberRepository.save(new TeamMember(_team, _member, _role));
        teamMemberRepository.findAll().stream()
                .forEach(System.out::println);
    }
}

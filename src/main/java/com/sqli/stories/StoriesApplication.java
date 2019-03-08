package com.sqli.stories;

import com.sqli.stories.dao.MemberRepository;
import com.sqli.stories.dao.RoleRepository;
import com.sqli.stories.dao.TeamMemberRepository;
import com.sqli.stories.dao.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoriesApplication  {

    public static void main(String[] args) {
        SpringApplication.run(StoriesApplication.class, args);
    }


}

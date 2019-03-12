package com.sqli.stories.controller;

import com.sqli.stories.entities.Member;
import com.sqli.stories.entities.Role;
import com.sqli.stories.entities.Team;
import com.sqli.stories.entities.TeamMember;
import com.sqli.stories.helpers.types.ListTeamMemberType;
import com.sqli.stories.services.MemberService;
import com.sqli.stories.services.RoleService;
import com.sqli.stories.services.TeamMemberService;
import com.sqli.stories.services.TeamService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamMemberControllerTest {

    MockMvc mockMvc;

    @Mock
    private TeamMemberController teamMemberController;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private MemberService memberService;


    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(teamMemberController).build();
    }

    @Test
    public void testAddTeamMember() {

        HttpEntity<Object> member = getHttpEntity("{ \"login\":\"sqli\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1 = template.postForEntity("/api/member", member, Member.class);
        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("sqli", response1.getBody().getLogin());


        HttpEntity<Object> role = getHttpEntity("{\"name\": \"Developpeur\" }");
        ResponseEntity<Role> response2 = template.postForEntity("/api/role", role, Role.class);

        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Developpeur", response2.getBody().getName());

        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response3 = template.postForEntity("/api/team", team, Team.class);
        Assert.assertEquals(200, response3.getStatusCode().value());
        Assert.assertEquals("Atlas", response3.getBody().getName());

        HttpEntity<Object> teamMember = getHttpEntity("{\"team\":{\"id\":" + response3.getBody().getId() + "},\"member\":{\"login\":\"sqli\"},\"role\":{\"id\":" + response2.getBody().getId() + "}}");
        ResponseEntity<TeamMember> response4 = template.postForEntity("/api/teamMember", teamMember, TeamMember.class);
        Assert.assertEquals(200, response4.getStatusCode().value());

        //cleaning thea added teamMembers
        teamMemberService.delete(response4.getBody().getId());
        memberService.delete(response1.getBody().getLogin());
        roleService.delete(response2.getBody().getId());
        teamService.delete(response3.getBody().getId());

    }
    @Test
    public void testUpdateTeamMember(){

        HttpEntity<Object> member = getHttpEntity("{ \"login\":\"sqli\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1 = template.postForEntity("/api/member", member, Member.class);
        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("sqli", response1.getBody().getLogin());


        HttpEntity<Object> role = getHttpEntity("{\"name\": \"Developpeur\" }");
        ResponseEntity<Role> response2 = template.postForEntity("/api/role", role, Role.class);

        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Developpeur", response2.getBody().getName());

        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response3 = template.postForEntity("/api/team", team, Team.class);
        Assert.assertEquals(200, response3.getStatusCode().value());
        Assert.assertEquals("Atlas", response3.getBody().getName());

        HttpEntity<Object> teamMember = getHttpEntity("{\"team\":{\"id\":" + response3.getBody().getId() +"},\"member\":{\"login\":\"sqli\"},\"role\":{\"id\":" + response2.getBody().getId() + "}}");
        ResponseEntity<TeamMember> response4 = template.postForEntity("/api/teamMember", teamMember, TeamMember.class);
        Assert.assertEquals(200, response4.getStatusCode().value());
        Assert.assertEquals(response3.getBody().getId(),response4.getBody().getTeam().getId());

        HttpEntity<Object> team2 = getHttpEntity("{\"name\": \"Menara\" }");
        ResponseEntity<Team> response5 = template.postForEntity("/api/team", team2, Team.class);
        Assert.assertEquals(200, response5.getStatusCode().value());
        Assert.assertEquals("Menara", response5.getBody().getName());

        HttpEntity<Object> teamMember2 = getHttpEntity("{\"id\":"+response4.getBody().getId()+",\"team\":{\"id\":" + response5.getBody().getId() +"},\"member\":{\"login\":\"sqli\"},\"role\":{\"id\":" + response2.getBody().getId() + "}}");
        ResponseEntity<TeamMember> response6 = template.postForEntity("/api/teamMember", teamMember2, TeamMember.class);
        Assert.assertEquals(200, response6.getStatusCode().value());
        Assert.assertEquals(response4.getBody().getId(),response6.getBody().getId());
        int numberOfTeamMembers=teamMemberService.getAll().size();
        Assert.assertEquals(1,numberOfTeamMembers);

        //cleaning the added teamMembers
        teamMemberService.delete(response4.getBody().getId());
        teamService.delete(response3.getBody().getId());
        teamService.delete(response5.getBody().getId());
        memberService.delete(response1.getBody().getLogin());
        roleService.delete(response2.getBody().getId());

    }
    @Test
    public void testGetAllTeamMebers(){
        HttpEntity<Object> member = getHttpEntity("{ \"login\":\"sqli\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1 = template.postForEntity("/api/member", member, Member.class);
        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("sqli", response1.getBody().getLogin());


        HttpEntity<Object> role = getHttpEntity("{\"name\": \"Developpeur\" }");
        ResponseEntity<Role> response2 = template.postForEntity("/api/role", role, Role.class);

        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Developpeur", response2.getBody().getName());

        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response3 = template.postForEntity("/api/team", team, Team.class);
        Assert.assertEquals(200, response3.getStatusCode().value());
        Assert.assertEquals("Atlas", response3.getBody().getName());

        HttpEntity<Object> teamMember = getHttpEntity("{\"team\":{\"id\":" + response3.getBody().getId() +"},\"member\":{\"login\":\"sqli\"},\"role\":{\"id\":" + response2.getBody().getId() + "}}");
        ResponseEntity<TeamMember> response4 = template.postForEntity("/api/teamMember", teamMember, TeamMember.class);
        Assert.assertEquals(200, response4.getStatusCode().value());

        ResponseEntity<ListTeamMemberType> teamMembersResponse=template.getForEntity("/api/teamMembers", ListTeamMemberType.class);
        Assert.assertEquals(200,teamMembersResponse.getStatusCode().value());
        Assert.assertEquals(1,teamMembersResponse.getBody().size());

        //cleaning the added teamMembers
        teamMemberService.delete(response4.getBody().getId());
        memberService.delete(response1.getBody().getLogin());
        roleService.delete(response2.getBody().getId());
        teamService.delete(response3.getBody().getId());

    }
    @Test
    public void testDeletingTeamMember(){
        HttpEntity<Object> member = getHttpEntity("{ \"login\":\"sqli\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1 = template.postForEntity("/api/member", member, Member.class);
        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("sqli", response1.getBody().getLogin());


        HttpEntity<Object> role = getHttpEntity("{\"name\": \"Developpeur\" }");
        ResponseEntity<Role> response2 = template.postForEntity("/api/role", role, Role.class);

        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Developpeur", response2.getBody().getName());

        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response3 = template.postForEntity("/api/team", team, Team.class);
        Assert.assertEquals(200, response3.getStatusCode().value());
        Assert.assertEquals("Atlas", response3.getBody().getName());

        HttpEntity<Object> teamMember = getHttpEntity("{\"team\":{\"id\":" + response3.getBody().getId() +"},\"member\":{\"login\":\"sqli\"},\"role\":{\"id\":" + response2.getBody().getId() + "}}");
        ResponseEntity<TeamMember> response4 = template.postForEntity("/api/teamMember", teamMember, TeamMember.class);
        Assert.assertEquals(200, response4.getStatusCode().value());

        ResponseEntity<ListTeamMemberType> teamMembersResponse=template.getForEntity("/api/teamMembers", ListTeamMemberType.class);
        Assert.assertEquals(200,teamMembersResponse.getStatusCode().value());
        Assert.assertEquals(1,teamMembersResponse.getBody().size());

        //after deleting the team member
        teamMemberService.delete(response4.getBody().getId());
        teamMembersResponse=template.getForEntity("/api/teamMembers", ListTeamMemberType.class);
        Assert.assertEquals(0,teamMembersResponse.getBody().size());


        //cleaning the added teamMembers

        memberService.delete(response1.getBody().getLogin());
        roleService.delete(response2.getBody().getId());
        teamService.delete(response3.getBody().getId());


    }



    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }


}

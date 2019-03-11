package com.sqli.stories.controller;

import com.sqli.stories.dao.TeamRepository;
import com.sqli.stories.entities.Team;
import com.sqli.stories.helpers.types.ListTeamType;
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

import javax.xml.ws.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {
    MockMvc mockMVC;

    @Mock
    private TeamController teamController;
    @Autowired
    private TestRestTemplate template;
    @Autowired
    private TeamService teamService;
    @Autowired
    TeamRepository teamRepository;

    @Before
    public void setup() throws Exception {
        mockMVC = MockMvcBuilders.standaloneSetup(teamController).build();
    }

    @Test
    public void testAddEquipe() throws Exception {
        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response = template.postForEntity("/api/team", team, Team.class);
        Assert.assertEquals("Atlas", response.getBody().getName());
        Assert.assertEquals(200, response.getStatusCode().value());

        // deleting the added team
        teamRepository.deleteById(response.getBody().getId());

    }

    @Test
    public void testUpdateEquipe() throws Exception {
        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response = template.postForEntity("/api/team", team, Team.class);
        Assert.assertEquals("Atlas", response.getBody().getName());
        Assert.assertEquals(200, response.getStatusCode().value());

        Long id = response.getBody().getId();
        HttpEntity<Object> updatedTeam = getHttpEntity("{\"id\":" + id + ",\"name\":\"Menira\"}");
        ResponseEntity<Team> response2 = template.postForEntity("/api/team", updatedTeam, Team.class);
        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Menira", response2.getBody().getName());

        // deleting the added team
        teamRepository.deleteById(response2.getBody().getId());

    }
    @Test
    public void testGetAllTeams(){
        HttpEntity<Object> team1 = getHttpEntity("{\"name\": \"Atlas\" }");
        HttpEntity<Object> team2 = getHttpEntity("{\"name\": \"Abtal\" }");

        ResponseEntity<Team> response1 = template.postForEntity("/api/team", team1, Team.class);
        ResponseEntity<Team> response2 = template.postForEntity("/api/team", team2, Team.class);

        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("Atlas", response1.getBody().getName());

        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Abtal", response2.getBody().getName());

        ResponseEntity<ListTeamType> listTeams=template.getForEntity("/api/teams",ListTeamType.class);
        Assert.assertEquals(2,listTeams.getBody().size());

        //cleaning the added teams
        teamService.delete(response1.getBody().getId());
        teamService.delete(response2.getBody().getId());

    }
    @Test
    public void testGetTeamByName(){

        HttpEntity<Object> team1 = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response1 = template.postForEntity("/api/team", team1, Team.class);
        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("Atlas", response1.getBody().getName());

        ResponseEntity<Team> response2=template.getForEntity("/api/team/search/"+response1.getBody().getName(),Team.class);

        Assert.assertEquals(200,response2.getStatusCode().value());
        Assert.assertEquals("Atlas",response2.getBody().getName());

        ResponseEntity<Team> response3=template.getForEntity("/api/team/search/Abtal",Team.class);
        Assert.assertEquals(404,response3.getStatusCode().value());

        //cleaning the added team
        teamService.delete(response1.getBody().getId());
    }
   @Test
    public void testGetTeamById(){
        HttpEntity<Object> team1 = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response1 = template.postForEntity("/api/team", team1, Team.class);
        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("Atlas", response1.getBody().getName());

        ResponseEntity<Team> response2=template.getForEntity("/api/team/"+response1.getBody().getId(),Team.class);

        Assert.assertEquals(200,response2.getStatusCode().value());
        Assert.assertEquals("Atlas",response2.getBody().getName());

        ResponseEntity<Team> response3=template.getForEntity("/api/team/14598215",Team.class);
        Assert.assertEquals(404,response3.getStatusCode().value());

        //cleaning thea added teams

       teamService.delete(response1.getBody().getId());
    }

   @Test
    public void  testDeletingTeam(){
       HttpEntity<Object> team1 = getHttpEntity("{\"name\": \"Atlas\" }");
       ResponseEntity<Team> response1 = template.postForEntity("/api/team", team1, Team.class);
       Assert.assertEquals(200, response1.getStatusCode().value());
       Assert.assertEquals("Atlas", response1.getBody().getName());

       HttpEntity<Object> team2 = getHttpEntity("{\"name\": \"Menira\" }");
       ResponseEntity<Team> response2 = template.postForEntity("/api/team", team2, Team.class);
       Assert.assertEquals(200, response2.getStatusCode().value());
       Assert.assertEquals("Menira", response2.getBody().getName());

       ResponseEntity<ListTeamType> teamsResponse=template.getForEntity("/api/teams",ListTeamType.class);

       Assert.assertEquals(200,teamsResponse.getStatusCode().value());
       Assert.assertEquals(2,teamsResponse.getBody().size());

       teamService.delete(response2.getBody().getId());

       ResponseEntity<ListTeamType> teamsResponseAfter=template.getForEntity("/api/teams",ListTeamType.class);

       Assert.assertEquals(200,teamsResponse.getStatusCode().value());
       Assert.assertEquals(1,teamsResponseAfter.getBody().size());


       //deleting the added teams
       teamService.delete(response1.getBody().getId());




    }



    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }


}

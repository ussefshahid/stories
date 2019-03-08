package com.sqli.stories.controller;

import com.sqli.stories.dao.TeamRepository;
import com.sqli.stories.entities.Team;
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

import java.util.List;

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
        int teamNumbersBefore = teamService.getAll().size();
        Assert.assertEquals(1, teamNumbersBefore);

        Long id = response.getBody().getId();
        HttpEntity<Object> updatedTeam = getHttpEntity("{\"id\":" + id + ",\"name\":\"Menira\"}");
        ResponseEntity<Team> response2 = template.postForEntity("/api/team", updatedTeam, Team.class);
        Assert.assertEquals("Menira", response2.getBody().getName());
        Assert.assertEquals(200, response2.getStatusCode().value());
        int teamNumbersAfter = teamService.getAll().size();
        Assert.assertEquals(1, teamNumbersAfter);

        // deleting the added team
        teamRepository.deleteById(response.getBody().getId());
    }

    @Test
    public void testgetTeamByName() {
        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response = template.postForEntity("/api/team", team, Team.class);

        Assert.assertEquals("Atlas", response.getBody().getName());
        Assert.assertEquals(200, response.getStatusCode().value());

        ResponseEntity<Team> response2 = template.getForEntity("/api/team/search/" + response.getBody().getName(), Team.class);
        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Atlas", response2.getBody().getName());


        ResponseEntity<Team> response3 = template.getForEntity("/api/team/search/Error", Team.class);
        Assert.assertEquals(404, response3.getStatusCode().value());

        //deleting the added team
        teamRepository.deleteById(response.getBody().getId());


    }

    @Test
    public void testGetTeams() {
        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response1 = template.postForEntity("/api/team", team, Team.class);

        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("Atlas", response1.getBody().getName());

        HttpEntity<Object> team2 = getHttpEntity("{\"name\": \"Abtal\" }");
        ResponseEntity<Team> response2 = template.postForEntity("/api/team", team2, Team.class);

        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Abtal", response2.getBody().getName());

        int teamsNumber = teamService.getAll().size();

        Assert.assertEquals(2, teamsNumber);

        //cleaning the added teams
        teamRepository.deleteById(response1.getBody().getId());
        teamRepository.deleteById(response2.getBody().getId());

    }

    @Test
    public void testDeletingTeam() {
        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response1 = template.postForEntity("/api/team", team, Team.class);

        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("Atlas", response1.getBody().getName());

        HttpEntity<Object> team2 = getHttpEntity("{\"name\": \"Abtal\" }");
        ResponseEntity<Team> response2 = template.postForEntity("/api/team", team2, Team.class);

        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Abtal", response2.getBody().getName());

        teamService.delete(response2.getBody().getId());

        Team teamTest = teamService.getAll().get(0);

        Assert.assertEquals("Atlas", teamTest.getName());

        //cleaning the added team
        teamRepository.deleteById(response1.getBody().getId());

    }

    @Test
    public void testGetTeamById() {
        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response1 = template.postForEntity("/api/team", team, Team.class);

        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("Atlas", response1.getBody().getName());

        ResponseEntity<Team> response2 = template.getForEntity("/api/team/" + response1.getBody().getId(), Team.class);
        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals("Atlas", response2.getBody().getName());

        ResponseEntity<Team> response3 = template.getForEntity("/api/search/team/145982245", Team.class);
        Assert.assertEquals(404, response3.getStatusCode().value());

        //cleaning the added team
        teamRepository.deleteById(response1.getBody().getId());


    }


    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }


}

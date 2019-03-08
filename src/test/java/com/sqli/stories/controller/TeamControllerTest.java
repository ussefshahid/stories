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
        HttpEntity<Object> equipe = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response = template.postForEntity("/app/equipe", equipe, Team.class);
        Assert.assertEquals("Atlas", response.getBody().getName());
        Assert.assertEquals(200, response.getStatusCode().value());

        // deleting the added team
        teamRepository.deleteById(response.getBody().getId());

    }

    @Test
    public void testUpdateEquipe() throws Exception {
        HttpEntity<Object> team = getHttpEntity("{\"name\": \"Atlas\" }");
        ResponseEntity<Team> response = template.postForEntity("/app/equipe", team, Team.class);
        Assert.assertEquals("Atlas", response.getBody().getName());
        Assert.assertEquals(200, response.getStatusCode().value());

        Long id = response.getBody().getId();
        HttpEntity<Object> updatedTeam = getHttpEntity("{\"id\":" + id + ",\"name\":\"Menira\"}");

        // deleting the added team
        teamRepository.deleteById(response.getBody().getId());

    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }


}

package com.sqli.stories.controller;


import com.sqli.stories.entities.Sprint;
import com.sqli.stories.helpers.types.ListSprintType;
import com.sqli.stories.services.SprintService;
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

import java.time.LocalDate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SprintControllerTest {

    MockMvc mockMvc;
    @Mock
    private SprintController sprintController;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private SprintService sprintService;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(sprintController).build();
    }

    @Test
    public void testAddSprint() {

        LocalDate currentDate = LocalDate.now();
        HttpEntity<Object> sprint = getHttpEntity("{\"numero\": 123 }");
        ResponseEntity<Sprint> response = template.postForEntity("/api/sprint", sprint, Sprint.class);
        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals(currentDate, response.getBody().getDateDebut());

        //cleaning the added sprint
        sprintService.delete(response.getBody().getNumero());
    }

    @Test
    public void testUpdateSprint() {
        LocalDate currentDate = LocalDate.now();
        currentDate = currentDate.plusDays(1);
        HttpEntity<Object> sprint = getHttpEntity("{\"numero\": 123 ,\"dateDebut\":\"2019-03-12\" }");
        ResponseEntity<Sprint> response = template.postForEntity("/api/sprint", sprint, Sprint.class);
        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals(currentDate, response.getBody().getDateDebut());

        HttpEntity<Object> sprint2 = getHttpEntity("{\"numero\": 123 ,\"dateDebut\":\"2019-03-13\" }");
        ResponseEntity<Sprint> response2 = template.postForEntity("/api/sprint", sprint2, Sprint.class);
        Assert.assertEquals(200, response2.getStatusCode().value());
        currentDate = currentDate.plusDays(1);
        Assert.assertEquals(currentDate, response2.getBody().getDateDebut());
        int numbersOfSprints = sprintService.getAll().size();
        Assert.assertEquals(1, numbersOfSprints);

        //cleaning the addded sprints
        sprintService.delete(response2.getBody().getNumero());
    }

    @Test
    public void testGetAllSprints() {
        LocalDate currentDate = LocalDate.now();
        HttpEntity<Object> sprint = getHttpEntity("{\"numero\": 123 }");
        ResponseEntity<Sprint> response = template.postForEntity("/api/sprint", sprint, Sprint.class);
        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals(currentDate, response.getBody().getDateDebut());

        HttpEntity<Object> sprint2 = getHttpEntity("{\"numero\": 523 }");
        ResponseEntity<Sprint> response2 = template.postForEntity("/api/sprint", sprint2, Sprint.class);
        Assert.assertEquals(200, response2.getStatusCode().value());
        Assert.assertEquals(currentDate, response2.getBody().getDateDebut());

        ResponseEntity<ListSprintType> sprintsList = template.getForEntity("/api/sprints", ListSprintType.class);
        Assert.assertEquals(200, sprintsList.getStatusCode().value());
        Assert.assertEquals(2, sprintsList.getBody().size());

        //cleaning the added sprints
        sprintService.delete(response.getBody().getNumero());
        sprintService.delete(response2.getBody().getNumero());

    }

    @Test
    public void testGetSprintById() {
        LocalDate currentDate = LocalDate.now();
        HttpEntity<Object> sprint = getHttpEntity("{\"numero\": 123 }");
        ResponseEntity<Sprint> response = template.postForEntity("/api/sprint", sprint, Sprint.class);
        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals(currentDate, response.getBody().getDateDebut());

        ResponseEntity<Sprint> getSprintByIdResponse = template.getForEntity("/api/sprint/" + response.getBody().getNumero(), Sprint.class);
        Assert.assertEquals(200, getSprintByIdResponse.getStatusCode().value());

        //try to get unexisting sprint
        ResponseEntity<Sprint> getUnexistingSprint = template.getForEntity("/api/sprint/175447974", Sprint.class);
        Assert.assertEquals(404, getUnexistingSprint.getStatusCode().value());

        //cleaning the added sprints
        sprintService.delete(response.getBody().getNumero());

    }

    @Test
    public void testDeletingSprint() {
        LocalDate currentDate = LocalDate.now();
        HttpEntity<Object> sprint = getHttpEntity("{\"numero\": 123 }");
        ResponseEntity<Sprint> response = template.postForEntity("/api/sprint", sprint, Sprint.class);
        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals(currentDate, response.getBody().getDateDebut());

        ResponseEntity<ListSprintType> sprintsListBeforeDeletingSprint = template.getForEntity("/api/sprints", ListSprintType.class);
        Assert.assertEquals(200, sprintsListBeforeDeletingSprint.getStatusCode().value());
        Assert.assertEquals(1, sprintsListBeforeDeletingSprint.getBody().size());

        //deleting the sprint
        sprintService.delete(response.getBody().getNumero());

        ResponseEntity<ListSprintType> sprintsListAfterDeletingSprint = template.getForEntity("/api/sprints", ListSprintType.class);
        Assert.assertEquals(200, sprintsListAfterDeletingSprint.getStatusCode().value());
        Assert.assertEquals(0, sprintsListAfterDeletingSprint.getBody().size());




    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }

}

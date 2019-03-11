package com.sqli.stories.controller;

import com.sqli.stories.entities.Story;
import com.sqli.stories.helpers.types.ListStoryType;
import com.sqli.stories.services.StoryService;
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
public class StroryControllerTest {

    MockMvc mockMvc;
    @Mock
    private StoryController storyController;

    @Autowired
    private TestRestTemplate template;
    @Autowired
    private StoryService storyService;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(storyController).build();
    }
    @Test
    public void testAddStory(){
        HttpEntity<Object> story=getHttpEntity("{\"jiraKey\":145 , \"title\":\"include all payment methods\"}");
        ResponseEntity<Story> response=template.postForEntity("/api/story",story,Story.class);
        Assert.assertEquals(200,response.getStatusCode().value());
        long jiraKey=response.getBody().getJiraKey();
        Assert.assertEquals(145,jiraKey);
        //cleaning the added stories
        storyService.delete(jiraKey);
    }
    @Test
    public void testUpdateStory(){
        HttpEntity<Object> story=getHttpEntity("{\"jiraKey\":145 , \"title\":\"include all payment methods\",\"storyPoint\":10}");
        ResponseEntity<Story> response=template.postForEntity("/api/story",story,Story.class);
        Assert.assertEquals(200,response.getStatusCode().value());
        long jiraKey=response.getBody().getJiraKey();
        Assert.assertEquals(145,jiraKey);

        HttpEntity<Object> story2=getHttpEntity("{\"jiraKey\":145 , \"title\":\"include all payment methods\",\"storyPoint\":13}");
        ResponseEntity<Story> response2=template.postForEntity("/api/story",story2,Story.class);
        Assert.assertEquals(200,response2.getStatusCode().value());
        jiraKey=response2.getBody().getJiraKey();
        Assert.assertEquals(145,jiraKey);
        int storyPoint=response2.getBody().getStoryPoint();
        Assert.assertEquals(13,storyPoint);

        //cleaning the added  stories
        storyService.delete(jiraKey);

    }
    @Test
    public void testGetAllStories(){
        HttpEntity<Object> story=getHttpEntity("{\"jiraKey\":145 , \"title\":\"include all payment methods\",\"storyPoint\":10}");
        ResponseEntity<Story> response=template.postForEntity("/api/story",story,Story.class);
        Assert.assertEquals(200,response.getStatusCode().value());

        HttpEntity<Object> story2=getHttpEntity("{\"jiraKey\":146 , \"title\":\"check if all necessary information are included\",\"storyPoint\":7}");
        ResponseEntity<Story> response2=template.postForEntity("/api/story",story2,Story.class);
        Assert.assertEquals(200,response2.getStatusCode().value());

        HttpEntity<ListStoryType> listStoryResponse=template.getForEntity("/api/stories",ListStoryType.class);
        Assert.assertEquals(200,((ResponseEntity<ListStoryType>) listStoryResponse).getStatusCode().value());
        Assert.assertEquals(2,listStoryResponse.getBody().size());

        //cleaning the added stories
        storyService.delete(response.getBody().getJiraKey());
        storyService.delete(response2.getBody().getJiraKey());

    }


    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }

}

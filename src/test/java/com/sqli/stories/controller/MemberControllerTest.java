package com.sqli.stories.controller;

import com.sqli.stories.entities.Member;
import com.sqli.stories.helpers.types.ListMemberType;
import com.sqli.stories.services.MemberService;
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
public class MemberControllerTest {

    MockMvc mockMvc;
    @Mock
    private MemberController memberController;

    @Autowired
    private TestRestTemplate template;
    @Autowired
    private MemberService memberService;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }

    @Test
    public void testAddMember(){
        HttpEntity<Object> member=getHttpEntity("{ \"login\":\"sqli\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1=template.postForEntity("/api/member",member, Member.class);
        Assert.assertEquals(200,response1.getStatusCode().value());
        Assert.assertEquals("sqli",response1.getBody().getLogin());

        //cleaning the added members
        memberService.delete(response1.getBody().getLogin());
    }
    @Test
    public void testUpdateMember(){
        HttpEntity<Object> member=getHttpEntity("{ \"login\":\"sqli\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1=template.postForEntity("/api/member",member, Member.class);
        Assert.assertEquals(200,response1.getStatusCode().value());
        Assert.assertEquals("sqli",response1.getBody().getLogin());

        HttpEntity<Object> memberUpdated=getHttpEntity("{ \"login\":\"sqli\",\"firstName\":\"Hello1\",\"lastName\":\"world1\"}");
        ResponseEntity<Member> response2=template.postForEntity("/api/member",memberUpdated, Member.class);
        Assert.assertEquals(200,response2.getStatusCode().value());
        int membersSize=memberService.getAll().size();
        Assert.assertEquals(1,membersSize);

        //cleaning the added members
        memberService.delete(response2.getBody().getLogin());
    }
    @Test
    public void testGetAllMembers(){
        HttpEntity<Object> member=getHttpEntity("{ \"login\":\"sqlir\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1=template.postForEntity("/api/member",member, Member.class);
        Assert.assertEquals(200,response1.getStatusCode().value());
        Assert.assertEquals("sqlir",response1.getBody().getLogin());

        HttpEntity<Object> member2=getHttpEntity("{ \"login\":\"sqlio\",\"firstName\":\"Hello1\",\"lastName\":\"world2\"}");
        ResponseEntity<Member> response2=template.postForEntity("/api/member",member2, Member.class);
        Assert.assertEquals(200,response2.getStatusCode().value());
        Assert.assertEquals("sqlio",response2.getBody().getLogin());

        ResponseEntity<ListMemberType> listMembersResponse=template.getForEntity("/api/members",ListMemberType.class);
        Assert.assertEquals(200,listMembersResponse.getStatusCode().value());
        Assert.assertEquals(2,listMembersResponse.getBody().size());

        //cleaning the added team
        memberService.delete(response1.getBody().getLogin());
        memberService.delete(response2.getBody().getLogin());

    }
    @Test
    public void testGetMemberByLogin(){
        HttpEntity<Object> member=getHttpEntity("{ \"login\":\"sqlir\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1=template.postForEntity("/api/member",member, Member.class);
        Assert.assertEquals(200,response1.getStatusCode().value());
        Assert.assertEquals("sqlir",response1.getBody().getLogin());

        ResponseEntity<Member> response2=template.getForEntity("/api/member/"+response1.getBody().getLogin(),Member.class);
        Assert.assertEquals(200,response2.getStatusCode().value());
        Assert.assertEquals("Hello",response2.getBody().getFirstName());

        ResponseEntity<Member> response3=template.getForEntity("/api/member/notFound",Member.class);
        Assert.assertEquals(404,response3.getStatusCode().value());

        //cleaning the added members
        memberService.delete(response1.getBody().getLogin());


    }
    @Test
    public void testDeletingMember(){
        HttpEntity<Object> member=getHttpEntity("{ \"login\":\"sqlir\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1=template.postForEntity("/api/member",member, Member.class);
        Assert.assertEquals(200,response1.getStatusCode().value());
        Assert.assertEquals("sqlir",response1.getBody().getLogin());

        HttpEntity<Object> member2=getHttpEntity("{ \"login\":\"sqlio\",\"firstName\":\"Hello1\",\"lastName\":\"world1\"}");
        ResponseEntity<Member> response2=template.postForEntity("/api/member",member2, Member.class);
        Assert.assertEquals(200,response2.getStatusCode().value());
        Assert.assertEquals("sqlio",response2.getBody().getLogin());

        ResponseEntity<ListMemberType> membersResponse=template.getForEntity("/api/members",ListMemberType.class);
        Assert.assertEquals(200,membersResponse.getStatusCode().value());
        Assert.assertEquals(2,membersResponse.getBody().size());

        memberService.delete(response2.getBody().getLogin());

        ResponseEntity<ListMemberType> membersResponseAfter=template.getForEntity("/api/members",ListMemberType.class);
        Assert.assertEquals(200,membersResponseAfter.getStatusCode().value());
        Assert.assertEquals(1,membersResponseAfter.getBody().size());

        //cleaning the added members
        memberService.delete(response1.getBody().getLogin());
    }
    @Test
    public void testSearchMemberByKeyword(){
        HttpEntity<Object> member=getHttpEntity("{ \"login\":\"sqlir\",\"firstName\":\"Hello\",\"lastName\":\"world\"}");
        ResponseEntity<Member> response1=template.postForEntity("/api/member",member, Member.class);
        Assert.assertEquals(200,response1.getStatusCode().value());
        Assert.assertEquals("sqlir",response1.getBody().getLogin());

        ResponseEntity<ListMemberType> membersResponse=template.getForEntity("/api/member/search/l",ListMemberType.class);
        Assert.assertEquals(200,membersResponse.getStatusCode().value());
        Assert.assertEquals(1,membersResponse.getBody().size());

        ResponseEntity<ListMemberType> membersResponse2=template.getForEntity("/api/member/search/p",ListMemberType.class);
        Assert.assertEquals(200,membersResponse2.getStatusCode().value());
        Assert.assertEquals(0,membersResponse2.getBody().size());

        //cleaning the added members
        memberService.delete(response1.getBody().getLogin());
    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }
}

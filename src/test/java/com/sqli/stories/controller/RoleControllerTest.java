package com.sqli.stories.controller;

import com.sqli.stories.entities.Role;
import com.sqli.stories.helpers.types.ListRolesType;
import com.sqli.stories.services.RoleService;
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
public class RoleControllerTest {
    MockMvc mockMvc;
    @Mock
    private RoleController roleController;
    @Autowired
    private TestRestTemplate template;

    @Autowired
    private RoleService roleService;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(roleController).build();
    }

    @Test
    public void testAddRole() throws Exception {
        HttpEntity<Object> role = getHttpEntity("{\"name\": \"Developpeur\" }");
        ResponseEntity<Role> response = template.postForEntity("/api/role", role, Role.class);

        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals("Developpeur", response.getBody().getName());

        // deleting the added team
        roleService.delete(response.getBody().getId());
    }

    @Test
    public void testUpdateRole() throws Exception {
        HttpEntity<Object> role = getHttpEntity("{\"name\": \"Developpeur\" }");
        ResponseEntity<Role> response = template.postForEntity("/api/role", role, Role.class);
        Assert.assertEquals("Developpeur", response.getBody().getName());
        Assert.assertEquals(200, response.getStatusCode().value());
        int roleNumberBefore =roleService.getAll().size();
        Assert.assertEquals(1, roleNumberBefore);


        HttpEntity<Object> updatedRole = getHttpEntity("{\"id\":"+response.getBody().getId()+",\"name\":\"CP\"}");
        ResponseEntity<Role> response2 = template.postForEntity("/api/role", updatedRole, Role.class);
        Assert.assertEquals("CP", response2.getBody().getName());
        Assert.assertEquals(200, response2.getStatusCode().value());
        int teamNumbersAfter = roleService.getAll().size();
        Assert.assertEquals(1, teamNumbersAfter);

        // deleting the added team
        roleService.delete(response.getBody().getId());
    }

    @Test
    public void tesGetAllRoles() {
        HttpEntity<Object> role = getHttpEntity("{\"name\": \"Developpeur\" }");
        ResponseEntity<Role> response = template.postForEntity("/api/role", role, Role.class);

        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals("Developpeur", response.getBody().getName());

        HttpEntity<Object> role1 = getHttpEntity("{\"name\": \"CP\" }");
        ResponseEntity<Role> response1 = template.postForEntity("/api/role", role1, Role.class);

        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("CP", response1.getBody().getName());
        ResponseEntity<ListRolesType> roles=template.getForEntity("/api/roles",ListRolesType.class);

        Assert.assertEquals(2, roles.getBody().size());

        //deleting the added Role
        roleService.delete(response.getBody().getId());
        roleService.delete(response1.getBody().getId());
    }

    @Test
    public void testDeletingRole() {
        HttpEntity<Object> role = getHttpEntity("{\"name\": \"Developpeur\" }");
        ResponseEntity<Role> response = template.postForEntity("/api/role", role, Role.class);

        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals("Developpeur", response.getBody().getName());

        HttpEntity<Object> role1 = getHttpEntity("{\"name\": \"CP\" }");
        ResponseEntity<Role> response1 = template.postForEntity("/api/role", role1, Role.class);

        Assert.assertEquals(200, response1.getStatusCode().value());
        Assert.assertEquals("CP", response1.getBody().getName());

        roleService.delete(response1.getBody().getId());

        Role role2 = roleService.getAll().get(0);
        Assert.assertEquals("Developpeur", role2.getName());


        //cleaning the added role
        roleService.delete(role2.getId());

    }

    @Test
    public void testGetRoleByName() {
        HttpEntity<Object> role = getHttpEntity("{\"name\": \"Developpeur\" }");
        ResponseEntity<Role> response = template.postForEntity("/api/role", role, Role.class);

        Assert.assertEquals(200, response.getStatusCode().value());
        Assert.assertEquals("Developpeur", response.getBody().getName());

        ResponseEntity<Role> response2=template.getForEntity("/api/role/"+response.getBody().getName(),Role.class);
        Assert.assertEquals(200,response2.getStatusCode().value());


    }

    private HttpEntity<Object> getHttpEntity(Object body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<Object>(body, headers);
    }


}

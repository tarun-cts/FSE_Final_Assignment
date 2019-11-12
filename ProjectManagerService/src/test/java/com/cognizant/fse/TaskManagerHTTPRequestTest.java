package com.cognizant.fse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.fse.entity.ParentTask;
import com.cognizant.fse.entity.Project;
import com.cognizant.fse.entity.Task;
import com.cognizant.fse.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaskManagerHTTPRequestTest {

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void optionsTest()throws Exception{
		Set<HttpMethod> optionsForAllow = restTemplate.optionsForAllow("http://localhost:"+port+"/projectManagerService/");
		HttpMethod[] supportedMethods
		  = {HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE};
		assertTrue(optionsForAllow.containsAll(Arrays.asList(supportedMethods)));
	}
	
	@Test
	public void greetingTextShouldReturndefaultMsg()throws Exception{
		assertThat(restTemplate.getForObject("http://localhost:"+port+"/projectManagerService/Tarun", String.class)).contains("Hello Tarun!");
	}
	
	@Test
	public void getUsersShouldReturnNotEmpty()throws Exception{
		assertThat(restTemplate.getForObject("http://localhost:"+port+"/projectManagerService/getUsers", List.class)).isNotEmpty();;
	}
	@Test
	public void postNewUser()throws Exception{
		User user = new User(0,"Bill","Price","EMP1001");
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/createUser";
		URI uri = new URI(baseUrl);
		HttpEntity<User> request = new HttpEntity<>(user);
		ResponseEntity<User> result = restTemplate.postForEntity(uri, request,User.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.cognizant.fse.entity.User", result.getBody().getClass().toString());
		assertEquals("Bill",result.getBody().getFirstName());
		assertEquals("Price",result.getBody().getLastName());
		assertEquals("EMP1001",result.getBody().getEmployeeId());
		assertEquals("New User id is one",1,result.getBody().getUserId());
		assertNotEquals("New User id is Not Zero ",0,result.getBody().getUserId());
		
	}
	@Test
	public void postEmptyUser()throws Exception{
		User user = new User();
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/createUser";
		URI uri = new URI(baseUrl);
		HttpEntity<User> request = new HttpEntity<>(user);
		ResponseEntity<User> result = restTemplate.postForEntity(uri, request,User.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals("class com.cognizant.fse.entity.User", result.getBody().getClass().toString());
		assertEquals(null,result.getBody().getFirstName());
		assertEquals(null,result.getBody().getLastName());
		assertEquals(null,result.getBody().getEmployeeId());
		//assertEquals("New User id is two",2,result.getBody().getUserId());
		assertNotEquals("New User id is Not Zero ",0,result.getBody().getUserId());
		
	}
	@Test
	public void updateExistingUser()throws Exception{
		User user = new User(1,"Bill","Sheere","EMP1001");
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/updateUser";
		URI uri = new URI(baseUrl);
		HttpEntity<User> request = new HttpEntity<>(user);
		ResponseEntity<User> result = restTemplate.exchange(uri,HttpMethod.PUT, request,User.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals("class com.cognizant.fse.entity.User", result.getBody().getClass().toString());
		assertEquals("Bill",result.getBody().getFirstName());
		assertNotEquals("Price",result.getBody().getLastName());
		assertEquals("Sheere",result.getBody().getLastName());
		assertEquals("EMP1001",result.getBody().getEmployeeId());
		//assertEquals("New User id is one",1,result.getBody().getUserId());
		assertNotEquals("New User id is Not Zero ",0,result.getBody().getUserId());
		
	}
	@Test
	public void deleteExistingUser()throws Exception{		
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/deleteUser/1";
		URI uri = new URI(baseUrl);		
		restTemplate.delete(uri);		
	}
	
	@Test
	public void postNewProject()throws Exception{
		User user = new User(1,"Bill","Sheere","EMP1001");
		Project project = new Project(0,"GREEN",new Date(),new Date(),1,user,2,2);
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/createProject?userId=1";
		URI uri = new URI(baseUrl);
		HttpEntity<Project> request = new HttpEntity<>(project);
		ResponseEntity<Project> result = restTemplate.postForEntity(uri, request,Project.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.cognizant.fse.entity.Project", result.getBody().getClass().toString());
		assertEquals("GREEN",result.getBody().getProjectName());
		assertNotNull(result.getBody().getUser());
		assertNotNull(result.getBody().getPriority());		
	}
	@Test
	public void postNewProjectWithNullUser()throws Exception{
		//User user = new User(1,"Bill","Sheere","EMP1001");
		Project project = new Project(0,"GREEN",new Date(),new Date(),1,null,2,2);
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/createProject?userId=1";
		URI uri = new URI(baseUrl);
		HttpEntity<Project> request = new HttpEntity<>(project);
		ResponseEntity<Project> result = restTemplate.postForEntity(uri, request,Project.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.cognizant.fse.entity.Project", result.getBody().getClass().toString());
		assertEquals("GREEN",result.getBody().getProjectName());
		assertNotNull(result.getBody().getPriority());		
	}
	@Test
	public void getProjectsShouldReturnNotEmpty()throws Exception{
		assertThat(restTemplate.getForObject("http://localhost:"+port+"/projectManagerService/getProjects", List.class)).isNotEmpty();;
	}
	@Test
	public void updateNewProjects()throws Exception{
		User user = new User(1,"Bill","Sheere","EMP1001");
		Project project = new Project(1,"GREEN",new Date(),new Date(),1,user,2,2);
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/updateProject?userId=1";
		URI uri = new URI(baseUrl);
		HttpEntity<Project> request = new HttpEntity<>(project);
		ResponseEntity<Project> result = restTemplate.exchange(uri,HttpMethod.PUT, request,Project.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.cognizant.fse.entity.Project", result.getBody().getClass().toString());
		assertEquals("GREEN",result.getBody().getProjectName());
		assertNotNull(result.getBody().getPriority());		
	}
	@Test
	public void postNewParentTask()throws Exception{
		ParentTask ptask = new ParentTask(0,"ParentTask1");
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/createParentTask";
		URI uri = new URI(baseUrl);
		HttpEntity<ParentTask> request = new HttpEntity<>(ptask);
		ResponseEntity<ParentTask> result = restTemplate.postForEntity(uri, request,ParentTask.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.cognizant.fse.entity.ParentTask", result.getBody().getClass().toString());
		assertEquals("ParentTask1",result.getBody().getParentTaskName());
		assertEquals("New Parent Task id is one",1,result.getBody().getParentId());
	}
	@Test
	public void postNewTask()throws Exception{
		ParentTask ptask = new ParentTask(1,"ParentTask1");
		User user = new User(1,"Bill","Sheere","EMP1001");
		Project project = new Project(1,"GREEN",new Date(),new Date(),1,user,2,2);
		Task task = new Task(0,ptask,project,user,"DesignTask",new Date(), new Date(),1,"ACT");
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/createTask?parentId=1&projectId=1&userId=1";
		URI uri = new URI(baseUrl);
		HttpEntity<Task> request = new HttpEntity<>(task);
		ResponseEntity<Task> result = restTemplate.exchange(uri,HttpMethod.POST, request,Task.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.cognizant.fse.entity.Task", result.getBody().getClass().toString());
		assertEquals("DesignTask",result.getBody().getTaskName());
		//assertNotNull(result.getBody().getParentTask());
		//assertNotNull(result.getBody().getProject());
		//assertNotNull(result.getBody().getUser());
		assertEquals("ACT",result.getBody().getStatus());
		assertNotNull(result.getBody().getPriority());		
	}
	@Test
	public void updateNewTask()throws Exception{
		ParentTask ptask = new ParentTask(1,"ParentTask1");
		User user = new User(1,"Bill","Sheere","EMP1001");
		Project project = new Project(1,"GREEN",new Date(),new Date(),1,user,2,2);
		Task task = new Task(1,ptask,project,user,"DesignTask",new Date(), new Date(),1,"END");
		final String baseUrl = "http://localhost:"+port+"/projectManagerService/updateTask?parentId=1&projectId=1&userId=1";
		URI uri = new URI(baseUrl);
		HttpEntity<Task> request = new HttpEntity<>(task);
		ResponseEntity<Task> result = restTemplate.exchange(uri,HttpMethod.PUT, request,Task.class);
		assertEquals(200, result.getStatusCodeValue());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
		assertEquals("class com.cognizant.fse.entity.Task", result.getBody().getClass().toString());
		assertEquals("DesignTask",result.getBody().getTaskName());
		//assertNotNull(result.getBody().getParentTask());
		//assertNotNull(result.getBody().getProject());
		//assertNotNull(result.getBody().getUser());
		assertEquals("END",result.getBody().getStatus());
		assertNotNull(result.getBody().getPriority());		
	}
	@Test
	public void getTasksShouldReturnNotEmpty()throws Exception{
		assertThat(restTemplate.getForObject("http://localhost:"+port+"/projectManagerService/getTasks", List.class)).isNotEmpty();;
	}
	@Test
	public void getTaskByTaskId()throws Exception{
		assertThat(restTemplate.getForObject("http://localhost:"+port+"/projectManagerService/getTaskById?taskId=1", Task.class)).isNotNull();;
	}
	@Test
	public void getTaskByProjectId()throws Exception{
		assertThat(restTemplate.getForObject("http://localhost:"+port+"/projectManagerService/getTasksByPorjectId?projectId=1", List.class)).isEmpty();;
	}
}

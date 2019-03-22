package com.luana.videolocadora;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;
import com.luana.videolocadora.models.Movie;
import com.luana.videolocadora.models.Rent;
import com.luana.videolocadora.repository.MoviesRepository;
import com.luana.videolocadora.repository.RentsRepository;
import com.luana.videolocadora.repository.UsersRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VideolocadoraApplicationTests {
	
	@Autowired
	RentsRepository rentsRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@Autowired
	MoviesRepository moviesRepository;
	
	@Autowired
	public WebApplicationContext context;
		
	private MockMvc mvc;
	
	private static Integer createdMovieId;
		
	@Before
	public void setup() {
		SecurityContextHolder.getContext().setAuthentication(new PreAuthenticatedAuthenticationToken("newuser@gmail.com", "123456", Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"))));
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	

    @Test
    public void testARequisitionMoviesPost() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("http://localhost:8080/www.videolocadora.com/movies")
                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                                      .content("{\"title\": \"Filme\",\"director\": \"Diretor 1\",\"available\": true}")
                                      .characterEncoding("UTF-8");
                                      
        MvcResult result = this.mvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status().isOk())                    
                    .andDo(MockMvcResultHandlers.print()).andReturn();
        
        String responseBody = result.getResponse().getContentAsString();
        setCreatedMovieId(responseBody);
    }
   
    
    @Test
    public void testBRequisitionMoviesPut() throws Exception {
    	
        Integer id = createdMovieId;
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("http://localhost:8080/www.videolocadora.com/movies")
                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                                      .content("{\"id\":"+id+",\"title\": \"Novo Titulo\",\"director\": \"Diretor 1\",\"available\": true}")
                                      .characterEncoding("UTF-8");
                                      
        this.mvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status().isOk())  
                    .andExpect(MockMvcResultMatchers.content().json("{\"id\":"+id+",\"title\": \"Novo Titulo\",\"director\": \"Diretor 1\",\"available\": true}"))
                    .andDo(MockMvcResultHandlers.print());      
        
    }
	
	
	@Test
	public void testCRequisitionMoviesGet() throws Exception {
		
		MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("http://localhost:8080/www.videolocadora.com/movies");		
		
		this.mvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status()
                                        .isOk());        
	}
	
    @Test
    public void testJRequisitionMoviesDelete() throws Exception {

        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.delete("http://localhost:8080/www.videolocadora.com/movies/"+createdMovieId);
                                      
        this.mvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
	public void testDRequisitionUsersGet() throws Exception {
		
		MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("http://localhost:8080/www.videolocadora.com/users");
		
		
		this.mvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status()
                                        .isOk());	
        
	}
	
    @Test
    public void testERequisitionUsersPost() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("http://localhost:8080/www.videolocadora.com/users")
                                      .contentType(MediaType.APPLICATION_JSON_VALUE)
                                      .content("{\"username\":\"newuser@gmail.com\",\"name\":\"New User\",\"role\":\"USER\",\"password\":\"123456\"}")
                                      .characterEncoding("UTF-8");
                                      
        this.mvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }
   

    @Test
    public void testIRequisitionUsersDelete() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.delete("http://localhost:8080/www.videolocadora.com/users/newuser@gmail.com");
                                      
        this.mvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.print());
    }

 
	@Test
	public void testHRequisitionRentGet() throws Exception {		
		MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.get("http://localhost:8080/www.videolocadora.com/rents");		
		
		this.mvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status()
                                        .isOk());        
	}
	
	@Test
	public void testFRequisitionRentPost() throws Exception {		
		MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.post("http://localhost:8080/www.videolocadora.com/rents/"+createdMovieId);		
		
		 this.mvc.perform(builder)
        .andExpect(MockMvcResultMatchers.status()
                                        .isOk());    

	}
	
	@Test
    public void testGRequisitionRentPut() throws Exception {

        Integer id = createdMovieId;
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("http://localhost:8080/www.videolocadora.com/rents/"+createdMovieId);
                                      
        this.mvc.perform(builder)
                    .andExpect(MockMvcResultMatchers.status().isOk())                    
                    .andDo(MockMvcResultHandlers.print());      
        
        
        //removing fake data from database
        Optional<Movie> m = moviesRepository.findById(createdMovieId);
		Rent r = rentsRepository.findByMovie(m.get());
		rentsRepository.delete(r);
        
    }
	
	private void setCreatedMovieId(String jsonMovie) {
		Gson gson = new Gson(); 
		Movie m = gson.fromJson(jsonMovie, Movie.class);		
		this.createdMovieId = m.getId();
	}

}

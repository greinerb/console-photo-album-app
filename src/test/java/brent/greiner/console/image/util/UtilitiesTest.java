package brent.greiner.console.image.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilitiesTest {
	
	//Using Spy to only control what gets returned from this call and everything else executed as is
	@Spy
	RestTemplate restTemplate;
	
	@InjectMocks
	private Utilities utilities;
	
	@Mock
	RestTemplateBuilder builder;
	
	@Mock
	ObjectMapper mapper;
	
	private String validPhotoAlbumJson = "[{\r\n" + 
			"    \"albumId\": 1,\r\n" + 
			"    \"id\": 1,\r\n" + 
			"    \"title\": \"This is ablum 1 with id 1\",\r\n" + 
			"    \"url\": \"https://via.placeholder.com/600/111111\",\r\n" + 
			"    \"thumbnailUrl\": \"https://via.placeholder.com/150/111111\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"albumId\": 1,\r\n" + 
			"    \"id\": 2,\r\n" + 
			"    \"title\": \"This is ablum 1 with id 2\",\r\n" + 
			"    \"url\": \"https://via.placeholder.com/600/222222\",\r\n" + 
			"    \"thumbnailUrl\": \"https://via.placeholder.com/150/222222\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"albumId\": 2,\r\n" + 
			"    \"id\": 3,\r\n" + 
			"    \"title\": \"This is ablum 2 with id 3\",\r\n" + 
			"    \"url\": \"https://via.placeholder.com/600/333333\",\r\n" + 
			"    \"thumbnailUrl\": \"https://via.placeholder.com/150/333333\"\r\n" + 
			"  },\r\n" + 
			"  {\r\n" + 
			"    \"albumId\": 2,\r\n" + 
			"    \"id\": 4,\r\n" + 
			"    \"title\": \"This is ablum 2 with id 4\",\r\n" + 
			"    \"url\": \"https://via.placeholder.com/600/444444\",\r\n" + 
			"    \"thumbnailUrl\": \"https://via.placeholder.com/150/444444\"\r\n" + 
			"  }]";
	
	private String invalidPhotoAlbumJson = "";
	private String url = "https://jsonplaceholder.typicode.com/photos";
	
	@Before
	public void init_mocks() {
		MockitoAnnotations.initMocks(this);
		Mockito.doReturn(restTemplate).when(builder).build();
		//RestTemplateBuilder builder = Mockito.mock(RestTemplateBuilder.class);
		//restTemplate = Mockito.mock(RestTemplate.class);
		//utilities = Mockito.mock(Utilities.class);
	}
	
	
	@Test
	public void testValidLoadPhotoAlbums() throws Exception {
		Mockito.doReturn(validPhotoAlbumJson).when(restTemplate).getForObject(url, String.class);
		Mockito.when(mapper.readValue(validPhotoAlbumJson, PhotoAlbum[].class)).thenReturn(new ObjectMapper().readValue(validPhotoAlbumJson, PhotoAlbum[].class));
		Map<Integer, ArrayList<PhotoAlbum>> photoAlbumsMap = utilities.loadPhotoAlbums();
		assertEquals(2,photoAlbumsMap.size());
	}
	
	@Test
	public void testGeneratePhotoAlbumMap_isNotNull() throws Exception {
		Mockito.doReturn(validPhotoAlbumJson).when(restTemplate).getForObject(url, String.class);
		Mockito.when(mapper.readValue(validPhotoAlbumJson, PhotoAlbum[].class)).thenReturn(new ObjectMapper().readValue(validPhotoAlbumJson, PhotoAlbum[].class));
		try {
			Map<Integer, ArrayList<PhotoAlbum>> photoAlbumsMap = utilities.loadPhotoAlbums();
			assertTrue(null!=photoAlbumsMap);
		}
		catch(Exception e) {
			fail("Should have passed because it was valid json");
		}
	}
	
	@Test
	public void testGeneratePhotoAlbumMap_isNull() throws Exception {
		Mockito.doReturn(invalidPhotoAlbumJson).when(restTemplate).getForObject(url, String.class);
		Map<Integer, ArrayList<PhotoAlbum>> photoAlbumsMap = null;
		try {
	    	photoAlbumsMap = utilities.loadPhotoAlbums();
			fail("Should not have been a valid map returned");
		}
		catch(Exception e) {
			assertTrue(null==photoAlbumsMap);
		}
	}
}

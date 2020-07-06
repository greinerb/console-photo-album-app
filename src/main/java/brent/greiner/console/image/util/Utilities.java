package brent.greiner.console.image.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import brent.greiner.console.image.MyConsole;

@Component
public class Utilities {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	ObjectMapper mapper;
	
	private static Logger LOG = LoggerFactory.getLogger(Utilities.class);
    
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	   // Do any additional configuration here
	  return builder.build();
	}
	
	public Map<Integer, ArrayList<PhotoAlbum>> loadPhotoAlbums() throws Exception{
		String photoAlbumJson = restTemplate.getForObject("https://jsonplaceholder.typicode.com/photos", String.class);
		PhotoAlbum[] photoAlbumArray = mapJsonToPhotoAlbumArray(photoAlbumJson);
		return generatePhotoAlbumMap(photoAlbumArray);
	}
	
	private PhotoAlbum[] mapJsonToPhotoAlbumArray(String photoAlbumJson) throws Exception{
		return mapper.readValue(photoAlbumJson, PhotoAlbum[].class);
	}
	
	private Map<Integer, ArrayList<PhotoAlbum>> generatePhotoAlbumMap(PhotoAlbum[] photoAlbumArray) {
		Map<Integer, ArrayList<PhotoAlbum>> photoAlbums = new HashMap<Integer, ArrayList<PhotoAlbum>>();
		for(PhotoAlbum pa:photoAlbumArray) {
			Integer albumId = pa.getAlbumId();
			ArrayList<PhotoAlbum> singleAlbum = null;
			if(photoAlbums.containsKey(albumId)) {
				singleAlbum = photoAlbums.get(albumId);
				singleAlbum.add(pa);
			}
			else {
				singleAlbum = new ArrayList<PhotoAlbum>();
				singleAlbum.add(pa);
			}
			photoAlbums.put(albumId, singleAlbum);
		}
		return photoAlbums;
	}
		
}

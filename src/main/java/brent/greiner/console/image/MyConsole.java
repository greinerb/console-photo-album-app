package brent.greiner.console.image;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import brent.greiner.console.image.util.PhotoAlbum;
import brent.greiner.console.image.util.Utilities;

@SpringBootApplication
@ComponentScan(basePackages = "brent.greiner.console")
public class MyConsole implements CommandLineRunner {
 
    private static Logger LOG = LoggerFactory.getLogger(MyConsole.class);
    
    @Autowired
    Utilities utilities;
    
    private int minAlbumId = 0;
    private int maxAlbumId = 0;
    private String header = "*****************************************************************%n";
    private static final String NO_CONSOLE = "No console available.  Make sure you are running it correctly.\n\n";
    private static final String NO_CONNECTIVITY = "Not able to retrieve photo albums.  Make sure you have an internet connection\n\n";
    private static String format = "[%s] %s%n";
	
    public static void main(String[] args) {
        SpringApplication.run(MyConsole.class, args);
    }
    
    public static Console setupConsole() {
    	Console console = System.console();
        if (console == null) {
            System.out.print(NO_CONSOLE);
            System.exit(0);
        }
        return console;
    }
    @Override
    public void run(String... args) {
    	boolean running = true;
    	Console console = setupConsole();
    	clearScreen();
        Map<Integer, ArrayList<PhotoAlbum>> photoAlbumMap = null;
        try{
        	console.printf("Loading photo albums, please wait...");
            photoAlbumMap = utilities.loadPhotoAlbums();
        }
        catch(Exception e) {
        	LOG.error(NO_CONNECTIVITY);
        	System.exit(0);
        }
        
        Integer minKey = Collections.min(photoAlbumMap.keySet()); 
        Integer maxKey = Collections.max(photoAlbumMap.keySet()); 
        String albumSelection = console.readLine(generateFormatedStringForPrompt(minKey,maxKey));
        while(running) {
        	try {
        		Integer selection = Integer.parseInt(albumSelection);
        		if(selection>=minKey && selection<=maxKey) {
        			ArrayList<PhotoAlbum> photoAlbumList = photoAlbumMap.get(selection);
        			if(null!=photoAlbumList) {
        				console.printf(header);
        	            console.printf("        Album : "+photoAlbumList.get(0).getAlbumId()+"%n");
        	            console.printf(header);
        	            console.printf(format, "Id", "Title");
        	    		for(PhotoAlbum photoAlbum:photoAlbumList) {
        					console.printf(format, String.valueOf(photoAlbum.getId()), photoAlbum.getTitle());
            	        }
        	    		console.printf(generateFormatedStringForPrompt(minKey,maxKey));
        	    		albumSelection = console.readLine("Or enter q for quit... ");
        	    		if(albumSelection.equalsIgnoreCase("q")) {running=false;}
        			}
        			else {albumSelection = console.readLine(generateFormatedStringNotValidSelection(minKey, maxKey));}
        		}
        		else {albumSelection = console.readLine(generateFormatedStringNotValidSelection(minKey, maxKey));}
        	}
        	catch(Exception e) {
				albumSelection = console.readLine(generateFormatedStringNotValidSelection(minKey, maxKey));
        	}
        	clearScreen();
        }        
        System.exit(0);
    }
    
    private static void clearScreen() {  
        try{
        	final String os = System.getProperty("os.name");
            if (os.contains("Windows")){Runtime.getRuntime().exec("cls");}
            else{Runtime.getRuntime().exec("clear");}
        }
        catch (final Exception e){
        	System.out.println(e);
        }
    }
    
    private String generateFormatedStringForPrompt(Integer minKey, Integer maxKey) {
    	return "Please enter an album id from this range to display...  "+minKey+"-"+maxKey+"  ";
    }
    private String generateFormatedStringNotValidSelection(Integer minKey, Integer maxKey) {
    	return "Not a valid album selection between min("+minKey+") and max("+maxKey+"). Please try again.  ";
    }
}

package brent.greiner.console.image.util;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PhotoAlbumTest {
	
	private static PhotoAlbum photoAlbum;
	
	private static final Integer VALID_ALBUM_ID = 1;
	private static final Integer VALID_ID = 1;
	private static final String VALID_TITLE = "My Title";
	private static final String VALID_URL = "http://myurl.com";
	private static final String VALID_THUMBNAIL_URL = "http://mythumbnailurl.com";
	
	private static final Integer SET_VALID_ALBUM_ID = 2;
	private static final Integer SET_VALID_ID = 2;
	private static final String SET_VALID_TITLE = "Set My Title";
	private static final String SET_VALID_URL = "http://set.myurl.com";
	private static final String SET_VALID_THUMBNAIL_URL = "http://set.mythumbnailurl.com";
	
	@BeforeClass
	public static void setUp() throws Exception {
		photoAlbum = new PhotoAlbum();
		photoAlbum.setAlbumId(VALID_ALBUM_ID);
		photoAlbum.setId(VALID_ID);
		photoAlbum.setTitle(VALID_TITLE);
		photoAlbum.setUrl(VALID_URL);
		photoAlbum.setThumbnailUrl(VALID_THUMBNAIL_URL);
	}

	@Test
	public final void testGetAlbumId() {
		assertEquals("AlbumId should equal",(Integer)photoAlbum.getAlbumId(),VALID_ALBUM_ID);
	}

	@Test
	public final void testSetAlbumId() {
		photoAlbum.setAlbumId(SET_VALID_ALBUM_ID);
		assertEquals("AlbumId should equal after set",(Integer)photoAlbum.getAlbumId(),SET_VALID_ALBUM_ID);
	}

	@Test
	public final void testGetId() {
		assertEquals("Id should equal",(Integer)photoAlbum.getId(),VALID_ID);
	}

	@Test
	public final void testSetId() {
		photoAlbum.setId(SET_VALID_ID);
		assertEquals("Id should equal after set",(Integer)photoAlbum.getId(),SET_VALID_ID);
	}

	@Test
	public final void testGetTitle() {
		assertEquals("Title should equal",photoAlbum.getTitle(),VALID_TITLE);
	}

	@Test
	public final void testSetTitle() {
		photoAlbum.setTitle(SET_VALID_TITLE);
		assertEquals("Title should equal after set",photoAlbum.getTitle(),SET_VALID_TITLE);
	}

	@Test
	public final void testGetUrl() {
		assertEquals("URL should equal",photoAlbum.getUrl(),VALID_URL);
	}

	@Test
	public final void testSetUrl() {
		photoAlbum.setUrl(SET_VALID_URL);
		assertEquals("URL should equal after set",photoAlbum.getUrl(),SET_VALID_URL);
	}

	@Test
	public final void testGetThumbnailUrl() {
		assertEquals("Thumbnail URL should equal",photoAlbum.getThumbnailUrl(),VALID_THUMBNAIL_URL);
	}

	@Test
	public final void testSetThumbnailUrl() {
		photoAlbum.setThumbnailUrl(SET_VALID_THUMBNAIL_URL);
		assertEquals("Thumbnail URL should equal after set",photoAlbum.getThumbnailUrl(),SET_VALID_THUMBNAIL_URL);
	}

}

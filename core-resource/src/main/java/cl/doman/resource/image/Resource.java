package cl.doman.resource.image;

import java.io.File;
import java.io.InputStream;

public interface Resource {
	File getFile(String id);
	InputStream getInputStream(String id);
}

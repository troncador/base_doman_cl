package cl.doman.core.email;

import java.io.File;
import java.util.Set;
import java.util.Map;

/**
 * 
 * @author troncador
 *
 */
public interface MailComposer {
	String getSubject();
	String getMessage();
	Set<File> getAttachedFile();
	Map<String,File> getImages();
}

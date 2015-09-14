package cl.doman.core.email;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class MailComposerSimple implements MailComposer{
	private String subject;
	private String message;
	private Set<File> attachedFile;
	private Map<String, File> images;

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public Set<File> getAttachedFile() {
		return attachedFile;
	}

	public Map<String, File> getImages() {
		return images;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setAttachedFile(Set<File> attachedFile) {
		this.attachedFile = attachedFile;
	}

	public void setImages(Map<String, File> images) {
		this.images = images;
	}

}

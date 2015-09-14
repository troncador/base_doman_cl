package cl.doman.springmvc.upload;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilteItemIterable  implements Iterable<FileItemStream>{
	static Logger log =LoggerFactory.getLogger(FilteItemIterable.class);
	private ServletFileUpload upload;
	private HttpServletRequest request;



	public FilteItemIterable(ServletFileUpload upload,HttpServletRequest request) {
		this.upload = upload;
		this.request = request;
	}
		
	public Iterator<FileItemStream> iterator() {
		try {
			return new FileItemIteratorWrap(upload, request);
		} catch (FileUploadException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		return null;
	}

}

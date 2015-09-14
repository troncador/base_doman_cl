package cl.doman.springmvc.upload;

import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FileItemIteratorWrap implements Iterator<FileItemStream>{
	static Logger log =LoggerFactory.getLogger(Iterator.class);
	FileItemIterator iter;
	
	public FileItemIteratorWrap(ServletFileUpload upload,HttpServletRequest request) throws FileUploadException, IOException{
		iter = upload.getItemIterator(request);
	}
	
	
	public boolean hasNext() {
		try {
			return iter.hasNext();
		} catch (FileUploadException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		return false;
	}

	
	public FileItemStream next() {
		try {
			return iter.next();
		} catch (FileUploadException e) {
			log.error(e.getMessage(),e);
		} catch (IOException e) {
			log.error(e.getMessage(),e);
		}
		throw new NoSuchElementException();
	}

	
	public void remove() {
		throw new UnsupportedOperationException();
	}

}

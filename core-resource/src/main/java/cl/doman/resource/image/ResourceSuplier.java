package cl.doman.resource.image;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResourceSuplier {
	private static final transient Logger log =LoggerFactory.getLogger(ResourceSuplier.class);
	static Pattern pattern = Pattern.compile("\\w+");
	
	private static final String imageDirectory = "/var/www/images/%s";
	private static final String defaultImageFile = "default.png";
	private static InputStream defaultImage = null;
	private String directory;
	private String extension;
	
	public ResourceSuplier(String directory,String extension){
		Validate.matchesPattern(extension,"\\w+");
		this.directory = directory;
		this.extension = extension;
		File folder = new File(directory);
		if(!folder.isDirectory()){
			throw new IllegalArgumentException("directory is not a directory");
		}
	}
	
	public InputStream getInputStream(String id){
		Validate.matchesPattern(id,"\\w+");
		String a = FilenameUtils.concat(directory,FilenameUtils.EXTENSION_SEPARATOR+id);
		
		File folder = new File(a);
		if(folder.exists() && folder.isFile()){
			log.info("tt");
		}
		return null;
	}
	public Set<String> getids(){
		File folder = new File(directory);
		ArrayList<String> extensionList = new ArrayList<String>();
		extensionList.add(extension);
		FilenameFilter filenameFilter = new SuffixFileFilter(extensionList,IOCase.INSENSITIVE);
		
		String[] files = folder.list(filenameFilter);
		Set<String> sfd = new HashSet<String>();
				
		for(String a: files){
			String d = FilenameUtils.removeExtension(a);
			sfd.add(d);
		}
		return sfd;
	}
}

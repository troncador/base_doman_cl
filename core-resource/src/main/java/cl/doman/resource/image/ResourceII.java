package cl.doman.resource.image;

import java.io.File;
import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.lang3.CharSetUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class ResourceII implements Resource{
	static Pattern pattern = Pattern.compile("\\w+");
	
	public ResourceII(String file){
		
		 File dir = new File(".");
		 String[] files = dir.list( DirectoryFileFilter.INSTANCE );
		 for ( int i = 0; i < files.length; i++ ) {
		     System.out.println(files[i]);
		 }
	}
	
	public File getFile(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public InputStream getInputStream(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validId(String id){
		Matcher matcher = pattern.matcher(id);
		return matcher.matches();
	}
	
}

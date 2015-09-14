package cl.doman.base.initializator;

import java.io.File;

public abstract class BaseSystemInitializator implements SystemInitializator{
	protected File getFile(String filepath) throws SystemInitializatorException{
		if ( filepath == null) {
			String str = "filepath: null";
			throw new SystemInitializatorException(str);
		}
		File externalConfigFile = new File(filepath);
		
		if (!externalConfigFile.exists()) {
			String str = "file: not exist";
			throw new SystemInitializatorException(str);
		} 
		if (!externalConfigFile.isFile()) {
			String str = "file: is not a file";
			throw new SystemInitializatorException(str);
		} 
		if (!externalConfigFile.canRead()) {
			String str = "file: can't be read";
			throw new SystemInitializatorException(str);			
		}
		return externalConfigFile;
	}
}

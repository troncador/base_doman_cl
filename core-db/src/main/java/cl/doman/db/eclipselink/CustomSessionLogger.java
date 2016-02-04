package cl.doman.db.eclipselink;

import org.eclipse.persistence.logging.AbstractSessionLog;
import org.eclipse.persistence.logging.SessionLog;
import org.eclipse.persistence.logging.SessionLogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomSessionLogger extends AbstractSessionLog implements SessionLog {
	public static final Logger log = LoggerFactory.getLogger(CustomSessionLogger.class);
	public static final Logger logSevere = LoggerFactory.getLogger("sql.severe");
	public static final Logger logWarning = LoggerFactory.getLogger("sql.warning");
	public static final Logger logInfo = LoggerFactory.getLogger("sql.info");
	public static final Logger logConfig = LoggerFactory.getLogger("sql.config");
	public static final Logger logFiner = LoggerFactory.getLogger("sql.finer");
	public static final Logger logFine = LoggerFactory.getLogger("sql.fine");
	public static final Logger logFinest = LoggerFactory.getLogger("sql.finest");
	private boolean on = true; 
	public void log(SessionLogEntry sessionLogEntry) {
		if(on){
			int level = sessionLogEntry.getLevel();
			String message = sessionLogEntry.getMessage();
			switch (level) {
				case SEVERE:
					logSevere.debug(message);
					break;
				case WARNING:
					logWarning.debug(message);
					break;
				case INFO:
					logInfo.debug(message);
					break;
				case FINE:
					//sql
					logFine.debug(message);
					break;
				case CONFIG:
					logConfig.debug(message);
					break;
				case FINER:
					logFiner.debug(message);
					break;
				case FINEST:
					logFinest.debug(message);
					break;
				default:
					log.debug(message);
				}
		}
	}
}


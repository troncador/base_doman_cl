package cl.doman.resource.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public enum MessageDigestAlgorithm {
	MD2("MD2"), MD5("MD5"), 
	SHA1("SHA-1"), SHA256("SHA-256"), SHA384("SHA-384"),SHA512("SHA-512");
	
	static Logger log =LoggerFactory.getLogger(MessageDigestAlgorithm.class);
	/**
	 * Algorithm name as defined in {@link MessageDigest#getInstance(String)}
	 */
	private final String algorithm;

	private MessageDigestAlgorithm(final String algorithm) {
		this.algorithm = algorithm;
	}

	public MessageDigest getMessageDigest() {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (final NoSuchAlgorithmException e) {
			log.error(e.getMessage(),e);
			throw new IllegalArgumentException(e.getMessage(),e);
		}
	}

	public String toString() {
		return this.algorithm;
	}
}

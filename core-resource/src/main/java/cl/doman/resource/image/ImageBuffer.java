package cl.doman.resource.image;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ImageBuffer extends InputStream {
	private static final transient Logger log =LoggerFactory.getLogger(ImageBuffer.class);
	private final InputStream decorated;

	public ImageBuffer(InputStream inputStream) {
		if (!inputStream.markSupported()) {
			throw new IllegalArgumentException("marking not supported");
		}
		inputStream.mark( 1 << 24); // magic constant: BEWARE
		decorated = inputStream;
	}

	@Override
	public void close() throws IOException {
		decorated.reset();
	}

	@Override
	public int read() throws IOException {
		return decorated.read();
	}

}

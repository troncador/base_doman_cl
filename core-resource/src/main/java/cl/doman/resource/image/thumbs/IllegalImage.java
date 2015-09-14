package cl.doman.resource.image.thumbs;

public class IllegalImage extends Exception {
	private static final long serialVersionUID = 1l;

	public IllegalImage(String msg,Throwable e){
		super(msg, e);
	}
	public IllegalImage(Throwable e){
		super( e);

	}
	public IllegalImage(String msg){
		super(msg);
	}
}

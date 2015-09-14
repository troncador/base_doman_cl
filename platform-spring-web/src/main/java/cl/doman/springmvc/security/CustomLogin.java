package cl.doman.springmvc.security;


public interface CustomLogin<USER> {
	USER getUser();
	boolean check();
}

package cl.doman.base.cache;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;


//	http://java.dzone.com/articles/caching-best-practices
public class Example {
	static Logger log = LoggerFactory.getLogger(Example.class);
	private LoadingCache<Integer, Integer> fibonacciCache = CacheBuilder.newBuilder()
	        .maximumSize(2)
	        .build(new CacheLoader<Integer, Integer>() {
	            public Integer load(Integer i) throws Exception {
	                if (i == 0){
	                    return i;
	                }
	                if (i == 1){
	                    return 1;
	                }
	                log.info("Calculating f(" + i + ")");
	                return fibonacciCache.getUnchecked(i - 2) + fibonacciCache.getUnchecked(i - 1);
	            }
	        });
	
	static public void main(String[] arg) throws ExecutionException{
		Example a = new Example();
		for (int i = 0; i < 10; i++) {
			Integer in = a.fibonacciCache.getUnchecked(i);
			log.info(String.format("f(%d)=%d",i,in));
		}
		
	}
}

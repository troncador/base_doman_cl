package cl.doman.db.query.dao;

public class PrepareLimitPagination implements PrepareLimit{
	private int pageSize;
	private int pageNumber;
	
	public PrepareLimitPagination(int pageSize){
		this.pageSize = pageSize;
	}
	
	public PrepareLimitPagination(Integer pageSize, Integer pageNumber){
		this.pageSize = pageSize;
		this.pageNumber = ((pageNumber==null)?0:pageNumber);
	}
	
	
	public void setPageNumber(int pageNumber){
		this.pageNumber = pageNumber;
	}
	
	public Integer getMaxResults(){
		return pageSize;
	}
	public Integer getFirstResult(){
		return pageSize*pageNumber;
		
	}
}

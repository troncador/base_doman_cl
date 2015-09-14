package cl.doman.spring.beanpopulator;

public class BeanPopulatorDataTable extends BeanPopulator{
	@Override
	public String[] splitPath(String Path) {
		String newkey = Path.replace("[", ".").replace("]", "");
		return newkey.split("[.]");
	}
}

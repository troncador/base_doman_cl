package cl.doman.spring.beanpopulator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.base.initializator.LoggerInitializator;
import cl.doman.springmvc.datatable.Table;
import cl.doman.springmvc.datatable.Order.Dir;


public class BeanPopulatorTest {
//	static Logger log = LoggerFactory.getLogger(BeanPopulatorTest.class);

//	public BeanPopulatorTest() throws IOException{
//		new LoggerInitializator();
//	}
//	
//	
//	BeanPopulator beanPopulator = new BeanPopulatorDataTable();
//	Table table = new Table();
//	
//	@Test
//	public void test1() throws BeanPopulatorException {
//		String value = "id";
//		beanPopulator.populate("id", table, value);
//		Assert.assertEquals("no se seteo", value, table.getId());
//
//	}
//
//	@Test
//	public void test2() throws BeanPopulatorException {
//		String value = "regex";
//		beanPopulator.populate("search[regex]", table, value);
//		Assert.assertEquals("no se seteo", value, table.getSearch().getRegex());
//
//	}
//
//	@Test
//	public void test3() throws BeanPopulatorException {
//		String value = "name";
//		beanPopulator.populate("columns[3][name]", table, value);
//		Assert.assertEquals("no se seteo", value,
//				table.getColumns()[3].getName());
//
//	}
//
//	@Test
//	public void test4() throws BeanPopulatorException {
//		Boolean value = true;
//		beanPopulator.populate("columns[3][searchable]", table,
//				value.toString());
//		Assert.assertEquals("no se seteo", value,
//				table.getColumns()[3].isSearchable());
//
//	}
//	
//	@Test
//	public void test5() throws BeanPopulatorException {
//		Dir value = Dir.asc;
//		beanPopulator.populate("order[3][dir]", table, value.toString());
//		Assert.assertEquals("no se seteo", value, table.getOrder()[3].getDir());
//	}
//	
//	
//	@Test
//	public void test6() throws BeanPopulatorException {
//		Dir value = Dir.asc;
//		beanPopulator.populate("order[5][dir]", table, value.toString());
//		Assert.assertEquals("no se seteo", 5+1, table.getOrder().length);
//		beanPopulator.populate("order[6][dir]", table, value.toString());
//		Assert.assertEquals("no se seteo", 6+1, table.getOrder().length);
//		
//		Assert.assertEquals("no se seteo", value, table.getOrder()[5].getDir());
//	}
//	
//	@Test
//	public void test7() throws BeanPopulatorException, IOException {
//		Properties prop = new Properties();
//		
//		
//		Package currentPackage = this.getClass().getPackage();
//		String packageJavaRepresentation = currentPackage.getName();
//		String packagePath = packageJavaRepresentation.replace(".", "/");
//		String filePath = String.format("/%s/%s",packagePath, "config.properties");
//		InputStream inputstream = this.getClass().getResourceAsStream(filePath);
//		prop.load(inputstream);
//		
//		
//		for(Object key : prop.keySet()){
//			Object value = prop.get(key);
//			beanPopulator.populate((String) key, table,(String) value);
//		}
//		
//	}
	
}
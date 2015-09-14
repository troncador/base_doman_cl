package cl.doman.spring.beanpopulator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Esta clase puebla los objetos anidados:
 * 		& 	todos los objetos tienen que tener setter y getter
 * 		&	no pueden haber list, ni map, sólo arreglos
 * 		&	hace converciones automáticas
 * 
 * ejemplo:
 * 
 * populate("search.orden.2.regex",new Table(),"[0-9]*");
 * populate("lenght",new Table(),"1");
 * 
 * 
 * @author troncador
 *
 */
public abstract class BeanPopulator {
	static Logger log = LoggerFactory.getLogger(BeanPopulator.class);
	private ConvertUtilsBean  convertUtilsBean = new FieldConvertUtilsBean();

	/**
	 * path	- javabean
	 * el javabean actual y hacia donde va
	 */
	private List<Pair<String,Object>> pathList;
	private int maxArray;
	private String path;
	
	/**
	 * TODO: número maximo de elementos de un arreglo que se pueden crear
	 * incorporar el parser de texto que separa por "." 
	 */
	public BeanPopulator(int maxArray){
		this.maxArray = maxArray;
	}
	
	public BeanPopulator(){
		this(15);
	}
	
	public abstract String[] splitPath(String Path);
	
	public void register(Converter converter, Class<?> clazz){
		convertUtilsBean.register(converter, clazz);
	}
	
	private void run(String value) throws BeanPopulatorException{
		for(int i=0;i< pathList.size()-1;i++){
			
			String path = pathList.get(i).getLeft();
			Object object = pathList.get(i).getRight();
			Object newObject = null;
			
			if(path.matches("\\d+")){
				int index = Integer.parseInt(path);
				if(index<0){
					throw new BeanPopulatorException(path,"array index negative");
				}
				newObject = isNumber(index, object, pathList.get(i-1));
			} else {
				newObject = makeStep(path,  object);
			}
			pathList.get(i+1).setValue(newObject);
		}
		//obtiene el último objeto almacenado y el último path, para poder setearlo
		
		Pair<String, Object> lastPath = pathList.get(pathList.size()-1);
		String path = lastPath.getLeft();
		Object object = lastPath.getRight();
		
		setValue(path,object,value);
	}
	
	private Object makeStep(String path, Object object) throws BeanPopulatorException{
		try{
			Object sonObject = PropertyUtils.getProperty(object, path);
			return sonObject;
		} catch (Exception e){
			throw new BeanPopulatorException(path,e.getMessage(),e);
		}
	}
	
	
	public void populate(String path, Object bean, String value) throws BeanPopulatorException{
		this.path = path;
		String[] pathStep = this.splitPath(path);
		
		this.pathList =  new ArrayList<Pair<String,Object>>(pathStep.length);
		for(String step : pathStep){
			Pair<String,Object> pair = new MutablePair<String,Object>(step,null);
			this.pathList.add( pair);
		}
		this.pathList.get(0).setValue(bean);
		run(value);
	}
	
	

	
	private void setValue(String path, Object currentObject,String value) throws BeanPopulatorException{
		PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
		if( propertyUtilsBean.isWriteable(currentObject,path) ){
			try{
				Class<?> classzz = propertyUtilsBean.getPropertyType(currentObject,path);
				Object newValue = convertUtilsBean.convert(value, classzz);
				PropertyUtils.setProperty(currentObject, path , newValue);
			} catch (Exception e) {
				throw new BeanPopulatorException(path,e.getMessage(),e);
			}
		} else{
			throw new BeanPopulatorException(path,"Value Is not Writable");
		}
	}
	
	
	private Object isNumber(int index, Object object, Pair<String, Object> fatherpair) throws BeanPopulatorException{
		try{
			Class<?> currentObjectClass = object.getClass();
			if (currentObjectClass.isArray()) {
				Object[] objectArray = (Object[]) object;
				int currentSize = objectArray.length;
				Class<?> elementClass = objectArray.getClass().getComponentType();
				if(index+1 >currentSize){
					if(index+1>maxArray){
						throw new BeanPopulatorException(path,"array in root");
					}
					Object[] newArray = Arrays.copyOf(objectArray,index+1);
					for(int i = currentSize; i <= index; i++){
						newArray[i] = elementClass.newInstance();
					}
					objectArray = newArray;
					Object fatherObject =  fatherpair.getRight();
					String path = fatherpair.getLeft();
					PropertyUtils.setProperty(fatherObject, path ,newArray);
				}
				return objectArray[index];
			} else {
				throw new BeanPopulatorException(path,"is not a array");
			}
		} catch (Exception e) {
			throw new BeanPopulatorException(path,e.getMessage(),e);
		
		}
	}
}
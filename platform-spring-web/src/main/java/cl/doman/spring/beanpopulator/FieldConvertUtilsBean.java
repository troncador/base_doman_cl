package cl.doman.spring.beanpopulator;

import org.apache.commons.beanutils.ConvertUtilsBean;

public class FieldConvertUtilsBean extends ConvertUtilsBean{
    @Override
    public Object convert(String value, Class clazz) {
          if (clazz.isEnum()){
               return Enum.valueOf(clazz, value);
          }else{
               return super.convert(value, clazz);
          }
   }
}

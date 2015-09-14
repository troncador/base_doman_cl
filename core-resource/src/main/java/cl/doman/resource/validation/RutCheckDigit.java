package cl.doman.resource.validation;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.ModulusCheckDigit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RutCheckDigit extends ModulusCheckDigit{
	private static final long serialVersionUID = 1L;
	static Logger log =LoggerFactory.getLogger(RutCheckDigit.class);
	static public RutCheckDigit INSTANCE = new RutCheckDigit();
	
	public RutCheckDigit() {
		super(11);
	}

	@Override
	protected String toCheckDigit(int charValue){
		return (charValue==10)?"k":new Integer(charValue).toString();
	}
	
	@Override
	protected int weightedValue(int charValue, int leftPos, int rightPos) throws CheckDigitException {
		return charValue*((rightPos-2)%6+2);
	}
}

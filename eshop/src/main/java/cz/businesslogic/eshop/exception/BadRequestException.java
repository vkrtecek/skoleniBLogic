package cz.businesslogic.eshop.exception;

public class BadRequestException extends RuntimeException {
	
	private static final long serialVersionUID = 1;
	
	public BadRequestException(String str) {
		super(str);
	}

}

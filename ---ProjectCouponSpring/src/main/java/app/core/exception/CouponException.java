package app.core.exception;

public class CouponException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public CouponException() {
		super();
	}

	public CouponException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public CouponException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public CouponException(String arg0) {
		super(arg0);
	}

	public CouponException(Throwable arg0) {
		super(arg0);
	}

}

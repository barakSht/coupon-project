package app.core.controllers;

import app.core.exception.CouponException;

public abstract class ClientController {
	public abstract String login(String email , String password)throws CouponException;

}

/**
 * 
 */
package com.hsbc.team4.ordersystem.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author zhailiang
 *
 */
public class UserNotExistException extends UsernameNotFoundException {
	private static final long serialVersionUID = -6112780192479692859L;
	
	private String id;

	/**
	 * the user notfount
	 * @param id
	 */
	public UserNotExistException(String id) {
		super("user not exist");
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}

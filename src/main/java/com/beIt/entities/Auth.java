/**
 * 
 */
package com.beIt.entities;

/**
 * @author Amadou Ali Ousseini
 *
 */
public class Auth{
	
	private String login;
	
	private String password;
	
	private String authUrl;

	/**
	 * 
	 */
	public Auth() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param login
	 * @param password
	 */
	public Auth(String login, String password, String authUrl) {
		super();
		this.login = login;
		this.password = password;
		this.authUrl = authUrl;
	}
	
	/**
	 * 
	 * @param login
	 * @param password
	 * @param authUrl
	 */
	public Auth(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the authUrl
	 */
	public String getAuthUrl() {
		return authUrl;
	}

	/**
	 * @param authUrl the authUrl to set
	 */
	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}
	
	
	
	
}

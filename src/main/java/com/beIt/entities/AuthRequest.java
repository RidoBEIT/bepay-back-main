/**
 * 
 */
package com.beIt.entities;

/**
 * @author Amadou Ali Ousseini
 *
 */
//$2a$10$s43u5Aoz06D8pEotX58tA.UETqJ/kC1nKze7ULdkQUJCidCcFGzK2
//$2a$10$7SjNeSkSk58aOAncIbttGerexe96UEr77k6yGGkmyJWhcCAjj/43S
public class AuthRequest {
	
	private String login;
	
	private String password;


	public AuthRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param login
	 * @param password
	 */
	public AuthRequest(String login, String password) {
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
	
	

}


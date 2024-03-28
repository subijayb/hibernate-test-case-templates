package org.hibernate.bugs;


public class UserValue {
	
	protected String uservalue = null;  // plaintext to be persisted into a secure Vault storage
	protected String uservalue_clob = null;
	public String getUservalue() {
		return uservalue;
	}
	public void setUservalue(String uservalue) {
		this.uservalue = uservalue;
	}
	public String getUservalue_clob() {
		return uservalue_clob;
	}
	public void setUservalue_clob(String uservalue_clob) {
		this.uservalue_clob = uservalue_clob;
	}
	public UserValue(String uservalue, String uservalue_clob) {
		//super();
		this.uservalue = uservalue;
		this.uservalue_clob = uservalue_clob;
	}
	public UserValue() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	//prot
	

}

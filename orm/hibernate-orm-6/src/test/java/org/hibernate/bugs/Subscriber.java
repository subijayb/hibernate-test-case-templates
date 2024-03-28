package org.hibernate.bugs;

import java.util.Date;

public class Subscriber {

	private Long id;
	private Long tenantid;
	private Date creationDate;
	private String accountNumber;
	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenantid() {
		return tenantid;
	}

	public void setTenantid(Long tenantid) {
		this.tenantid = tenantid;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}

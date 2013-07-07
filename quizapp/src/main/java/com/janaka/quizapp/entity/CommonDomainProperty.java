package com.janaka.quizapp.entity;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

@Component
@Embeddable
public class CommonDomainProperty implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Date creationDate;
	private Date lastModifiedDate;
	private SystemUser lastModifiedUser;
	private SystemUser createdUser;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@OneToOne()
	@JoinColumn(name="LAST_MODIFIED_USER")
	public SystemUser getLastModifiedUser() {
		return lastModifiedUser;
	}
	public void setLastModifiedUser(SystemUser lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
	
	@OneToOne()
	@JoinColumn(name="CREATED_USER")
	public SystemUser getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(SystemUser createdUser) {
		this.createdUser = createdUser;
	}	
	
	
		
	
		
	
}

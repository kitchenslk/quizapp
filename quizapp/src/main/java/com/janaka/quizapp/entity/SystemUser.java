package com.janaka.quizapp.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.janaka.quizapp.enums.ActiveStatus;
import com.janaka.quizapp.enums.AuthenticationType;

@Entity(name = "systemUser")
@Table(name = "SYSTEM_USER", uniqueConstraints =@UniqueConstraint(columnNames = "USER_NAME"))
public class SystemUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String userName;
    private String userDisplayName;
    private String password;
    private String firstName;
    private String emailAddress;
    private String lastName;
    private String authenticationId;
    private UploadedFile profileImage;
    private AuthenticationType authenticationType;
    private Set<UserRole> userRoles;
    private ActiveStatus activeStatus; 
    private int versionId;    
    private CommonDomainProperty commonDomainProperty;
  
    
    @Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.SystemUserIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "USER_ID", length = 12)
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

    @Column(name = "USER_NAME")
    public String getUserName() {
        return userName;
    }    
	public void setUserName(String userName) {
        this.userName = userName;
    }
	
	
	@Column(name = "EMAIL_ADDRESS")
    public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	@Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "AUTHENTICATION_ID")
	public String getAuthenticationId() {
		return authenticationId;
	}
	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PROFILE_IMAGE_ID")
	@Cascade(CascadeType.ALL)
	public UploadedFile getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(UploadedFile profileImage) {
		this.profileImage = profileImage;
	}
	
	@Column(name = "USER_DISPLAY_NAME")
	public String getUserDisplayName() {
		return userDisplayName;
	}
	public void setUserDisplayName(String userDisplayName) {
		this.userDisplayName = userDisplayName;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "AUTHENTICATION_TYPE")
	public AuthenticationType getAuthenticationType() {
		return authenticationType;
	}
	public void setAuthenticationType(AuthenticationType authenticationType) {
		this.authenticationType = authenticationType;
	}
	
		
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SYSTEM_USER_ROLES", joinColumns =
    @JoinColumn(name = "USER_ID"), inverseJoinColumns =
    @JoinColumn(name = "USER_ROLE_ID"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }    
	public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
	
	
	@Enumerated(EnumType.STRING)
    @Column(name="ACCOUNT_STATUS")
	public ActiveStatus getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(ActiveStatus activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Version
    @Column(name = "VERSION_ID")
    public int getVersionId() {
        return versionId;
    }   
	public void setVersionId(int versionId) {
        this.versionId = versionId;
    }       
    
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "creationDate", column =
        @Column(name = "CREATION_DATE")),
        @AttributeOverride(name = "createdUser", column =
        @Column()),
        @AttributeOverride(name = "lastModifiedUser", column =
        @Column()),
        @AttributeOverride(name = "lastModifiedDate", column =
        @Column(name = "LAST_MODIFIED_DATE"))
    })
    public CommonDomainProperty getCommonDomainProperty() {
        return commonDomainProperty;
    }

    public void setCommonDomainProperty(CommonDomainProperty commonDomainProperty) {
        this.commonDomainProperty = commonDomainProperty;
    }

    @Override
   	public int hashCode() {
   		HashCodeBuilder builder = new HashCodeBuilder();
   		builder.append(this.userId);
   		return builder.toHashCode();
   	}

   	@Override
   	public boolean equals(Object obj) {
   		if (obj instanceof SystemUser) {
   			SystemUser other = (SystemUser) obj;
   			EqualsBuilder builder = new EqualsBuilder();
   			builder.append(this.userId, other.userId);
   			return builder.isEquals();
   		}
   		return false;
   	}
    
}

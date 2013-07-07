package com.janaka.quizapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

import com.janaka.quizapp.enums.UserRoleType;

@Entity(name = "userRole")
@Table(name = "USER_ROLE")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userRoleId;
    private UserRoleType userRoleType;
    private int versionId;

    @Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.UserRoleIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "USER_ROLE_ID", length = 12)
    public String getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(String userRoleId) {
		this.userRoleId = userRoleId;
	}

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE_TYPE")
    public UserRoleType getUserRoleType() {
        return userRoleType;
    }    
	public void setUserRoleType(UserRoleType userRoleType) {
        this.userRoleType = userRoleType;
    }

    @Version
    @Column(name = "VERSION_ID")
    public int getVersionId() {
        return versionId;
    }
    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }
}

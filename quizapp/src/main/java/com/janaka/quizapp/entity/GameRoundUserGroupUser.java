package com.janaka.quizapp.entity;

import java.io.Serializable;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.janaka.quizapp.enums.ActiveStatus;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 7, 2013 - 1:48:32 PM
 * Project	: quizapp
 */
@Entity(name = "gameRoundUserGroupUser")
@Table(name = "GAME_ROUND_USER_GROUP_USER")
public class GameRoundUserGroupUser implements Serializable {

	private static final long serialVersionUID = 1L;
	private String gameRoundUserGroupUserId;
	private int gameRoundUserGroupUserSeqNo;
	private boolean groupOwner;
	private boolean allowedToAnswer;
	private boolean acceptedStatus;
	private SystemUser systemUser;
	private GameRoundUserGroup gameRoundUserGroup;
	private	ActiveStatus activeStatus;	
    private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
		
	@Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.GameRoundUserGroupUserIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "GAME_ROUND_USER_GROUP_USER_ID", length = 12)
	public String getGameRoundUserGroupUserId() {
		return gameRoundUserGroupUserId;
	}
	public void setGameRoundUserGroupUserId(String gameRoundUserGroupUserId) {
		this.gameRoundUserGroupUserId = gameRoundUserGroupUserId;
	}
	
	@Column(name = "GAME_ROUND_USER_GROUP_USER_SEQ_NO")
	public int getGameRoundUserGroupUserSeqNo() {
		return gameRoundUserGroupUserSeqNo;
	}
	public void setGameRoundUserGroupUserSeqNo(int gameRoundUserGroupUserSeqNo) {
		this.gameRoundUserGroupUserSeqNo = gameRoundUserGroupUserSeqNo;
	}
	
	@Column(name = "GROUP_OWNER")
	public boolean isGroupOwner() {
		return groupOwner;
	}
	public void setGroupOwner(boolean groupOwner) {
		this.groupOwner = groupOwner;
	}
	
	@Column(name = "ALLOWED_TO_ANSWER")
	public boolean isAllowedToAnswer() {
		return allowedToAnswer;
	}	
	public void setAllowedToAnswer(boolean allowedToAnswer) {
		this.allowedToAnswer = allowedToAnswer;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="SYSTEM_USER_ID")
	@Cascade(CascadeType.MERGE)
	public SystemUser getSystemUser() {
		return systemUser;
	}
	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="GAME_ROUND_USER_GROUP_ID")
	@Cascade(CascadeType.MERGE)
	public GameRoundUserGroup getGameRoundUserGroup() {
		return gameRoundUserGroup;
	}	
	public void setGameRoundUserGroup(GameRoundUserGroup gameRoundUserGroup) {
		this.gameRoundUserGroup = gameRoundUserGroup;
	}
	
	@Column(name = "ACCEPTED_STATUS")
	public boolean isAcceptedStatus() {
		return acceptedStatus;
	}
	public void setAcceptedStatus(boolean acceptedStatus) {
		this.acceptedStatus = acceptedStatus;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ACTIVE_STATUS")
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
		builder.append(this.gameRoundUserGroupUserId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GameRoundUserGroupUser) {
			GameRoundUserGroupUser other = (GameRoundUserGroupUser) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.gameRoundUserGroupUserId, other.gameRoundUserGroupUserId);
			return builder.isEquals();
		}
		return false;
	}
	

}

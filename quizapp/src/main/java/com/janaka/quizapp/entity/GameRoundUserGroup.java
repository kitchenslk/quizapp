package com.janaka.quizapp.entity;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.OneToMany;
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
 * Date/Time: Jul 7, 2013 - 1:46:39 PM
 * Project	: quizapp
 */
@Entity(name = "gameRoundUserGroup")
@Table(name = "GAME_ROUND_USER_GROUP")
public class GameRoundUserGroup implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String gameRoundUserGroupId;
	private String gameRoundUserGroupName;
	private GameRound gameRound;
	private List<GameRoundUserGroupUser> gameRoundUserGroupUsers;
	private	ActiveStatus activeStatus;	
    private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
	
	@Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.GameRoundUserGroupIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "GAME_ROUND_USER_GROUP_ID", length = 12)
	public String getGameRoundUserGroupId() {
		return gameRoundUserGroupId;
	}
	public void setGameRoundUserGroupId(String gameRoundUserGroupId) {
		this.gameRoundUserGroupId = gameRoundUserGroupId;
	}
	
	@Column(name="GAME_ROUND_USER_GROUP_NAME")
	public String getGameRoundUserGroupName() {
		return gameRoundUserGroupName;
	}
	public void setGameRoundUserGroupName(String gameRoundUserGroupName) {
		this.gameRoundUserGroupName = gameRoundUserGroupName;
	}
	
	
	@OneToMany(mappedBy="gameRoundUserGroup")
	public List<GameRoundUserGroupUser> getGameRoundUserGroupUsers() {
		return gameRoundUserGroupUsers;
	}
	public void setGameRoundUserGroupUsers(
			List<GameRoundUserGroupUser> gameRoundUserGroupUsers) {
		this.gameRoundUserGroupUsers = gameRoundUserGroupUsers;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GAME_ROUND_ID")
	@Cascade(CascadeType.MERGE)
	public GameRound getGameRound() {
		return gameRound;
	}
	public void setGameRound(GameRound gameRound) {
		this.gameRound = gameRound;
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
		builder.append(this.gameRoundUserGroupId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GameRoundQuestion) {
			GameRoundUserGroup other = (GameRoundUserGroup) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.gameRoundUserGroupId, other.gameRoundUserGroupId);
			return builder.isEquals();
		}
		return false;
	}
	

}

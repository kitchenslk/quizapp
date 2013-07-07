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
 * Date/Time: Jul 7, 2013 - 1:22:05 PM
 * Project	: quizapp
 */
@Entity(name = "gameRoundQuestion")
@Table(name = "GAME_ROUND_QUESTION")
public class GameRoundQuestion implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private String gameroundQuestionId;
	private GameRound gameRound;
	private Question question;
	private int gameRoundQuestionTime;
	private int numberOfGameRoundQuestionUsers;
	private double gameRoundQuestionPoints;
	private	ActiveStatus activeStatus;	
    private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
	
	@Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.GameRoundQuestionIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "GAME_ROUND_QUESTION_ID", length = 12)
	public String getGameroundQuestionId() {
		return gameroundQuestionId;
	}
	public void setGameroundQuestionId(String gameroundQuestionId) {
		this.gameroundQuestionId = gameroundQuestionId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GAME_ROUND_ID", nullable=false)
	@Cascade(CascadeType.MERGE)
	public GameRound getGameRound() {
		return gameRound;
	}
	public void setGameRound(GameRound gameRound) {
		this.gameRound = gameRound;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="QUESTION_ID", nullable=false)
	@Cascade(CascadeType.MERGE)
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
	@Column(name="GAME_ROUND_QUESTION_TIME")
	public int getGameRoundQuestionTime() {
		return gameRoundQuestionTime;
	}
	public void setGameRoundQuestionTime(int gameRoundQuestionTime) {
		this.gameRoundQuestionTime = gameRoundQuestionTime;
	}
	
	@Column(name="NUMBER_OF_GAME_ROUND_QUESTION_USERS")
	public int getNumberOfGameRoundQuestionUsers() {
		return numberOfGameRoundQuestionUsers;
	}
	public void setNumberOfGameRoundQuestionUsers(int numberOfGameRoundQuestionUsers) {
		this.numberOfGameRoundQuestionUsers = numberOfGameRoundQuestionUsers;
	}
	
	@Column(name="GAME_ROUND_QUESTION_POINTS")
	public double getGameRoundQuestionPoints() {
		return gameRoundQuestionPoints;
	}
	public void setGameRoundQuestionPoints(double gameRoundQuestionPoints) {
		this.gameRoundQuestionPoints = gameRoundQuestionPoints;
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
		builder.append(this.gameroundQuestionId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GameRoundQuestion) {
			GameRoundQuestion other = (GameRoundQuestion) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.gameroundQuestionId, other.gameroundQuestionId);
			return builder.isEquals();
		}
		return false;
	}
	
	

}

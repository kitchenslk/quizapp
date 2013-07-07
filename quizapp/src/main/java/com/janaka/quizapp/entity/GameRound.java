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
 * Date/Time: Jul 7, 2013 - 10:54:02 AM
 * Project	: quizapp
 */
@Entity(name = "gameRound")
@Table(name = "GAME_ROUND")
public class GameRound implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String gameRoundId;
	private String gameRoundName;
	private String gameRoundCode;
	private String gameRoundDescription;
	private int numberOfGameRoundUsers;
	private int totalTimeForGameRound;
	private double maxNumberOfGameRoundPoints;
	private double minNumberOfGameRoundPointsToWin;
	private int totalGameRoundQuestions;
	private Game game;
	private	ActiveStatus activeStatus;	
    private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
	
	@Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.GameRoundIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "GAME_ROUND_ID", length = 12)	
	public String getGameRoundId() {
		return gameRoundId;
	}	
	public void setGameRoundId(String gameRoundId) {
		this.gameRoundId = gameRoundId;
	}
	
	@Column(name = "GAME_ROUND_NAME")
	public String getGameRoundName() {
		return gameRoundName;
	}
	public void setGameRoundName(String gameRoundName) {
		this.gameRoundName = gameRoundName;
	}
	
	@Column(name = "GAME_ROUND_CODE")
	public String getGameRoundCode() {
		return gameRoundCode;
	}
	public void setGameRoundCode(String gameRoundCode) {
		this.gameRoundCode = gameRoundCode;
	}
	
	@Column(name = "GAME_ROUND_DESCRIPTION", length=5000)
	public String getGameRoundDescription() {
		return gameRoundDescription;
	}
	public void setGameRoundDescription(String gameRoundDescription) {
		this.gameRoundDescription = gameRoundDescription;
	}
	
	@Column(name = "NUMBER_OF_GAME_ROUND_USERS")
	public int getNumberOfGameRoundUsers() {
		return numberOfGameRoundUsers;
	}
	public void setNumberOfGameRoundUsers(int numberOfGameRoundUsers) {
		this.numberOfGameRoundUsers = numberOfGameRoundUsers;
	}
	
	@Column(name = "TOTAL_TIME_FOR_GAME_ROUND")
	public int getTotalTimeForGameRound() {
		return totalTimeForGameRound;
	}
	public void setTotalTimeForGameRound(int totalTimeForGameRound) {
		this.totalTimeForGameRound = totalTimeForGameRound;
	}
	
	@Column(name = "MAX_NUMBER_OF_GAME_ROUND_POINTS")
	public double getMaxNumberOfGameRoundPoints() {
		return maxNumberOfGameRoundPoints;
	}
	public void setMaxNumberOfGameRoundPoints(double maxNumberOfGameRoundPoints) {
		this.maxNumberOfGameRoundPoints = maxNumberOfGameRoundPoints;
	}
	
	@Column(name = "MIN_NUMBER_OF_GAME_ROUND_POINTS_TOWIN")	
	public void setMinNumberOfGameRoundPointsToWin(
			double minNumberOfGameRoundPointsToWin) {
		this.minNumberOfGameRoundPointsToWin = minNumberOfGameRoundPointsToWin;
	}
	
	public double getMinNumberOfGameRoundPointsToWin() {
		return minNumberOfGameRoundPointsToWin;
	}
	
	@Column(name = "TOTAL_GAME_ROUND_QUESTIONS")
	public int getTotalGameRoundQuestions() {
		return totalGameRoundQuestions;
	}
	public void setTotalGameRoundQuestions(int totalGameRoundQuestions) {
		this.totalGameRoundQuestions = totalGameRoundQuestions;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GAME_ID")
	@Cascade(CascadeType.MERGE)
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
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
		builder.append(this.gameRoundId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GameRound) {
			GameRound other = (GameRound) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.gameRoundId, other.gameRoundId);
			return builder.isEquals();
		}
		return false;
	}
	
	

}

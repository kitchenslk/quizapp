package com.janaka.quizapp.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.janaka.quizapp.enums.ActiveStatus;
import com.janaka.quizapp.enums.GameStatus;

@Entity(name = "game")
@Table(name = "GAME")
public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private	String gameId;
	private	String gameName;
	private String gameCode;
	private String gameDescription;
	private GameStatus gameStatus;
	private UploadedFile icon;
	private int totalNumberOfGameRounds;
	private int totalNumberOfGameQuestions;
	private int totalNumberOfGameUsers;
	private double maxNumberOfPoints;
	private double minNumberOfPointsToWin;
	private	Date gameOpenDate;
	private	Date gameClosingDate;
	private SubCategory subCategory; 
	private List<GameRound> gameRounds;
	private	ActiveStatus activeStatus;	
    private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
	@Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.GameIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "GAME_ID", length = 12)	
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	
	@Column(name = "GAME_NAME")
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	
	@Column(name = "GAME_CODE")	
	public String getGameCode() {
		return gameCode;
	}
	public void setGameCode(String gameCode) {
		this.gameCode = gameCode;
	}
	
	@Column(name = "GAME_DESCRIPTION", length=5000)	
	public String getGameDescription() {
		return gameDescription;
	}
	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "GAME_STATUS")	
	public GameStatus getGameStatus() {
		return gameStatus;
	}
	public void setGameStatus(GameStatus gameStatus) {
		this.gameStatus = gameStatus;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ICON_ID")
	@Cascade(CascadeType.ALL)
	public UploadedFile getIcon() {
		return icon;
	}
	public void setIcon(UploadedFile icon) {
		this.icon = icon;
	}
	
	@Column(name = "TOTAL_NUMBER_OF_GAME_ROUNDS")	
	public int getTotalNumberOfGameRounds() {
		return totalNumberOfGameRounds;
	}
	public void setTotalNumberOfGameRounds(int totalNumberOfGameRounds) {
		this.totalNumberOfGameRounds = totalNumberOfGameRounds;
	}
	
	@Column(name = "TOTAL_NUMBER_OF_GAME_QUESTIONS")	
	public int getTotalNumberOfGameQuestions() {
		return totalNumberOfGameQuestions;
	}
	public void setTotalNumberOfGameQuestions(int totalNumberOfGameQuestions) {
		this.totalNumberOfGameQuestions = totalNumberOfGameQuestions;
	}
	
	@Column(name = "TOTAL_NUMBER_OF_GAME_USERS")	
	public int getTotalNumberOfGameUsers() {
		return totalNumberOfGameUsers;
	}
	public void setTotalNumberOfGameUsers(int totalNumberOfGameUsers) {
		this.totalNumberOfGameUsers = totalNumberOfGameUsers;
	}
	
	@Column(name = "MAX_NUMBER_OF_POINTS")	
	public double getMaxNumberOfPoints() {
		return maxNumberOfPoints;
	}
	public void setMaxNumberOfPoints(double maxNumberOfPoints) {
		this.maxNumberOfPoints = maxNumberOfPoints;
	}
	
	@Column(name = "MIN_NUMBER_OF_POINTS_TO_WIN")	
	public double getMinNumberOfPointsToWin() {
		return minNumberOfPointsToWin;
	}
	public void setMinNumberOfPointsToWin(double minNumberOfPointsToWin) {
		this.minNumberOfPointsToWin = minNumberOfPointsToWin;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GAME_OPEN_DATE")	
	public Date getGameOpenDate() {
		return gameOpenDate;
	}	
	public void setGameOpenDate(Date gameOpenDate) {
		this.gameOpenDate = gameOpenDate;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GAME_CLOSING_DATE")	
	public Date getGameClosingDate() {
		return gameClosingDate;
	}
	public void setGameClosingDate(Date gameClosingDate) {
		this.gameClosingDate = gameClosingDate;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SUB_CATEGORY_ID")
	@Cascade(CascadeType.MERGE)
	public SubCategory getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}
	
	
	@OneToMany(mappedBy="game")
	@Cascade(CascadeType.SAVE_UPDATE)
	public List<GameRound> getGameRounds() {
		return gameRounds;
	}
	public void setGameRounds(List<GameRound> gameRounds) {
		this.gameRounds = gameRounds;
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
		builder.append(this.gameId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Game) {
			Game other = (Game) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.gameId, other.gameId);
			return builder.isEquals();
		}
		return false;
	}
	


}

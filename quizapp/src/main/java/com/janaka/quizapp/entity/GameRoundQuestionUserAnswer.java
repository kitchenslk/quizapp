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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
 * Date/Time: Jul 7, 2013 - 2:10:18 PM
 * Project	: quizapp
 */
@Entity(name = "gameRoundQuestionUserAnswer")
@Table(name = "GAME_ROUND_QUESTION_USER_ANSWER")
public class GameRoundQuestionUserAnswer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String gameRoundQuestionUserAnswerId;
	private GameRoundQuestion gameRoundQuestion;
	private GameRoundUserGroup gameRoundUserGroup;
	private List<Answer> answers;
	private	ActiveStatus activeStatus;	
    private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
	
	@Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.GameRoundQuestionUserAnswerIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "GAME_ROUND_QUESTION_USER_ANSWER_ID", length = 12)
	public String getGameRoundQuestionUserAnswerId() {
		return gameRoundQuestionUserAnswerId;
	}
	public void setGameRoundQuestionUserAnswerId(
			String gameRoundQuestionUserAnswerId) {
		this.gameRoundQuestionUserAnswerId = gameRoundQuestionUserAnswerId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GAME_ROUND_QUESTION_ID")
	@Cascade(CascadeType.MERGE)
	public GameRoundQuestion getGameRoundQuestion() {
		return gameRoundQuestion;
	}
	public void setGameRoundQuestion(GameRoundQuestion gameRoundQuestion) {
		this.gameRoundQuestion = gameRoundQuestion;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="GAME_ROUND_USER_GROUP_ID")
	@Cascade(CascadeType.MERGE)
	public GameRoundUserGroup getGameRoundUserGroup() {
		return gameRoundUserGroup;
	}
	public void setGameRoundUserGroup(GameRoundUserGroup gameRoundUserGroup) {
		this.gameRoundUserGroup = gameRoundUserGroup;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "GAME_ROUND_QUESTION_USER_ANSWER_ANSWERS", joinColumns =
    @JoinColumn(name = "GAME_ROUND_QUESTION_USER_ANSWER_ID"), inverseJoinColumns =
    @JoinColumn(name = "ANSWER_ID"))
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
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
		builder.append(this.gameRoundQuestionUserAnswerId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GameRoundQuestionUserAnswer) {
			GameRoundQuestionUserAnswer other = (GameRoundQuestionUserAnswer) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.gameRoundQuestionUserAnswerId, other.gameRoundQuestionUserAnswerId);
			return builder.isEquals();
		}
		return false;
	}

}

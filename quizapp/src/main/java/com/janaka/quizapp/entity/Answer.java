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

@Entity(name = "answer")
@Table(name = "ANSWER")
public class Answer implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String answerId;
	private String answerName;
	private String answerText;
	private UploadedFile answerImage;
	private boolean correctAnswer;
	private Question question;
	private ActiveStatus activeStatus;
	private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
	@Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.AnswerIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "ANSWER_ID", length = 12)	
    public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	
	@Column(name = "ANSWER_NAME", length=5000)
	public String getAnswerName() {
		return answerName;
	}
	public void setAnswerName(String answerName) {
		this.answerName = answerName;
	}
	
	
	@Column(name = "ANSWER_TEXT", length=10000)
	public String getAnswerText() {
		return answerText;
	}
	public void setAnswerText(String answerText) {
		this.answerText = answerText;
	}
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="ANSWER_IMAGE_ID")
	@Cascade(CascadeType.ALL)
	public UploadedFile getAnswerImage() {
		return answerImage;
	}
	public void setAnswerImage(UploadedFile answerImage) {
		this.answerImage = answerImage;
	}
	
	@Column(name="CORRECT_ANSWER")
	public boolean isCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(boolean correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="QUESTION_ID")
	@Cascade(CascadeType.MERGE)
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
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
   		builder.append(this.answerId);
   		return builder.toHashCode();
   	}

   	@Override
   	public boolean equals(Object obj) {
   		if (obj instanceof Answer) {
   			Answer other = (Answer) obj;
   			EqualsBuilder builder = new EqualsBuilder();
   			builder.append(this.answerId, other.answerId);
   			return builder.isEquals();
   		}
   		return false;
   	}
	


}

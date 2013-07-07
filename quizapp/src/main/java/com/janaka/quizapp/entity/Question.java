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
import com.janaka.quizapp.enums.QuestionType;

@Entity(name = "question")
@Table(name = "QUESTION")
public class Question implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String questionId;
	private String questionName;
	private String questionText;
	private UploadedFile questionImage;	
	private QuestionType questionType;
	private List<Answer> answers;
	private ActiveStatus activeStatus;
	private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
	@Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.QuestionIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "QUESTION_ID", length = 12)	
    public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	
	@Column(name = "QUESTION_NAME", length=5000)
	public String getQuestionName() {
		return questionName;
	}
	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}
	
	
	@Column(name = "QUESTION_TEXT", length=10000)
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	
	
	@OneToMany(mappedBy="question")
	@Cascade(CascadeType.ALL)
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
	
	
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="QUESTION_IMAGE_ID")
	@Cascade(CascadeType.ALL)
	public UploadedFile getQuestionImage() {
		return questionImage;
	}
	public void setQuestionImage(UploadedFile questionImage) {
		this.questionImage = questionImage;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "QUESTION_TYPE")
	public QuestionType getQuestionType() {
		return questionType;
	}
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
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
   		builder.append(this.questionId);
   		return builder.toHashCode();
   	}

   	@Override
   	public boolean equals(Object obj) {
   		if (obj instanceof Question) {
   			Question other = (Question) obj;
   			EqualsBuilder builder = new EqualsBuilder();
   			builder.append(this.questionId, other.questionId);
   			return builder.isEquals();
   		}
   		return false;
   	}
	


}

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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import com.janaka.quizapp.enums.ActiveStatus;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 7, 2013 - 10:26:02 AM
 * Project	: quizapp
 */
@Entity(name = "subCategory")
@Table(name = "SUB_CATEGORY")
public class SubCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	private String subCategoryId;
	private String subCategoryName;
	private String subCategoryDescription;
	private Category category;
	private List<Game> games;
	private	ActiveStatus activeStatus;	
	private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
		
	@Id
    @GenericGenerator(name = "seq_id", strategy = "com.janaka.quizapp.idgenerators.SubCategoryIdGenerator")
    @GeneratedValue(generator = "seq_id")
    @Column(name = "SUB_CATEGORY_ID", length = 12)	
	public String getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(String subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	
	@Column(name="SUB_CATEGORY_NAME")
	public String getSubCategoryName() {
		return subCategoryName;
	}
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
	
	@Column(name="SUB_CATEGORY_DESCRIPTION", length=5000)
	public String getSubCategoryDescription() {
		return subCategoryDescription;
	}
	public void setSubCategoryDescription(String subCategoryDescription) {
		this.subCategoryDescription = subCategoryDescription;
	}
	
	
	@OneToMany(mappedBy="subCategory", fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	public List<Game> getGames() {
		return games;
	}
	public void setGames(List<Game> games) {
		this.games = games;
	}
	
	@ManyToOne()
	@JoinColumn(name="CATEGORY_ID")
	@Cascade(CascadeType.MERGE)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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

}

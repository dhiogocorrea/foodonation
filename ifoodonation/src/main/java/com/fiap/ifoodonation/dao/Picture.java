package com.fiap.ifoodonation.dao;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "picture")
@SequenceGenerator(name = "PICTURE_SEQ", sequenceName = "PICTURE_SEQ", initialValue = 1, allocationSize = 1)
public class Picture {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PICTURE_SEQ")
	private long pictureId;
	
	@Column(length=10485760)
	private String imageData;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdIn;
	
	public Picture() {}

	public Picture(String imageData) {
		this.imageData = imageData;
	}

	@PrePersist
	void onCreate() {
		this.createdIn = new Date();
	}

	public long getPictureId() {
		return pictureId;
	}

	public void setPictureId(long pictureId) {
		this.pictureId = pictureId;
	}

	public String getImageData() {
		return imageData;
	}

	public void setImageData(String imageData) {
		this.imageData = imageData;
	}

	public Date getCreatedIn() {
		return createdIn;
	}

	public void setCreatedIn(Date createdIn) {
		this.createdIn = createdIn;
	}
}

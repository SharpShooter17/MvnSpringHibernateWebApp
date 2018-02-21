package web;

import lombok.Data;

import org.joda.time.contrib.hibernate.PersistentYearMonthDay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

import java.sql.Date;

@Data
@Entity
@Table(name = "ra_ranking_t")
public class Ranking {
	
	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "SCORE")
	private Long score;	
	
	public void addScore(){
		this.score += 100;
		System.out.println("Id: " + this.id + " score: " + this.score);
	}
}
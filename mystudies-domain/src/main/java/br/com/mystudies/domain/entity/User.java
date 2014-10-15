package br.com.mystudies.domain.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;

	@Column(name="PASSWORD")
	private String password;

	@Column(name="EMAIL")
	private String email;



	public String getEmail() {
		return email;
	}



	public String getPassword() {
		return password;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPassWord(String password) {
		this.password = password;
	}



}

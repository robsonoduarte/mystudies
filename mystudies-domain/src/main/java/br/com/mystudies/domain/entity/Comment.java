package br.com.mystudies.domain.entity;

/**
 * @author Robson
 */
public class Comment implements EntityBase{

	private static final long serialVersionUID = 1L;


	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}

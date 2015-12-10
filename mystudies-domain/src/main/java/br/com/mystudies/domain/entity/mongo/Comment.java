package br.com.mystudies.domain.entity.mongo;

/**
 * @author Robson
 */
public class Comment implements Entity{

	private static final long serialVersionUID = 1L;


	private String comment;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}

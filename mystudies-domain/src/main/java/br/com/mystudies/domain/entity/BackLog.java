package br.com.mystudies.domain.entity;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 * @author Robson
 * 
 */
@Entity
public class BackLog extends BaseEntity{

	private static final long serialVersionUID = 1L;


	@Id
	private Long id;


	@OneToMany(mappedBy="backLog", cascade=CascadeType.ALL, fetch= FetchType.EAGER) // FIXME: can't is EAGER
	private Set<Theme> themes;


	public void addTheme(Theme theme) {
		if(themes == null){
			themes = new HashSet<Theme>();
		}
		theme.setBackLog(this); // FIXME: Null pointer, remove this logic there ?
		themes.add(theme);
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Set<Theme> getThemes() {
		return themes;
	}

	public void setThemes(Set<Theme> themes) {
		this.themes = themes;
	}

}

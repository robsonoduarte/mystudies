package br.com.mystudies.domain.entity.mongo;


import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 *
 *
 * @author Robson
 */
@Entity
@Table(name="BACKLOG")
public class BackLog extends BaseEntity{

	private static final long serialVersionUID = 1L;


	@Id
	private Long id;


	@OneToMany(mappedBy="backLog", cascade=ALL, fetch = EAGER) 
	private Set<Theme> themes;


	public void addTheme(Theme theme) {
		if(themes == null){
			themes = new HashSet<Theme>();
		}
//		theme.setBackLog(this); // FIXME: Null pointer, remove this logic there ?
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

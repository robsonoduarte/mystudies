package br.com.mystudies.service.bean;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.service.ThemeService;
import br.com.r3wa.fiscalpackage.persistence.Repository;


/**
 * @author robson
 *
 */
@Stateless
@Local(ThemeService.class)
public class ThemeServiceBean implements ThemeService{

	@EJB
	private Repository repository;

	
	@Override
	public Theme getTheme(Long themeId) {
		return repository.find(Theme.class ,themeId);
	}

	
	@Override
	public Theme addStory(Theme theme, Story story) {
		theme.getStories().add(story);
		story.setTheme(theme);
		return repository.save(theme);
	}

	
}

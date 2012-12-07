package br.com.mystudies.service;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.service.persistence.ThemeDao;


/**
 * @author robson
 *
 */
@Stateless
@Remote(ThemeService.class)
public class ThemeServiceBean implements ThemeService{

	@EJB
	private ThemeDao themeDao;

	
	@Override
	public Theme getTheme(Long themeId) {
		return themeDao.find(themeId);
	}

	@Override
	public Theme addStory(Theme theme, Story story) {
		theme.getStories().add(story);
		story.setTheme(theme);
		return themeDao.update(theme);
	}

}

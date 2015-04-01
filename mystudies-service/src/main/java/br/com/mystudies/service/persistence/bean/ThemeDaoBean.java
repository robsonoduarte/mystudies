package br.com.mystudies.service.persistence.bean;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.mystudies.domain.entity.Theme;
import br.com.mystudies.service.persistence.ThemeDao;

@Stateless
@Local(ThemeDao.class)
public class ThemeDaoBean implements ThemeDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Theme find(Long themeId) {
		return entityManager.find(Theme.class, themeId);
	}

	@Override
	public Theme update(Theme theme) {
		return entityManager.merge(theme);
	}

}

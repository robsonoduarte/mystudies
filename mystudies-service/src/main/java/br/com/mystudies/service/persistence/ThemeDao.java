package br.com.mystudies.service.persistence;

import br.com.mystudies.domain.entity.Theme;

public interface ThemeDao {

	Theme find(Long themeId);

	Theme update(Theme theme);

}

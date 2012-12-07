package br.com.mystudies.service;

import br.com.mystudies.domain.entity.Story;
import br.com.mystudies.domain.entity.Theme;

public interface ThemeService {

	Theme getTheme(Long themeId);

	Theme addStory(Theme theme, Story story);

}

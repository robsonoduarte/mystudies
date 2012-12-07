package br.com.mystudies.service.persistence;

import java.util.List;

import br.com.mystudies.domain.entity.Sprint;
import br.com.mystudies.domain.enun.SprintStatus;

public interface SprintDao {

	Sprint findSprintByStatus(SprintStatus sprintStatus);

	Sprint persist(Sprint sprint);

	Sprint update(Sprint sprint);
	
	List<Sprint> listAll();

}

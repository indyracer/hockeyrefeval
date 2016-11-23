package org.launchcode.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.models.Evaluator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EvaluatorDao extends CrudRepository<Evaluator, Integer> {
	
	Evaluator findByUid(int uid);
	List<Evaluator> findAll();
	Evaluator findByUsername(String username);
	
	
	

}

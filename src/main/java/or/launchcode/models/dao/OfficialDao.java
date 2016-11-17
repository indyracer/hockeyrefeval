package or.launchcode.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.models.Official;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface OfficialDao extends CrudRepository<Official, Integer> {
	
	Official findByUid(int uid);
	List<Official> findAll();
	Official findByUsername(String username);
	Official findByLevel(int level);
	Official findByFullName(String fullName);
	

}

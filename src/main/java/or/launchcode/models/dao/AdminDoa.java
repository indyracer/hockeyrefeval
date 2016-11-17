package or.launchcode.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.launchcode.models.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface AdminDoa extends CrudRepository<Admin, Integer>{
	
	Admin findByUid(int uid);
	List<Admin> findAll();
	Admin findByUsername(String username);
	Admin findByAdminFullName(String adminFullName);

}

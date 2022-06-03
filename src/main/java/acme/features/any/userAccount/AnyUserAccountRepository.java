package acme.features.any.userAccount;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyUserAccountRepository extends AbstractRepository{
	
	@Query("select u from UserAccount u where u.enabled = true")
	List<UserAccount> findEnabledUserAccounts();
	
	@Query("select u from UserAccount u where u.id = :id")
	UserAccount findById(int id);

}

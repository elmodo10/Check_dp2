package acme.features.inventor.patronage;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorPatronageRepository extends AbstractRepository{

	@Query("select p from Patronage p where p.inventor.id = :id")
	List<Patronage> findPatronagesByInventorId(int id);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findById(int id);
	
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);
}

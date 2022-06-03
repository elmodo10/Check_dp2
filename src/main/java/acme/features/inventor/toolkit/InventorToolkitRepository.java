package acme.features.inventor.toolkit;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {
	
	@Query("select t from Toolkit t where t.inventor.id = :id")
	List<Toolkit> findOwnToolkits(int id);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);
	
	@Query("select q from Quantity q where q.toolkit.id = :id ")
	List<Quantity> findQuantityByToolkitId(int id);
	
	@Query("select i from Item i")
	List<Item> findManyItem();
	
	@Query("select t from Toolkit t WHERE t.code = :code")
	Toolkit findToolkitByCode(String code);

}

package acme.features.inventor.item;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorItemRepository extends AbstractRepository{

	@Query("select i from Item i where i.inventor.id = :id")
	Collection<Item> findItemsByInventorId(int id);
	
	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);
	
	@Query("select t from Toolkit t where t.id = :masterId")
	Toolkit findOneToolkitById(int masterId);
	

	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :masterId")
	Collection<Item> findItemsByToolkitId(int masterId);
	
	@Query("select distinct(q.toolkit) from Quantity q where q.item.inventor.id = :id")
	Collection<Toolkit> findToolkitsByInventorId(int id);
	
	@Query("select i from Item i where i.type = acme.entities.item.ItemType.COMPONENT and i.inventor.id = :id")
	List<Item> findComponentsByInventorId(int id);
	
	@Query("select i from Item i where i.type = acme.entities.item.ItemType.TOOL and i.inventor.id = :id")
	List<Item> findToolsByInventorId(int id);
	
	@Query("select i from Item i where i.id = :id")
	Item findOneItemById(int id);

	@Query(value="select i from Inventor i where i.userAccount.id = :id")
	Inventor findInventorByUserAccountId(int id);
	
	@Query("select i from Item i WHERE i.code = :code")
	Item findItemByCode(String code);
	
	@Query("select i.id from Inventor i WHERE i.userAccount.id = :id")
	Integer findUsersInventorId(Integer id);
	
	@Query("select c from Chimpum c WHERE c.artefact.id = :id")
	Chimpum findchimpumbyartifactId(Integer id);
}

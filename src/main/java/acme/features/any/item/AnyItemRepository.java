package acme.features.any.item;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyItemRepository extends AbstractRepository {
	
	@Query( "select t from Item t where t.type = acme.entities.item.ItemType.COMPONENT and t.status = acme.enums.PublishedStatus.PUBLISHED" )
	Collection<Item> findPublishedComponents();
	
	@Query( "select t from Item t where t.type = acme.entities.item.ItemType.TOOL and t.status = acme.enums.PublishedStatus.PUBLISHED" )
	Collection<Item> findPublishedTools();
	
	@Query("select t from Item t where t.id= :id")
	Item findItemById(int id);
	
	@Query("select q.item from Quantity q where q.toolkit.id= :id")
	List<Item> findItemByToolkitId(int id);
	
	@Query("SELECT item FROM Item item WHERE item.type = acme.entities.item.ItemType.TOOL")
	Collection<Item> findTools();
	
	@Query("SELECT item FROM Item item WHERE item.type = acme.entities.item.ItemType.TOOL AND item.id = :id")
	Item findToolById(Integer id);
	
}

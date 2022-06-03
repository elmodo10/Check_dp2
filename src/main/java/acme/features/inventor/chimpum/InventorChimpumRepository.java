package acme.features.inventor.chimpum;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.chimpum.Chimpum;
import acme.entities.item.Item;
import acme.entities.patronage.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorChimpumRepository extends AbstractRepository{
	
	@Query("select c from Chimpum c where c.artefact.inventor.id = :id")
	Collection<Chimpum> findChimpumsByItemId(int id);
	
	@Query("select p from Chimpum p where p.artefact.id = :id")
	List<Chimpum> findByItemId(int id);
	
	@Query("select p from Item p where p.id = :id")
	Item findItemById(int id);
	
	@Query("select p from Chimpum p where p.id = :id")
	Chimpum findOneChimpumById(int id);
	
	@Query("select p from Chimpum p where p.artefact.id = :id")
	Collection<Chimpum> findChimpumById2(int id);
	
	@Query("select p from Chimpum p")
	List<Chimpum> findChimpums();
	
	@Query("select p from Chimpum p WHERE p.code = :code")
	Chimpum findChimpumByCode(String code);
	
	
	@Query("select c.artefact from Chimpum c")
    List<Item> findItemsWithChimpum();

}

package acme.features.patron.patronage;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.Item;
import acme.entities.patronage.Patronage;
import acme.entities.patronagereport.PatronageReport;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;
@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);

	@Query("select p from Patronage p where p.patron.id = :patronId")
	Collection<Patronage> findAllPatronagesByPatronId(int patronId);
	
	@Query("select p from PatronageReport p where p.patronage.id = :id ")
	List<PatronageReport> findPatronageReportBypatronageId(int id);
	
	@Query("select patron from Patron patron WHERE patron.id=:id")
    Patron findPatronById(int id);
	
	@Query("select inventor from Inventor inventor WHERE inventor.userAccount.username=:un")
    Inventor findInventorByUsername(String un);
	
	@Query("select i from Inventor i")
    Collection<Inventor> findInventors();
	
	@Query("select p from Patronage p WHERE p.code = :code")
	Patronage findPatronageByCode(String code);

}

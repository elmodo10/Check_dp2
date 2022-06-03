package acme.features.inventor.patronageReports;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage.Patronage;
import acme.entities.patronagereport.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository{
	
	@Query("select p from PatronageReport p where p.patronage.inventor.id = :id")
	Collection<PatronageReport> findPatronageReportsByInventorId(int id);
	
	@Query("select p from PatronageReport p where p.patronage.id = :id")
	List<PatronageReport> findByPatronageId(int id);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);
	
	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findOnePatronageReportById(int id);
	
	@Query("select p from PatronageReport p where p.patronage.id = :id")
	Collection<PatronageReport> findPatronageReportById2(int id);
	
	@Query("select p from PatronageReport p")
	List<PatronageReport> findPatronageReports();
	
}

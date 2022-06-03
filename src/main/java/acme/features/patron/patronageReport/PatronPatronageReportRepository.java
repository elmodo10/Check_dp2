package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronagereport.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository {
	
	
	@Query("select p from PatronageReport p where p.id = :id")
	PatronageReport findPatronageReportById(int id);
	
	
	
	@Query("select p from PatronageReport p where p.patronage.id = :patronageId")
	Collection<PatronageReport> findPatronageReportsByPatronageId(int patronageId);
	
	@Query("select p from PatronageReport p where p.patronage.patron.id = :patronId")
	Collection<PatronageReport> findPatronageReportByPatronId(int patronId);

}

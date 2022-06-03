package acme.features.patron.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.item.ItemType;
import acme.enums.Status;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {


	
	@Query("SELECT COUNT(patronage) FROM Patronage patronage WHERE patronage.status = :status AND patronage.patron.id = :id")
	Integer getPatronageTotalsByStatus(Status status, int id);
	
	@Query("SELECT patronage.budget.currency, MIN(patronage.budget.amount), MAX(patronage.budget.amount), AVG(patronage.budget.amount), STDDEV(patronage.budget.amount) from Patronage patronage WHERE patronage.status = :status AND patronage.patron.id = :id GROUP BY patronage.budget.currency")
	List<String> getPatronageBudgetByStatus(Status status, int id);
	
	
	
	
}

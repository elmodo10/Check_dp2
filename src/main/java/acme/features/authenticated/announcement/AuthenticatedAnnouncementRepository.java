package acme.features.authenticated.announcement;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcement.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedAnnouncementRepository extends AbstractRepository {
	
	@Query("select t from Announcement t where t.creation > :fecha")
	List<Announcement> findAnnouncements(Date fecha);
	
	@Query("select t from Announcement t where t.id = :id")
	Announcement findAnnouncementById(int id);
	


}

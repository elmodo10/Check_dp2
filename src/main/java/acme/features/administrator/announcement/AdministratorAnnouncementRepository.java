package acme.features.administrator.announcement;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.announcement.Announcement;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAnnouncementRepository extends AbstractRepository {


	@Query("select c from Announcement c where c.creation > :maxTime")
	Collection<Announcement> findLastAnnouncement(Date maxTime);
}

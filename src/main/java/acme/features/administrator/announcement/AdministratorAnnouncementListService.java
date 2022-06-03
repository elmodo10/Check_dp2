
package acme.features.administrator.announcement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acme.entities.announcement.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorAnnouncementListService implements AbstractListService<Administrator, Announcement> {

	@Autowired
	protected AdministratorAnnouncementRepository repository;


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return true;
	}

	@Override
	public Collection<Announcement> findMany(final Request<Announcement> request) {
		assert request != null;

		Collection<Announcement> res;
		Calendar calendar;
		Date maxTime;

		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		maxTime = calendar.getTime();

		res = this.repository.findLastAnnouncement(maxTime);

		return res;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "creation", "title", "body", "critic", "link");

	}

}

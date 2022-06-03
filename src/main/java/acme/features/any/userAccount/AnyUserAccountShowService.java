package acme.features.any.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountShowService implements AbstractShowService<Any, UserAccount>{

	@Autowired
	protected AnyUserAccountRepository repository;
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final UserAccount u = this.repository.findById(id);
		
		return (!u.isAnonymous() && !u.hasRole(Administrator.class));
	}
	
	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		
		return this.repository.findById(id);
	}
	
	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "identity.name", "identity.surname", "identity.email");
	}
}

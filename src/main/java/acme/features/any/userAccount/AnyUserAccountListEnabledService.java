package acme.features.any.userAccount;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Administrator;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountListEnabledService implements AbstractListService<Any, UserAccount>{

	@Autowired
	protected AnyUserAccountRepository repository;
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public List<UserAccount> findMany(final Request<UserAccount> request){
		assert request != null;
		
		final List<UserAccount> enabled = this.repository.findEnabledUserAccounts();
		final List<UserAccount> res = new ArrayList<>();
		
		for(final UserAccount u: enabled) {
			if(!u.isAnonymous() && !u.hasRole(Administrator.class)) {
				res.add(u);
			}
		}
		return res;
	}
	
	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model,"identity.name", "identity.surname");
		
		final Collection<UserRole> roles = entity.getRoles();
		final StringBuilder listRoles = new StringBuilder();
		for(final UserRole rol: roles) {
			listRoles.append(rol.getAuthorityName());
			listRoles.append(" ");
		}
		model.setAttribute("listRoles", listRoles);
		
	}
}

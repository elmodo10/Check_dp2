package acme.features.inventor.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.toolkit.Status;
import acme.entities.toolkit.Toolkit;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor, Toolkit>{
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository confRepository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		final int id = request.getModel().getInteger("id");
		final Inventor i = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		final Toolkit t = this.repository.findToolkitById(id);
		
		return t.getInventor().getId()==i.getId() && t.getStatus()==Status.NON_PUBLISHED;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "descripcion", "assemblyNotes", "title", "link");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "descripcion", "assemblyNotes", "link", "status");
		
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		
		return this.repository.findToolkitById(request.getModel().getInteger("id"));
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Collection<Configuration> config = this.confRepository.findConfigurations();
		
		for(final Configuration c : config) {
			errors.state(request, !c.isSpamStrong(entity.getCode()), "code", "inventor.toolkit.code.strongSpam");
			errors.state(request, !c.isSpamStrong(entity.getTitle()), "title", "inventor.toolkit.title.strongSpam");
			errors.state(request, !c.isSpamStrong(entity.getDescripcion()), "descripcion", "inventor.toolkit.description.strongSpam");
			errors.state(request, !c.isSpamStrong(entity.getAssemblyNotes()), "assemblyNotes", "inventor.toolkit.assemblyNotes.strongSpam");
			errors.state(request, !c.isSpamStrong(entity.getLink()), "link", "inventor.toolkit.link.strongSpam");
			
			errors.state(request, !c.isSpamWeak(entity.getCode()), "code", "inventor.toolkit.code.weakSpam");
			errors.state(request, !c.isSpamWeak(entity.getTitle()), "title", "inventor.toolkit.title.weakSpam");
			errors.state(request, !c.isSpamWeak(entity.getDescripcion()), "descripcion", "inventor.toolkit.description.weakSpam");
			errors.state(request, !c.isSpamWeak(entity.getAssemblyNotes()), "assemblyNotes", "inventor.toolkit.assemblyNotes.weakSpam");
			errors.state(request, !c.isSpamWeak(entity.getLink()), "link", "inventor.toolkit.link.weakSpam");
		}
		
		final Toolkit toolkit = this.repository.findToolkitByCode(entity.getCode());
		
		if(toolkit != null) {
			errors.state(request, toolkit.getId() == entity.getId(), "code", "inventor.item.title.codeNotUnique");
		}
		
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}

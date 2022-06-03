package acme.features.inventor.toolkit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configuration.Configuration;
import acme.entities.item.Item;
import acme.entities.quantity.Quantity;
import acme.entities.toolkit.Status;
import acme.entities.toolkit.Toolkit;
import acme.features.administrator.configurations.AdministratorConfigurationRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit>{

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected AdministratorConfigurationRepository confRepository;
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		
		final Inventor i = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		
		return i!=null;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		request.bind(entity, errors, "code", "title", "descripcion", "assemblyNotes", "link");
		
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "descripcion", "assemblyNotes", "link");
		
		final List<Item> items = this.repository.findManyItem();
		model.setAttribute("items", items);
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;
		
		final Toolkit res = new Toolkit();
		final Inventor i = this.repository.findInventorByUserAccountId(request.getPrincipal().getAccountId());
		final List<Quantity> quantities = new ArrayList<>();
		
		res.setInventor(i);
		res.setQuantity(quantities);
		res.setCode(this.generateCode());
		res.setDescripcion("");
		res.setAssemblyNotes("");
		res.setLink("");
		res.setStatus(Status.NON_PUBLISHED);
		
		return res;
	}
	
	private String generateCode() {
		String code = "";
		final List<String> alphabet = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
		
		for(int i=0; i<3; i++) {
			code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
		}
		code += "-";
		for(int i=0; i<3; i++) {
			code += Integer.toString(ThreadLocalRandom.current().nextInt(0, 9));
		}
		code += "-";
		code += alphabet.get(ThreadLocalRandom.current().nextInt(0, alphabet.size()));
		
		return code;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final Collection<Configuration> config = this.confRepository.findConfigurations();
		
		errors.state(request, this.repository.findToolkitByCode(entity.getCode()) == null, "code", "inventor.toolkit.title.codeNotUnique");
		
		for(final Configuration c : config) {
			errors.state(request, !c.isSpamStrong(entity.getCode()), "code", "inventor.toolkit.code.strongSpam");
			errors.state(request, !c.isSpamStrong(entity.getTitle()), "title", "inventor.toolkit.title.strongSpam");
			errors.state(request, !c.isSpamStrong(entity.getDescripcion()), "descripcion", "inventor.toolkit.description.strongSpam");
			errors.state(request, !c.isSpamStrong(entity.getAssemblyNotes()), "assemblyNotes", "inventor.toolkit.assemblyNotes.strongSpam");
			errors.state(request, !c.isSpamStrong(entity.getLink()), "link", "inventor.toolkit.link.strongSpam");
			
			errors.state(request, !c.isSpamWeak(entity.getCode()), "code", "inventor.toolkit.code.strongSpam");
			errors.state(request, !c.isSpamWeak(entity.getTitle()), "title", "inventor.toolkit.title.strongSpam");
			errors.state(request, !c.isSpamWeak(entity.getDescripcion()), "descripcion", "inventor.toolkit.description.strongSpam");
			errors.state(request, !c.isSpamWeak(entity.getAssemblyNotes()), "assemblyNotes", "inventor.toolkit.assemblyNotes.strongSpam");
			errors.state(request, !c.isSpamWeak(entity.getLink()), "link", "inventor.toolkit.link.strongSpam");
		}
		
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
		
	}

}

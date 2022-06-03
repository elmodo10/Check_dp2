package acme.features.any.chirp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.chirp.Chirp;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyChirpController extends AbstractController<Any, Chirp>{

	@Autowired
	protected AnyChirpListService listService;
	
	//Me pide solo listar(list) por lo que no será necesario mostrar(show) nada.
	
	@Autowired
	protected AnyChirpCreateService createService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("create", this.createService);

	}
}
package acme.entities.quantity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Quantity extends AbstractEntity{
	
	@Min(1)
	protected int number;
	
	@ManyToOne
	@Valid
	protected Item item;
	
	@ManyToOne
	@Valid
	protected Toolkit toolkit;

}

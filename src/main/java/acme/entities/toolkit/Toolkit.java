package acme.entities.toolkit;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.quantity.Quantity;
import acme.forms.MoneyExchange;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Toolkit extends AbstractEntity {

	//Serialisation identifier     ---------------------------------------------
	
	protected static final long	serialVersionUID= 1L;
	
	// Attributes
	
	@NotBlank
	@Column(unique=true)
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 100)
	protected String title;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String descripcion;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String assemblyNotes;
	
	@URL
	protected String link;
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	protected Inventor inventor;
	
	@Valid
	@OneToMany(mappedBy="toolkit", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	protected List<Quantity> quantity;
	
	@NotNull
	protected Status status;
	
	
	public Money getRetailPrice(final String targetCurrency) {
		
		final Money res = new Money();
		
		final List<Quantity> cantidad = this.quantity;
		
		if(!cantidad.isEmpty()) {
			Double aux = 0.0;
			for(final Quantity c: cantidad ) {
				final MoneyExchange me = new MoneyExchange(c.getItem().getRetailPrice(), targetCurrency);
				
				aux = aux + (c.getNumber()*me.getExchange().getAmount());
			}
			res.setAmount(aux);
			res.setCurrency(targetCurrency);
		} else {
			res.setAmount(0.0);
			res.setCurrency("EUR");
		}
		
		return res;
	}
	
}
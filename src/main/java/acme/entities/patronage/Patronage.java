package acme.entities.patronage;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.enums.PublishedStatus;
import acme.enums.Status;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import acme.roles.Inventor;
import acme.roles.Patron;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Patronage extends AbstractEntity {

	protected static final long serialVersionUID = 1L;
	
	@NotNull
	protected Status status;
	
	@Pattern(regexp = "^[A-Z]{3}-[0-9]{3}(-[A-Z])?$")
	@Column(unique=true)
	protected String code;
	
	@NotBlank
	@Length(min = 1, max = 255)
	protected String legalStuff;
	 
	@NotNull
	protected Money budget;
	
	
	@NotNull
	protected PublishedStatus publishedStatus;
	

	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date startsAt;
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date finishesAt;
	
	@URL
	protected String link;
	
	@ManyToOne
	@Valid
	protected Patron patron;
	
	@ManyToOne
	@Valid
	protected Inventor inventor;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date creationTime;
	
	
}

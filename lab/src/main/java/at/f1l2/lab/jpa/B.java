package at.f1l2.lab.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "B")
public class B {

	@Id
	// @SequenceGenerator(name = "SEQ")
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private Long id;

	private String description;

	@ManyToMany(cascade = CascadeType.ALL)
	private List<C> cs;

	@ManyToOne(targetEntity = A.class)
	private A parent;
	// Getter & Setter

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<C> getCs() {
		return cs;
	}

	public void setCs(List<C> cs) {
		this.cs = cs;
	}

	public A getParent() {
		return parent;
	}

	public void setParent(A parent) {
		this.parent = parent;
	}

}
package at.f1l2.laboratory.jpa;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table
@Entity
public class C {

	@Id
	// @SequenceGenerator(name = "SEQ")
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private Long id;

	private String description;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = B.class)
	private List<B> bs;
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

	public List<B> getBs() {
		return bs;
	}

	public void setBs(List<B> bs) {
		this.bs = bs;
	}
}
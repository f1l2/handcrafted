package at.f1l2.laboratroy.generic;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "A")
public class Employee {

	@Id
	// @SequenceGenerator(name = "SEQ")
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ")
	private Long id;

}

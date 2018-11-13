package at.f1l2.lab.jpa;

import org.springframework.stereotype.Repository;

@Repository
public class ARepository extends BaseRepository<A> {

	public ARepository() {
		super(A.class);
	}

}

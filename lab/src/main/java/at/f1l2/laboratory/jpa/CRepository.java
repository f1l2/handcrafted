package at.f1l2.laboratory.jpa;

import org.springframework.stereotype.Repository;

@Repository
public class CRepository extends BaseRepository<C> {

	public CRepository() {
		super(C.class);
	}

}

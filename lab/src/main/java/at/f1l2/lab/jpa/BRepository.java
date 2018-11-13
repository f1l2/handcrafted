package at.f1l2.lab.jpa;

import org.springframework.stereotype.Repository;

@Repository
public class BRepository extends BaseRepository<B> {

	public BRepository() {
		super(B.class);
	}

}

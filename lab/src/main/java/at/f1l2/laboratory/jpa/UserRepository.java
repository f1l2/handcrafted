package at.f1l2.laboratory.jpa;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<User> {

	public UserRepository() {
		super(User.class);
	}

}

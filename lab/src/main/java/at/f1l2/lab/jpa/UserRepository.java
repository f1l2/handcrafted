package at.f1l2.lab.jpa;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends BaseRepository<User> {

	public UserRepository() {
		super(User.class);
	}

}

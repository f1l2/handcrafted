package at.f1l2.laboratory.jpa;

import org.springframework.stereotype.Repository;

@Repository
public class UserFriendRepository extends BaseRepository<UserFriend> {

	public UserFriendRepository() {
		super(UserFriend.class);
	}

}

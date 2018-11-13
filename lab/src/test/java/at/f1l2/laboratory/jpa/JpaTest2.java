package at.f1l2.laboratory.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;

import at.f1l2.lab.jpa.User;
import at.f1l2.lab.jpa.UserFriend;
import at.f1l2.lab.jpa.UserFriendRepository;
import at.f1l2.lab.jpa.UserRepository;

@ContextConfiguration(locations = { "file:src/test/resources/context.xml" })
@RunWith(SpringRunner.class)
@Transactional
public class JpaTest2 {

	@PersistenceContext
	private EntityManager em;

	private User user1, user2, user3;

	@Autowired
	private UserFriendRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Before
	public void before() {

		user1 = new User();
		user2 = new User();
		user3 = new User();

		UserFriend userFriend = new UserFriend();
		userFriend.setUser(user1);
		userFriend.setFriend(user2);

		repository.save(userFriend);

		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user2);

		TestTransaction.flagForCommit();
		TestTransaction.end();
		TestTransaction.start();

	}

	@Test
	public void testAll() {

		List resultList = em.createQuery("FROM " + UserFriend.class.getSimpleName()).getResultList();

		Assert.assertEquals(1, resultList.size());

	}
}

package at.f1l2.laboratory.jpa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.transaction.TestTransaction;

@ContextConfiguration(locations = { "file:src/test/resources/context.xml" })
@RunWith(SpringRunner.class)
@Transactional
public class JpaTest {

	/**
	 * Certain associations an Entity can have can be deemed 'bags' by
	 * Hibernate. A bag is an unordered collection that permits duplicate
	 * elements and when persisting these collections, the order of elements
	 * cannot be guaranteed. For these reasons, bags are an efficient way of
	 * representing a collection as you can add to a bag collection without the
	 * collection having been loaded. An example of a bag would be an ArrayList.
	 * 
	 * A common problem encountered when modeling entities using Hibernate is
	 * that of multiple bags. It happens when an Entity contains two or more
	 * associations which are bags that have a fetch strategy of EAGER. This
	 * could be an Entity which has two member collections like the Company
	 * class below, or an Entity that contains collections within its object
	 * graph.
	 * 
	 * The Hibernate exception when identifying more than one bag to fetch is
	 * shown below:
	 * 
	 * Caused by: org.hibernate.HibernateException: cannot simultaneously fetch
	 * multiple bags
	 * 
	 * There are a few ways to resolve this problem once the offending
	 * association(s) have been identified. One option is to make all or all but
	 * one collection have a fetch strategy of LAZY. This is the default
	 * strategy for Hibernate. This would mean that LAZY associations would not
	 * be loaded until requested but if requested outside of a session, this
	 * would raise a LazyInitializationException. One way around is to load the
	 * collection when in a collection (Hibernate.initialize)
	 *
	 * Another option is to revise the type of collection being used. Does it
	 * have to be java.util.List? An alternative could be to use java.util.Set
	 * which isn't treated by Hibernate as a bag.
	 *
	 * If it must be java.util.List, then another solution is to
	 * use @IndexColumn which would mean the Collection semantic is that of List
	 * and not Bag. As well as a name, the Index can have a base number which if
	 * not specified will default to zero.
	 *
	 */

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private ARepository aRepository;

	@Autowired
	private BRepository bRepository;

	@Autowired
	private CRepository cRepository;

	private A a1, a2, a3;

	private B b1, b2, b3, b4, b5, b6;

	private C c1, c2, c3;

	@Before
	public void before() {

		buildAs();
		buildBs();
		buildCs();
		setRelations();

		aRepository.save(a1);
		aRepository.save(a2);
		aRepository.save(a3);

		TestTransaction.flagForCommit();
		TestTransaction.end();
		TestTransaction.start();

	}

	@Ignore
	@Test
	public void testAll() {

		aRepository.findAll().size();

		TestTransaction.flagForCommit();
		TestTransaction.end();
		TestTransaction.start();
	}

	@Test
	public void test2() {

		String query = "SELECT a FROM A a INNER JOIN a.bs b INNER JOIN b.cs c WHERE (b.id,b.parent) IN (SELECT MAX(b2.id),b2.parent FROM B b2 GROUP BY b2.parent) AND c.description = 'c3'";

		List<A> resultList = em.createQuery(query).getResultList();

		Assert.assertEquals(1, resultList.size());

		Assert.assertEquals("a2", resultList.get(0).getDescription());

	}

	// @Test
	public void test() {

		String query = "SELECT c1 FROM A c1 INNER JOIN c1.bs c2 INNER JOIN c2.cs c3 where c3.id = :id GROUP BY c1";

		List<A> allAsWithFirstBContainingC = new ArrayList<>();

		@SuppressWarnings("unchecked")
		List<A> allAsWithC3 = (List<A>) em.createQuery(query).setParameter("id", c3.getId()).getResultList();
		allAsWithC3.stream().forEach(item -> {
			Optional<B> newestItem = item.getBs().stream().min(Comparator.comparing(B::getId));
			Predicate<C> predicate = p -> p.getId() == c3.getId();
			if (newestItem.get().getCs().stream().anyMatch(predicate)) {
				allAsWithFirstBContainingC.add(item);
			}
		});

		Assert.assertEquals(2, allAsWithC3.size());
		Assert.assertEquals(1, allAsWithFirstBContainingC.size());

		TestTransaction.flagForCommit();
		TestTransaction.end();
		TestTransaction.start();
	}

	@After
	public void after() {

		Assert.assertEquals(Long.valueOf(6l), bRepository.countAll());
		Assert.assertEquals(Long.valueOf(3l), cRepository.countAll());

		List<A> as = aRepository.findAll();

		for (A a : as) {
			aRepository.delete(a);
		}

		Assert.assertEquals(Long.valueOf(0l), bRepository.countAll());
		Assert.assertEquals(Long.valueOf(0l), cRepository.countAll());
	}

	private void buildAs() {
		a1 = new A();
		a1.setId(1l);
		a1.setDescription("a1");
		a2 = new A();
		a2.setId(2l);
		a2.setDescription("a2");
		a3 = new A();
		a3.setId(3l);
		a3.setDescription("a3");

		a1.setBs(new HashSet<>());
		a2.setBs(new HashSet<>());
		a3.setBs(new HashSet<>());
	}

	private void buildBs() {
		b1 = new B();
		b1.setId(1l);
		b1.setDescription("b1");
		b2 = new B();
		b2.setId(2l);
		b2.setDescription("b2");
		b3 = new B();
		b3.setId(3l);
		b3.setDescription("b3");
		b4 = new B();
		b4.setId(4l);
		b4.setDescription("b4");
		b5 = new B();
		b5.setId(5l);
		b5.setDescription("b5");
		b6 = new B();
		b6.setId(6l);
		b6.setDescription("b6");

		b1.setCs(new ArrayList<>());
		b2.setCs(new ArrayList<>());
		b3.setCs(new ArrayList<>());
		b4.setCs(new ArrayList<>());
		b5.setCs(new ArrayList<>());
		b6.setCs(new ArrayList<>());
	}

	private void buildCs() {
		c1 = new C();
		c1.setId(1l);
		c1.setDescription("c1");
		c2 = new C();
		c2.setId(2l);
		c2.setDescription("c2");
		c3 = new C();
		c3.setId(3l);
		c3.setDescription("c3");
	}

	private void setRelations() {
		a1.getBs().add(b1);
		a1.getBs().add(b2);
		a1.getBs().add(b3);

		b1.setParent(a1);
		b2.setParent(a1);
		b3.setParent(a1);

		a2.getBs().add(b4);
		a2.getBs().add(b5);
		b4.setParent(a2);
		b5.setParent(a2);
		a3.getBs().add(b6);
		b6.setParent(a3);

		b1.getCs().add(c1);
		b1.getCs().add(c2);
		b1.getCs().add(c3);
		b2.getCs().add(c1);
		b2.getCs().add(c2);
		b3.getCs().add(c1);
		b4.getCs().add(c2);
		b5.getCs().add(c3);
		b6.getCs().add(c1);
		b2.getCs().add(c3);
	}

	// SELECT*
	//
	// FROM A;
	//
	// SELECT*
	// FROM B;SELECT*
	// FROM B_C;SELECT*
	// FROM C;SELECT*
	// FROM B
	// LEFT JOIN
	// B_C ON B.id=
	// B_C.B_ID LEFT
	// JOIN C
	// ON C.id=
	// B_C.CS_ID
	// WHERE C.DESCRIPTION='c3'
	// ORDER BY B.ID;
	//
	// SELECT*
	// FROM A
	// WHERE A.
	//
	// ID IN (
	// SELECT b1.PARENT_ID FROM B b1 WHERE ((b1.ID,b1.PARENT_ID) IN (
	//
	// SELECT MAX(b2.ID),b2.PARENT_ID FROM B b2 GROUP BY b2.PARENT_ID)) AND 'b3'
	// )
	//
	// SELECT * FROM B
	//
	// b1 WHERE ((b1.ID,b1.PARENT_ID) IN (
	//
	// SELECT MAX(b2.ID),b2.PARENT_ID FROM B b2 GROUP BY b2.PARENT_ID)) AND
	// b1.DESCRIPTION = 'b3'
	//
	// SELECT * FROM B b1 WHERE
	// SELECT (MAX(b2.ID), b2.PARENT_ID) FROM B b2 GROUP BY b2.PARENT_ID
	//
	// SELECT *
	// FROM t1 WHERE (id,rev) IN
	// ( SELECT id, MAX(rev)
	// FROM t1
	// GROUP BY id
	// )
	//
	// SELECT MAX(ID) FROM B
	//
	// author a
	// JOIN book_author ba ON a.id = ba.authorId
	// JOIN books b ON b.id = ba.bookId
	//
	//
	// SELECT * FROM C;

}

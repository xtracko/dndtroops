package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.PersistenceSampleApplicationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class TroopDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
	public EntityManager em;

	@Autowired
	public TroopDao productDao;

}

package cz.muni.fi.pa165.dndtroops.service;

import cz.muni.fi.pa165.dndtroops.dao.AdministratorDao;
import cz.muni.fi.pa165.dndtroops.entities.Administrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Miroslav Macor
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public void createAdministrator(Administrator admin) {
        administratorDao.createAdministrator(admin);
    }

    @Override
    public void updateAdministrator(Administrator admin) {
        administratorDao.updateAdministrator(admin);
    }

    @Override
    public Administrator findAdministatorById(Long id) { return administratorDao.findAdministatorById(id); }

    @Override
    public Administrator findAdministratorByName(String name) { return administratorDao.findAdministratorByName(name);
    }

    @Override
    public List<Administrator> findAllAdministrators() {return administratorDao.findAllAdministrators();}

    @Override
    public void removeAdministrator(Administrator admin) {administratorDao.removeAdministrator(admin);}
}

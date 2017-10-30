package cz.muni.fi.pa165.dndtroops.dao;

import cz.muni.fi.pa165.dndtroops.entities.Administrator;

import java.util.List;

/**
 * Creted by Miroslav Macor
 */

public interface AdministratorDao {


    public void createAdministrator(Administrator admin);

    public void updateAdministrator(Administrator admin);

    public Administrator findAdministatorById(Long id);

    public Administrator findAdminByName(String name);

    public List<Administrator> findAllAdministrators();

    public void removeAdministrator(Administrator admin);

}

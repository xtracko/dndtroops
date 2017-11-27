package cz.muni.fi.pa165.dndtroops.facade;
import cz.muni.fi.pa165.dndtroops.dto.AdminDTO;
import cz.muni.fi.pa165.dndtroops.dto.CreateAdminDTO;

import java.util.List;


/**
 * @author Miroslav Macor
 */

public interface AdminFacade {

    /**
     * Creates Admin
     * @param admin a Admin creation data
     * @return fully initialized Admin
     */
    AdminDTO createAdministrator(CreateAdminDTO admin);

    /**
     * updated Admin
     * @param admin to be updated
     */
    void updateAdministrator(AdminDTO admin);

    /**
     * Returns Administrator with the id or null
     * @param id of Administrator
     * @return Administrator with sanme id
     */
    AdminDTO findAdministatorById(Long id);

    /**
     * Finds Administrator by name
     * @param name of Administrator
     * @return Administrator with same name or null
     */
    AdminDTO findAdministratorByName(String name);

    /**
     * Get all Administrators
     * @return all current Administrator
     */
    List<AdminDTO> findAllAdministrators();

    /**
     * If exist removes Admin
     * @param admin to be removed
     */
    void removeAdministrator(AdminDTO admin);

}
package cz.muni.fi.pa165.dndtroops.facade;

import cz.muni.fi.pa165.dndtroops.dto.AdminDTO;
import cz.muni.fi.pa165.dndtroops.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.dndtroops.entities.Administrator;
import cz.muni.fi.pa165.dndtroops.service.AdminService;
import cz.muni.fi.pa165.dndtroops.service.BeanMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author Miroslav Macor
 */

@Service
@Transactional
public class AdminFacadeImpl implements AdminFacade {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public AdminDTO createAdministrator(AdminDTO admin,String unencryptedPassword) {
        Administrator mapped = beanMappingService.mapTo(admin, Administrator.class);
        adminService.createAdministrator(mapped,unencryptedPassword);
        return beanMappingService.mapTo(mapped, AdminDTO.class);
    }
    @Override
    public void updateAdministrator(AdminDTO admin){
        adminService.updateAdministrator(beanMappingService.mapTo(admin, Administrator.class));
    }

    @Override
    public void removeAdministrator(AdminDTO admin){
        adminService.removeAdministrator(beanMappingService.mapTo(admin, Administrator.class));
    }

    @Override
    public List<AdminDTO> findAllAdministrators() {
        return beanMappingService.mapTo(adminService.findAllAdministrators(), AdminDTO.class);
    }

    @Override
    public AdminDTO findAdministatorById(Long id){
        return beanMappingService.mapTo(adminService.findAdministatorById(id), AdminDTO.class);
    }
    @Override
    public AdminDTO findAdministratorByName(String name){
        Administrator admin = adminService.findAdministratorByName(name); 
        return admin == null ? null : beanMappingService.mapTo(admin, AdminDTO.class);
    }

    @Override
    public boolean authenticate(UserAuthenticateDTO u) {
        return adminService.authenticate(
                adminService.findAdministratorByName(u.getName()), u.getPassword());
    }

    @Override
    public boolean isAdmin(AdminDTO admin) {
        return adminService.isAdmin(adminService.findAdministratorByName(admin.getName())  );
    }
}
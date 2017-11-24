package cz.muni.fi.pa165.dndtroops;

import cz.muni.fi.pa165.dndtroops.dto.RoleDTO;
import cz.muni.fi.pa165.dndtroops.entities.Role;
import cz.muni.fi.pa165.dndtroops.facade.RoleFacadeImpl;
import cz.muni.fi.pa165.dndtroops.service.RoleServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Jiří Novotný
 */

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={RoleServiceImpl.class, RoleFacadeImpl.class})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new EntityMapping());
        return dozer;
    }

    public class EntityMapping extends BeanMappingBuilder {
        @Override
        protected void configure() {
            // TODO: add your entity mapping here
        }
    }

}

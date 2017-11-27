package cz.muni.fi.pa165.dndtroops;

import cz.muni.fi.pa165.dndtroops.dto.*;
import cz.muni.fi.pa165.dndtroops.entities.Hero;
import cz.muni.fi.pa165.dndtroops.entities.Troop;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import cz.muni.fi.pa165.dndtroops.entities.Role;
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
@ComponentScan(basePackageClasses={})
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
            mapping(Role.class, RoleDTO.class);
            mapping(Role.class, CreateRoleDTO.class);

            mapping(Hero.class, HeroDTO.class);
            mapping(Hero.class, HeroCreateDTO.class);

            mapping(Troop.class, TroopDTO.class);
            mapping(Troop.class, TroopCreateDTO.class);
        }
    }

}

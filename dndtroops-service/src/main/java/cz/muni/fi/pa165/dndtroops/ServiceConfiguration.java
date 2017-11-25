package cz.muni.fi.pa165.dndtroops;

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
        }
    }

}

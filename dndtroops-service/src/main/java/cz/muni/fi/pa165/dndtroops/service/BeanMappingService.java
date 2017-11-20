package cz.muni.fi.pa165.dndtroops.service;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiří Novotný
 */

public interface BeanMappingService {

    <T> T mapTo(Object u, Class<T> mapToClass);

    <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);

    Mapper getMapper();

}

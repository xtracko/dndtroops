package cz.muni.fi.pa165.dndtroops.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Jiří Novotný
 */

@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    @Autowired
    private Mapper mapper;

    @Override
    public <T> T mapTo(Object object, Class<T> mapToClass) {
        return mapper.map(object, mapToClass);
    }

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(mapper.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }
}

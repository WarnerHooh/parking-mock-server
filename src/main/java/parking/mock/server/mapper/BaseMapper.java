package parking.mock.server.mapper;

import lombok.Getter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public abstract class BaseMapper {

    private final MapperFactory mapperFactory;

    public BaseMapper() {
        this.mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    public void register(Class<?> typeA, Class<?> typeB) {
        mapperFactory.classMap(typeA, typeB).byDefault().register();
    }

    public <S, D> D map(S sourceObject, D destinationObject) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        mapperFacade.map(sourceObject, destinationObject);
        return destinationObject;
    }

    public <S, D> D map(S sourceObject, Type<S> sourceType, Type<D> destinationType) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        return mapperFacade.map(sourceObject, sourceType, destinationType);
    }


    public <T> T mapLeft(Class<T> targetType, Object... objects) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        T target = mapperFacade.map(objects[0], targetType);
        return mapLeft(target, Arrays.stream(objects).skip(1));
    }

    public <T> T mapLeft(Type<T> targetType, Object... objects) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        final T target = mapperFacade.newObject(objects[0], targetType, new MappingContext.Factory().getContext());
        return mapLeft(target, Arrays.stream(objects));
    }

    private <T> T mapLeft(T target, Stream<Object> objects) {
        objects.filter(it -> !Objects.isNull(it)).forEachOrdered(source -> mapperFactory.getMapperFacade().map(source, target));
        return target;
    }


    public <T> T mapRight(Class<T> targetType, Object... objects) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        int lastIndex = objects.length - 1;
        T target = mapperFacade.map(objects[lastIndex], targetType);
        return mapRight(target, Arrays.stream(objects).limit(lastIndex));
    }

    public <T> T mapRight(Type<T> targetType, Object... objects) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        int lastIndex = objects.length - 1;
        final T target = mapperFacade.newObject(objects[lastIndex], targetType, new MappingContext.Factory().getContext());
        return mapRight(target, Arrays.stream(objects));
    }

    private <T> T mapRight(T target, Stream<Object> objects) {
        List<Object> lists = objects.filter(it -> !Objects.isNull(it)).collect(Collectors.toList());
        Collections.reverse(lists);
        lists.forEach(source -> mapperFactory.getMapperFacade().map(source, target));
        return target;
    }
}

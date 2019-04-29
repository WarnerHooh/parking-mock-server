package parking.mock.server.mapper;

import lombok.Getter;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;

import java.util.Arrays;
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

    public <T> T mapLeft(Class<T> targetType, Object... objects) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        T target = mapperFacade.map(objects[0], targetType);
        return mapLeft(target, Arrays.stream(objects).skip(1));
    }

    private <T> T mapLeft(T target, Stream<Object> objects) {
        objects.forEachOrdered(source -> mapperFactory.getMapperFacade().map(source, target));
        return target;
    }


    public <T> T mapLeft(Type<T> targetType, Object... objects) {
        MapperFacade mapperFacade = mapperFactory.getMapperFacade();
        final T t = mapperFacade.newObject(objects[0], targetType, new MappingContext.Factory().getContext());
        return t;
    }
}

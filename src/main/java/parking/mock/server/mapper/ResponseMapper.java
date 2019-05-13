package parking.mock.server.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.springframework.stereotype.Service;
import parking.mock.server.model.generic.Person;
import parking.mock.server.model.generic.PersonDto;
import parking.mock.server.model.group.APerson;
import parking.mock.server.model.group.BPerson;
import parking.mock.server.model.group.CPerson;
import parking.mock.server.model.group.Mate;

@Service
public class ResponseMapper extends BaseMapper {

    public ResponseMapper() {
        MapperFactory mapperFactory = getMapperFactory();

        mapperFactory.registerClassMap(
                mapperFactory.classMap(
                        new TypeBuilder<Person>() {}.build(),
                        new TypeBuilder<PersonDto>() {}.build()
                )
                        .byDefault().toClassMap()
        );



        mapperFactory.registerClassMap(
                mapperFactory.classMap(
                        new TypeBuilder<Mate>() {}.build(),
                        new TypeBuilder<APerson>() {}.build()
                )
                        .field("apName", "name")
                        .field("apGender", "gender")
                        .byDefault().toClassMap());

        mapperFactory.registerClassMap(
                mapperFactory.classMap(
                        new TypeBuilder<Mate>() {}.build(),
                        new TypeBuilder<BPerson>() {}.build()
                )
                        .field("bpName", "name")
                        .field("bpGender", "gender")
                        .byDefault().toClassMap());

        mapperFactory.registerClassMap(
                mapperFactory.classMap(
                        new TypeBuilder<Mate>() {}.build(),
                        new TypeBuilder<CPerson>() {}.build()
                )
                        .field("cpName", "name")
                        .field("cpGender", "gender")
                        .byDefault().toClassMap());
    }
}

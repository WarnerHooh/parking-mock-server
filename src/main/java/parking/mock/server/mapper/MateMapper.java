package parking.mock.server.mapper;

import org.springframework.stereotype.Service;
import parking.mock.server.mapper.BaseMapper;
import parking.mock.server.model.group.*;

@Service
public class MateMapper extends BaseMapper {
    public MateMapper() {
        getMapperFactory().classMap(Mate.class, Person.class)
                .byDefault()
                .register();

        getMapperFactory().classMap(Mate.class, APerson.class)
                .field("apName", "name")
                .field("apGender", "gender")
                .byDefault()
                .register();
        getMapperFactory().classMap(Mate.class, BPerson.class)
                .field("bpName", "name")
                .field("bpGender", "gender")
                .byDefault()
                .register();
        getMapperFactory().classMap(Mate.class, CPerson.class)
                .field("cpName", "name")
                .field("cpGender", "gender")
                .byDefault()
                .register();
    }

    public Mate map(Person aPerson) {
        Mate mate = new Mate();
        return map(aPerson, mate);
    }

    public Mate mapByLeft(APerson aPerson, BPerson bPerson, CPerson cPerson) {
        return mapLeft(Mate.class, aPerson, bPerson, cPerson);
    }

    public Mate mapByRight(APerson aPerson, BPerson bPerson, CPerson cPerson) {
        return mapRight(Mate.class, aPerson, bPerson, cPerson);
    }
}

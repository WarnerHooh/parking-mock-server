package parking.mock.server.model.group;

import org.junit.Before;
import org.junit.Test;
import parking.mock.server.mapper.MateMapper;

import static org.assertj.core.api.Assertions.assertThat;

public class MateMapperTest {

    private MateMapper mateMapper;

    @Before
    public void setup() {
        mateMapper = new MateMapper();
    }


    @Test
    public void should_map_from_person_to_mate() {
        // given
        Person person = new Person();
        person.setName("name");
        person.setGender("gender");
        person.setAge(11);


        // when
        Mate mate = mateMapper.map(person);

        // then
        assertThat(mate.getName()).isEqualTo("name");
        assertThat(mate.getGender()).isEqualTo("gender");
        assertThat(mate.getAge()).isEqualTo(11);
    }

    @Test
    public void should_map_with_multiple_fields_by_left() {
        // given
        APerson aPerson = new APerson();
        aPerson.setName("aname");
        aPerson.setGender("agender");
        aPerson.setAge(10);

        BPerson bPerson = new BPerson();
        bPerson.setName("bname");
        bPerson.setGender("bgender");
        bPerson.setAge(20);

        CPerson cPerson = new CPerson();
        cPerson.setName("cname");
        cPerson.setGender("cgender");
        cPerson.setAge(30);

        // when
        Mate mate = mateMapper.mapByLeft(aPerson, bPerson, cPerson);

        // then
        assertThat(mate.getApName()).isEqualTo("aname");
        assertThat(mate.getApGender()).isEqualTo("agender");

        assertThat(mate.getBpName()).isEqualTo("bname");
        assertThat(mate.getBpGender()).isEqualTo("bgender");

        assertThat(mate.getCpName()).isEqualTo("cname");
        assertThat(mate.getCpGender()).isEqualTo("cgender");

        assertThat(mate.getAge()).isEqualTo(30);
    }

    @Test
    public void should_map_with_multiple_fields_by_right() {
        // given
        APerson aPerson = new APerson();
        aPerson.setName("aname");
        aPerson.setGender("agender");
        aPerson.setAge(10);

        BPerson bPerson = new BPerson();
        bPerson.setName("bname");
        bPerson.setGender("bgender");
        bPerson.setAge(20);

        CPerson cPerson = new CPerson();
        cPerson.setName("cname");
        cPerson.setGender("cgender");
        cPerson.setAge(30);

        // when
        Mate mate = mateMapper.mapByRight(aPerson, bPerson, cPerson);

        // then
        assertThat(mate.getApName()).isEqualTo("aname");
        assertThat(mate.getApGender()).isEqualTo("agender");

        assertThat(mate.getBpName()).isEqualTo("bname");
        assertThat(mate.getBpGender()).isEqualTo("bgender");

        assertThat(mate.getCpName()).isEqualTo("cname");
        assertThat(mate.getCpGender()).isEqualTo("cgender");

        assertThat(mate.getAge()).isEqualTo(10);
    }

}

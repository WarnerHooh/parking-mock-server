package parking.mock.server.model.generic;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.junit.Ignore;
import org.junit.Test;
import parking.mock.server.mapper.ResponseMapper;
import parking.mock.server.model.group.APerson;
import parking.mock.server.model.group.BPerson;
import parking.mock.server.model.group.CPerson;
import parking.mock.server.model.group.Mate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;


public class ResponseMapperTest {

    @Test
    public void should_map_simple_generic() {
        Response<String> source = new Response<>("source");

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        ResponseDto result = mapperFactory.getMapperFacade().map(source, ResponseDto.class);

        assertEquals("source", result.getData());
    }

    @Test
    @Ignore("This won't work")
    public void genericTest2(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        Response<Person> response = new Response<>();
        Person person = new Person();
        person.setName("test generic");
        response.setData(person);

        Response<PersonDto> responseDto = mapperFactory.getMapperFacade().map(response, Response.class);

        assertTrue(responseDto.getData() instanceof PersonDto);
        assertThat(responseDto.getData().getName()).isEqualTo("test generic");
    }

    @Test
    @Ignore("The won't work, compare with the next one")
    public void genericTest3(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        Response<Person> response = new Response<>();
        Person person = new Person();
        person.setName("test generic");
        response.setData(person);

        Type<Response<Person>> personResponse = new TypeBuilder<Response<Person>>() {}.build();
        Type<Response<PersonDto>> personDtoResponse = new TypeBuilder<Response<PersonDto>>() {}.build();

        Response<PersonDto> responseDto = mapperFactory.getMapperFacade().map(response, personResponse, personDtoResponse);

        assertTrue(responseDto.getData() instanceof PersonDto);
    }

    @Test
    public void should_map_with_generic_response() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        Response<Person> response = new Response<Person>();
        Person person = new Person();
        person.setName("test generic");
        response.setData(person);


        Type<Response<Person>> fromType = new TypeBuilder<Response<Person>>() {}.build();
        Type<Response<PersonDto>> toType = new TypeBuilder<Response<PersonDto>>() {}.build();

        mapperFactory.registerClassMap(
                mapperFactory.classMap(new TypeBuilder<Person>() {}.build(),
                        new TypeBuilder<PersonDto>() {}.build()).byDefault().toClassMap());

        Response<PersonDto> responseDto = mapperFactory.getMapperFacade().map(response, fromType, toType);


        assertTrue(responseDto.getData() instanceof PersonDto);
        assertEquals("test generic" , responseDto.getData().getName());
    }

    @Test
    public void should_same_with_previous_one() {
        Response<Person> response = new Response<Person>();
        Person person = new Person();
        person.setName("test generic");
        response.setData(person);


        Type<Response<Person>> fromType = new TypeBuilder<Response<Person>>() {}.build();
        Type<Response<PersonDto>> toType = new TypeBuilder<Response<PersonDto>>() {}.build();

        // with extracted ResponseMapper
        Response<PersonDto> responseDto = new ResponseMapper().map(response, fromType, toType);


        assertTrue(responseDto.getData() instanceof PersonDto);
        assertEquals("test generic" , responseDto.getData().getName());
    }

    @Test
    public void should_map_generic_with_source_object_and_destination_object() {
        Response<Person> response = new Response<Person>();
        Person person = new Person();
        person.setName("test generic");
        response.setData(person);


        Response<PersonDto> personDtoResponse = new Response<>();
        Response<PersonDto> responseDto = new ResponseMapper().map(response, personDtoResponse);


        assertTrue(responseDto.getData() instanceof PersonDto);
        assertEquals("test generic" , responseDto.getData().getName());
    }

    @Test
    public void should_map_multiple_by_left() {
        // given
        APerson aPerson = new APerson();
        aPerson.setAge(11);
        aPerson.setName("aname");
        aPerson.setGender("agender");
        Response<APerson> response1 = new Response<>(aPerson);

        BPerson bPerson = new BPerson();
        bPerson.setAge(12);
        bPerson.setName("bname");
        bPerson.setGender("bgender");
        Response<BPerson> response2 = new Response<>(bPerson);

        CPerson cPerson = new CPerson();
        cPerson.setAge(13);
        cPerson.setName("cname");
        cPerson.setGender("cgender");
        Response<CPerson> response3 = new Response<>(cPerson);

        Type<Response<CPerson>> fromType = new TypeBuilder<Response<CPerson>>() {}.build();
        Type<Response<Mate>> destType = new TypeBuilder<Response<Mate>>() {}.build();

        // when
        Response<Mate> mateResponse = new ResponseMapper().mapLeft(destType, response1, response2, response3);

        // then
        Mate mate = mateResponse.getData();

        System.out.println(mate);

        assertEquals("aname", mate.getApName());
        assertEquals("agender", mate.getApGender());

        assertEquals("bname", mate.getBpName());
        assertEquals("bgender", mate.getBpGender());

        assertEquals("cname", mate.getCpName());
        assertEquals("cgender", mate.getCpGender());

        assertEquals(13, mate.getAge());
        assertNull(mate.getName());
        assertNull(mate.getGender());
    }

    @Test
    public void should_map_multiple_by_right() {
        // given
        APerson aPerson = new APerson();
        aPerson.setAge(11);
        aPerson.setName("aname");
        aPerson.setGender("agender");
        Response<APerson> response1 = new Response<>(aPerson);

        BPerson bPerson = new BPerson();
        bPerson.setAge(12);
        bPerson.setName("bname");
        bPerson.setGender("bgender");
        Response<BPerson> response2 = new Response<>(bPerson);

        CPerson cPerson = new CPerson();
        cPerson.setAge(13);
        cPerson.setName("cname");
        cPerson.setGender("cgender");
        Response<CPerson> response3 = new Response<>(cPerson);

        Type<Response<CPerson>> fromType = new TypeBuilder<Response<CPerson>>() {}.build();
        Type<Response<Mate>> destType = new TypeBuilder<Response<Mate>>() {}.build();

        // when
        Response<Mate> mateResponse = new ResponseMapper().mapRight(destType, response1, response2, response3);

        // then
        Mate mate = mateResponse.getData();

        System.out.println(mate);

        assertEquals("aname", mate.getApName());
        assertEquals("agender", mate.getApGender());

        assertEquals("bname", mate.getBpName());
        assertEquals("bgender", mate.getBpGender());

        assertEquals("cname", mate.getCpName());
        assertEquals("cgender", mate.getCpGender());

        assertEquals(11, mate.getAge());
        assertNull(mate.getName());
        assertNull(mate.getGender());
    }
}

package parking.mock.server.model.generic;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class ResponseTest {

    @Test
    public void should_map() {
        Response<String> source = new Response<>("source");

        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        ResponseDto result = mapperFactory.getMapperFacade().map(source, ResponseDto.class);

        assertEquals("source", result.getData());
    }

    @Test
    public void genericTest2(){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        Response<Person> response = new Response<>();
        Person person = new Person();
        person.setName("test generic");
        response.setData(person);
        Response<PersonDto> responseDto = mapperFactory.getMapperFacade().map(response, Response.class);
        assertFalse(responseDto.getData() instanceof PersonDto);
    }

    @Test
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
//        assertTrue(responseDto.getData() instanceof PersonDto);
    }

    @Test
    public void genericTest4() {
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
        assertEquals("test generic" , responseDto.getData().getName());
    }
}

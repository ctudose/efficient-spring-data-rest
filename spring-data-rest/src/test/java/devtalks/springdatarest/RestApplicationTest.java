package devtalks.springdatarest;

import com.fasterxml.jackson.databind.ObjectMapper;
import devtalks.springdatarest.beans.CsvDataLoader;
import devtalks.springdatarest.exceptions.PersonNotFoundException;
import devtalks.springdatarest.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
@Import(CsvDataLoader.class)
public class RestApplicationTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private Room room;

    @Autowired
    private Map<String, Country> countriesMap;

    @MockBean
    private PersonRepository personRepository;

    @MockBean
    private CountryRepository countryRepository;

    @Test
    void testGetAllCountries() throws Exception {
        when(countryRepository.findAll()).thenReturn(new ArrayList<>(countriesMap.values()));
        mvc.perform(get("/countries"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)));

        verify(countryRepository, times(1)).findAll();
    }

    @Test
    void testGetAllPersons() throws Exception {
        when(personRepository.findAll()).thenReturn(new ArrayList<>(room.getPersons()));

        mvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(20)));

        verify(personRepository, times(1)).findAll();
    }

    @Test
    void testPersonNotFound() {
        Throwable throwable = assertThrows(NestedServletException.class, () -> mvc.perform(get("/persons/30")).andExpect(status().isNotFound()));
        assertEquals(PersonNotFoundException.class, throwable.getCause().getClass());
    }

    @Test
    void testPostPerson() throws Exception {

        Person person = new Person("Peter Michelsen");
        person.setCountry(countriesMap.get("US"));
        person.setIsRegistered(false);
        when(personRepository.save(person)).thenReturn(person);

        mvc.perform(post("/persons")
                .content(new ObjectMapper().writeValueAsString(person))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Peter Michelsen")))
                .andExpect(jsonPath("$.country.codeName", is("US")))
                .andExpect(jsonPath("$.country.name", is("USA")))
                .andExpect(jsonPath("$.registered", is(Boolean.FALSE)));

        verify(personRepository, times(1)).save(person);

    }

    @Test
    void testPatchPerson() throws Exception {
        Person person = new Person("Sophia Graham");
        person.setCountry(countriesMap.get("UK"));
        person.setIsRegistered(false);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));
        when(personRepository.save(person)).thenReturn(person);
        String updates =
                "{\"name\":\"Sophia Jones\", \"country\":\"AU\", \"isRegistered\":\"true\"}";

        mvc.perform(patch("/persons/1")
                .content(updates)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(personRepository, times(1)).findById(1L);
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void testDeletePerson() throws Exception {

        mvc.perform(delete("/persons/4"))
                .andExpect(status().isOk());

        verify(personRepository, times(1)).deleteById(4L);
    }
}

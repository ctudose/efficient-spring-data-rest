package devtalks.springdatarest.controllers.assemblers;

import devtalks.springdatarest.controllers.HateoasPersonController;
import devtalks.springdatarest.model.Person;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PersonModelAssembler implements RepresentationModelAssembler<Person, EntityModel<Person>> {

    @Override
    public EntityModel<Person> toModel(Person person) {
        return EntityModel.of(person,
                WebMvcLinkBuilder.linkTo(methodOn(HateoasPersonController.class).findById(person.getId())).withSelfRel(),
                linkTo(methodOn(HateoasPersonController.class).findAll()).withRel("persons")
        );
    }

    @Override
    public CollectionModel<EntityModel<Person>> toCollectionModel(Iterable<? extends Person> persons) {

        List<EntityModel<Person>> result =
                StreamSupport.stream(persons.spliterator(), false)
                        .map(this::toModel)
                        .collect(Collectors.toList());

        return CollectionModel.of(result,
                linkTo(methodOn(HateoasPersonController.class).findAll()).withSelfRel());

    }
}

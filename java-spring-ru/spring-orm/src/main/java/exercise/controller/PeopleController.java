package exercise.controller;

import exercise.model.Person;
import exercise.dto.PersonDto;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
@RequestMapping("/people")
public class PeopleController {

    // Автоматически заполняем значение поля
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return personRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public void createPerson(@RequestBody Person person) {
        // добавляем новую сущность в базу
        this.personRepository.save(person);
    }
    @DeleteMapping(path = "/{id}")
    public void deletePerson(@PathVariable long id) {
        // удаляем сущность из базы по её id
        this.personRepository.deleteById(id);
    }
    @PatchMapping(path = "/{id}")
    public void patchPerson(@PathVariable long id, @RequestBody Person person) {

        Person personPatched = getPerson(id);
        personPatched.setFirstName(person.getFirstName());
        personPatched.setLastName(person.getLastName());

        this.personRepository.save(personPatched);
    }
    // END
}

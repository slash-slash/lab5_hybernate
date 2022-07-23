package dao.daoImpl;

import entity.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonDaoImplTest {

    PersonDaoImpl personDao = new PersonDaoImpl();

    @Test
    void findPersonById() {
        Person personById = personDao.findPersonById(1);
        System.out.println(personById);

    }
}
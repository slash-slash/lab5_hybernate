package dao.daoImpl;

import com.mysql.cj.jdbc.exceptions.SQLError;
import dao.CompanyDao;
import entity.Company;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CompanyDaoImplTest {
    private CompanyDao companyDao = new CompanyDaoImpl();
    private static Company company;

    @BeforeAll
    static void init() {
        company = new Company();
        String companyName = "Oil";
        company.setCompanyName(companyName);
        company.setCompanyCountry("US");
    }

    @Test
    @Order(1)
    void itShouldAddCompanyToDB() {
        companyDao.addCompany(company);
        Company expected = companyDao.findCompanyByName(company.getCompanyName());
        Assertions.assertEquals(expected, company);
    }

    @Test
    @Order(2)
    void itShouldThrowException() {
        Assertions.assertThrows(ConstraintViolationException.class, () -> companyDao.addCompany(company));
    }

    @Test
    @Order(3)
    void findCompanyById() {
        int id = company.getCompanyId();
        assertEquals(companyDao.findCompanyById(id), company);
    }

    @Test
    @Order(4)
    void findCompanyByName() {
        String name = company.getCompanyName();
        assertEquals(companyDao.findCompanyByName(name), company);
    }

    @Test
    @Order(5)
    void showCompanies() {
        List<Company> companies = companyDao.showCompanies();
        assertEquals(companies.get(0), company);
    }

    @Test
    @Order(6)
    void updateCompany() {
        int id = company.getCompanyId();
        final Company companyFromDB = companyDao.findCompanyById(id);
        companyFromDB.setCompanyName("new name");
        companyDao.updateCompany(companyFromDB);
        assertNotEquals(companyFromDB.getCompanyName(), company.getCompanyName());
    }

    @Test
    @Order(7)
    void itShouldDeleteCompany() {
        int id = company.getCompanyId();
        assertTrue(companyDao.deleteCompany(id));
    }
}
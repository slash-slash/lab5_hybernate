package service.serviceImpl;

import dao.CompanyDao;
import dao.daoImpl.CompanyDaoImpl;
import entity.Company;
import exception.ShowException;
import org.hibernate.HibernateError;
import service.CompanyService;

import java.util.List;

public class CompanyServiceImpl implements CompanyService {

    CompanyDao companyDao = new CompanyDaoImpl();

    public CompanyServiceImpl() {}

    @Override
    public boolean addCompany(Company company) {
        boolean isAdded = false;
        try {
            companyDao.addCompany(company);
            isAdded = true;
        }
        catch (HibernateError e) {
            ShowException.showNotice(e);
        }
        return isAdded;
    }

    @Override
    public boolean updateCompany(Company company) {
        return companyDao.updateCompany(company);
    }

    @Override
    public boolean deleteCompany(int id) {
        return companyDao.deleteCompany(id);
    }

    @Override
    public List<Company> showCompanies() {
        return companyDao.showCompanies();
    }

    @Override
    public Company findCompanyById(int id) {

        return companyDao.findCompanyById(id);
    }

    @Override
    public Company findCompanyByName(String name) {
        return companyDao.findCompanyByName(name);
    }
}
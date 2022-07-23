package dao.daoImpl;

import dao.CompanyDao;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sessionFactory.SessionFactoryImpl;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {
    public CompanyDaoImpl() {}

    @Override
    public boolean addCompany(Company company) {
        boolean isAdded = false;
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            session.save(company);
            tx.commit();
            session.close();
            isAdded = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        finally {
            session.close();
        }
        return isAdded;
    }

    @Override
    public boolean updateCompany(Company company) {
        boolean isUpdated = false;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            session.update(company);
            tx.commit();
            session.close();
            isUpdated = true;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return isUpdated;
    }

    @Override
    public boolean deleteCompany(int id) {
        boolean isDeleted = false;
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        try {
            Transaction tx = session.beginTransaction();
            Company company = session.load(Company.class, id);
            session.delete(company);
            tx.commit();
            session.close();
            isDeleted = company != null;
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        } finally {
            session.close();
        }
        return isDeleted;
    }

    @Override
    public Company findCompanyById(int id) {
        Company company = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.equal(root.get("companyId"), id));
            company = session.createQuery(cr).getSingleResult();
            tx.commit();
            session.close();
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return company;
    }

    @Override
    public Company findCompanyByName(String name) {
        Company company = null;
        try {
            Session session = SessionFactoryImpl.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Company> cr = cb.createQuery(Company.class);
            Root<Company> root = cr.from(Company.class);
            cr.select(root).where(cb.equal(root.get("companyName"), name));
            company = session.createQuery(cr).getSingleResult();
            tx.commit();
            session.close();
        }
        catch (NoClassDefFoundError e) {
            System.out.println("Exception: " + e);
        }
        return company;
    }

    @Override
    public List<Company> showCompanies() {
        List<Company> companies = (List<Company>)SessionFactoryImpl.getSessionFactory().openSession().createQuery("From Company").list();
        return companies;
    }
}
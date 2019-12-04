package demo;

import demo.dto.AwesomePeopleCount;
import demo.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("PersonPU");
        EntityManager em = factory.createEntityManager();

        // Insert Persons
        insertPerson(em);
        insertAwesomePerson(em);
        insertAddress(em);
        insertOrders(em);
        // fetch Data
        Query query = em.createQuery("select p from Person p");
        List<Person> result = query.getResultList();

        Query awesomeCities = em.createQuery("select distinct a.city from Person p join p.addresses a where p.isAwesome = true order by a.city asc");
        List<String> res1 = awesomeCities.getResultList();

        Query awesomePeoples = em.createQuery("select new demo.dto.AwesomePeopleCount(p.isAwesome, count(p.SSN)) from Person p group by p.isAwesome order by p.isAwesome desc");
        List<AwesomePeopleCount> res2 = awesomePeoples.getResultList();

        Query orderSummaries = em.createQuery("select p.firstName from Person p join p.addresses a join Order o on o.address.id.person p");
        // join Order o on a.id.id = o.address.id.id join OrderItem oi join Product p
        System.out.println(orderSummaries.getSingleResult());

        em.close();
        factory.close();

        // print Data
        System.out.println("\u001B[34m");

        System.out.println("Persons:");
        for (Person p : result) {
            System.out.println(p.getSSN() + ": " + p.getFirstName() + " " + p.getLastName());
        }
        System.out.println();

        System.out.println("Awesome Cities:");
        for (String c : res1) {
            System.out.println("\t--> " + c);
        }
        System.out.println();

        System.out.println("Awesomeness Count:");
        System.out.println("In total there are " + res2.get(0).getCount() + " awesome and " + res2.get(1).getCount() + " not awesome people");
        System.out.println("\u001B[0m");

    }

    private static void insertPerson(EntityManager em) {
        em.getTransaction().begin();
        Person newPerson = new Person();
        newPerson.setSSN("5555050670");
        newPerson.setFirstName("Java");
        newPerson.setLastName("Student");
        newPerson.setDateOfBirth(Date.from(LocalDate.of(1970, 6, 5).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        newPerson.setAwesome(false);
        newPerson.setAwesomeness(-8.12);

        em.persist(newPerson);
        em.getTransaction().commit();
    }

    private static void insertAwesomePerson(EntityManager em) {
        em.getTransaction().begin();

        Person newPerson = new Person();
        newPerson.setSSN("314159");
        newPerson.setFirstName("David");
        newPerson.setLastName("Getter");
        newPerson.setDateOfBirth(Date.from(LocalDate.of(1967, 11, 7).atStartOfDay(ZoneId.systemDefault()).toInstant()));
        newPerson.setAwesome(true);
        newPerson.setAwesomeness(10);
        newPerson.addAddress("Paris", "France", "Getterstreet", 1);
        em.persist(newPerson);
        em.getTransaction().commit();
    }

    private static void insertAddress(EntityManager em) {
        // fetch Java Student
        em.getTransaction().begin();
        Query query = em.createQuery("select p from Person p where p.firstName = 'Java'");
        Person javaStudent = (Person) query.getResultList().get(0);
        // persist student
        Address studentAddress = new Address();
        studentAddress.setAssociatedPerson(javaStudent);
        studentAddress.setCity("Vienna");
        studentAddress.setCountry("Austria");
        studentAddress.setStreet("Hauptstraße");
        studentAddress.setStreetNumber(5);
        em.persist(studentAddress);
        em.getTransaction().commit();
    }

    private static void insertOrders(EntityManager em) {
        Query query = em.createQuery("select a from demo.entities.Address a where a.street like 'Getterstreet'");
        Address address = (Address) query.getSingleResult();

        // Insert orders to set their state to make them "Managed" objects
        // Need to be persisted before order because id would not be generated.
        em.getTransaction().begin();
        Product product1 = new Product("Coffe", 2.0);
        Product product2 = new Product("Tea", .75);

        em.persist(product1);
        em.persist(product2);
        em.getTransaction().commit();

        // Create order, add products and persist
        em.getTransaction().begin();
        Order order = new Order(address, LocalDate.now(), 0);
        order.addProduct(product1, 999);
        order.addProduct(product2, 3);
        em.persist(order);
        em.getTransaction().commit();

    }
}

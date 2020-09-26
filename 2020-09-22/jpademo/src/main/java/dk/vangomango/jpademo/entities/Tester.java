/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.vangomango.jpademo.entities;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Oliver
 */
public class Tester {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        
        Person p1 = new Person("John", 1963);
        Person p2 = new Person("Klok", 1990);
        
        Address a1 = new Address("Store tov 2", 3000, "Helsingør");
        Address a2 = new Address("MangeGade 6", 3400, "Hillerød");
        
        p1.setAddress(a1);
        p2.setAddress(a2);
        
        Fee f1 = new Fee(100);
        Fee f2 = new Fee(200);
        Fee f3 = new Fee(300);
        
        p1.addFee(f1);
        p1.addFee(f2);
        p2.addFee(f3);
        
        SwimStyle s1 = new SwimStyle("Crawl");
        SwimStyle s2 = new SwimStyle("ButterFly");
        SwimStyle s3 = new SwimStyle("Breast Stroke");
        
        p1.addSwinStyle(s1);
        p1.addSwinStyle(s3);
        p2.addSwinStyle(s2);
        
        
        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
        
        
        em.getTransaction().begin();
        p1.removeSwimStyle(s3);
        em.getTransaction().commit();
        
        System.out.println("p1: " + p1.getP_id() + ", " +  p1.getName() + ", " +  p1.getAddress().getCity());
        System.out.println("p2: " + p2.getP_id() + ", " +  p2.getName() + ", " +  p2.getAddress().getCity());
        
        System.out.println("To-vejs: " + a1.getPerson().getName());
        
        
        System.out.println("Betalt f2: " + f2.getPerson().getName());
        
        System.out.println("Hvad er der betalt: ");
        
        TypedQuery<Fee> q1 = em.createQuery("SELECT f from Fee f", Fee.class);
        List<Fee> fees = q1.getResultList();
        for(Fee f: fees){
            System.out.println(f.getPerson().getName() + ": " + f.getAmount() + " kr. - " + f.getPayDate());
            
            System.out.println("----SwimStyles");
        }
        
        
        TypedQuery<Person> q2 = em.createQuery("SELECT p from Person p", Person.class);
        List<Person> persons = q2.getResultList();
        for(Person p: persons){
            
        }
    }
}

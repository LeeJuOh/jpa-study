package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("m1");
            em.persist(member1);
            Member member2 = new Member();
            member2.setUsername("m2");
            em.persist(member2);
            em.flush();
            em.clear();

            Member m1 = em.find(Member.class, member1.getId());
            Member m2 = em.find(Member.class, member2.getId());
            System.out.println(m1.getClass());
            System.out.println(m2.getClass());
            System.out.println(m1.getClass() == m2.getClass());
            System.out.println("=========");
            em.clear();
            Member proxyM1 = em.getReference(Member.class, member1.getId());
            Member proxyM2 = em.getReference(Member.class, member2.getId());
            System.out.println(proxyM1.getClass());
            System.out.println(proxyM2.getClass());
            System.out.println(proxyM1.getClass() == proxyM2.getClass());
            System.out.println(proxyM1 instanceof Member);
            System.out.println(proxyM2 instanceof Member);
            tx.commit();
        } catch (Exception exception) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}

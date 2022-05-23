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
            System.out.println("=========");
            Member refMember = em.getReference(Member.class, member1.getId());
            System.out.println("refMember : " + refMember.getClass());
            Member findMember = em.getReference(Member.class, member1.getId());
            System.out.println("findMember : " + findMember.getClass());
            System.out.println(refMember == findMember);

            tx.commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

}

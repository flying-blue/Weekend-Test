package com.fishpro.demo.jpa.dao;

import com.fishpro.demo.jpa.domain.Person;
import com.fishpro.demo.jpa.domain.UserDO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @ClassName : PersonRepository
 * @Author : Administrator
 * @Date: 2021/1/2 20:45
 * @Description :
 */
@Transactional
@Repository
public class PersonRepository {

    @PersistenceContext
    private EntityManager em;

    public void findByPersonId(){
        String sql  = "select * from Person e where e.person_id = '0' ";
        em.createNativeQuery(sql).getResultList();
    }

    @Modifying
    public void test(){
        //prepareData();
        List<UserDO> l = em.createNativeQuery("SELECT e.id,e.user_name,e.create_time," +
                "e.last_login_time,e.password,e.sex FROM "+
                "t_user_x"+" e where e.sex = :sex",UserDO.class)
                .setParameter("sex",2).getResultList();
        for(UserDO p:l){
            System.out.println("----Person: "+p);
            em.createNativeQuery("update "+"t_user_x"+" u set u.password = :pwd where u.id =:id")
                    .setParameter("pwd","abcdefg456")
                    .setParameter("id",p.getId())
                    .executeUpdate();
        }
    }

    private void prepareData(){
        Person p = new Person();
        p.setPersonName("TOM2");
        p.setAddress("tom2_addr");
        p.setSex(2);
        em.persist(p);
    }

    @Modifying
    public void update(){
        List<Person> l = em.createQuery("SELECT e FROM Person e where e.sex =:sex")
                .setParameter("sex",2).getResultList();
        for(Person p:l){
            p.setAddress("TOM2");
//            em.persist(p);
        }
    }


    public void findByName(){
        List<Person> l = em.createQuery("SELECT e " +
                "FROM Person e " +
                "WHERE e.address = :dept AND " +
                "      e.sex = (SELECT MAX(e2.sex) " +
                "                  FROM Person e2 " +
                "                  WHERE e2.sex  = :sex)")
                .setParameter("dept", "Jery")
                .setParameter("sex", 2)
                .getResultList();
        for(Person p:l){
            System.out.println("----Person22: "+p);
        }
    }

    public void findUpdate(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        em.createQuery(
                "UPDATE Person p SET p.address = :add, p.personName = :name where p.sex = :sex ")
                .setParameter("add","Business_Jery")
                .setParameter("name","Jery")
                .setParameter("sex",1)
                .executeUpdate();
        System.out.println("******** Update sussess *********");
    }

    @Modifying
    public void findDelete(){
        List<Person> l = em.createQuery(    "SELECT e " +
                "FROM Person e " +
                "WHERE e.address = :dept AND " +
                "      e.sex = (SELECT MAX(e2.sex) " +
                "                  FROM Person e2 " +
                "                  WHERE e2.sex  = :sex)")
                .setParameter("dept", "Jery")
                .setParameter("sex", 2)
                .getResultList();
        for(Person e:l){
            System.out.println("*****--******DeletePerson:"+e);
            em.createQuery("delete from Person p where p.personId = :personId")
                    .setParameter("personId",e.getPersonId()).executeUpdate();
        }
    }

}

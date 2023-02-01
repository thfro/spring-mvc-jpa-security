package com.example.persistence.springcore;

import com.example.persistence.OrderEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void createOrder(OrderEntity entity) {
        em.persist(entity);
    }

    @Override
    public List<OrderEntity> getAllOrders() {
            return em.createQuery("SELECT o from OrderEntity o", OrderEntity.class).getResultList();
    }

    @Override
    public OrderEntity findOrderById(Integer id) {
        return em.createQuery("SELECT o FROM OrderEntity o WHERE o.id = :id", OrderEntity.class)
                 .setParameter("id", id)
                 .getSingleResult();
    }
}

package edu.wat.tim.lab1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import edu.wat.tim.lab1.model.CartProductsEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartProductsEntityRepository extends JpaRepository<CartProductsEntity, Long> {

    @Transactional
    void deleteByCartEntityIdAndProductEntityId(Long cartId, Long productId);

    CartProductsEntity findByCartEntityIdAndProductEntityId(Long cartId, Long productId);
}

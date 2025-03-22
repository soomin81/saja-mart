package soomin.park.sajamart.domain.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insert() {
        Product product = Product.builder()
                .name("테스트 상품")
                .description("테스트 상품입니다.")
                .price(1000d)
                .stockQuantity(4)
                .productStatus(ProductStatus.SELL)
                .build();

        productRepository.save(product);
    }

    @Test
    public void findAll() {

    }
}
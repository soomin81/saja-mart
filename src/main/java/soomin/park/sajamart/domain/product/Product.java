package soomin.park.sajamart.domain.product;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long productId; // 상품 ID (자동 증가)

    @Column(nullable = false)
    private String name; // 상품명

    @Column(nullable = false)
    private String description; // 상품 설명

    @Column(nullable = false)
    private Double price; // 상품 가격

    @Column(nullable = false)
    private Integer stockQuantity; // 재고 수량

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus; // 상품 상태

    @CreatedDate // 엔티티가 생성될 때 생성 시간 저장
    private LocalDateTime createAt; // 등록 일시

    @LastModifiedDate // 엔티티가 수정될 때 수정 시간 저장
    private LocalDateTime updateAt; // 수정 일시

    @Builder
    public Product(String name, String description, Double price, Integer stockQuantity, ProductStatus productStatus) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.productStatus = productStatus;
    }

    public void update(ProductRequest productRequest) {
        this.name = productRequest.getName();
        this.description = productRequest.getDescription();
        this.price = productRequest.getPrice();
        this.stockQuantity = productRequest.getStockQuantity();
        this.productStatus = productRequest.getProductStatus();
    }
}

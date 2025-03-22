package soomin.park.sajamart.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.IanaLinkRelations.SELF;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

    private final ProductService service;
    private final ProductModelAssembler assembler;

    // 상품 등록
    @PostMapping
    public ResponseEntity<EntityModel<Product>> createProduct(@RequestBody @Validated ProductRequest request) {

        var product = service.save(request);
        var entityModel = assembler.toModel(product);
        entityModel.add(Link.of("/docs/index.html#_상품_등록").withRel("profile"));

        return ResponseEntity
                .created(entityModel.getRequiredLink(SELF).toUri())
                .body(entityModel);
    }


    // 특정 상품 조회
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> getProductById(@PathVariable long id) {
        var product = service.findById(id);

        var entityModel = assembler.toModel(product);
        entityModel.add(Link.of("/docs/index.html").withRel("profile"));

        return ResponseEntity.ok(entityModel);
    }

    // 전체 상품 조회
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Product>>> getAllProducts(Pageable pageable,
                                                                           PagedResourcesAssembler<Product> assembler) {
        var page = service.findAll(pageable);
        var pagedResources = assembler.toModel(page);
        pagedResources.add(Link.of("/docs/index.html").withRel("profile"));

        return ResponseEntity.ok(pagedResources);
    }

    // 상품 수정
    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<Product>> updateProduct(@PathVariable long id, @RequestBody ProductRequest request) {
        var product = service.update(id, request);
        var entityModel = assembler.toModel(product);
        entityModel.add(Link.of("/docs/index.html").withRel("profile"));

        return ResponseEntity
                .created(entityModel.getRequiredLink(SELF).toUri())
                .body(entityModel);
    }

    // 상품 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        service.delete(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}

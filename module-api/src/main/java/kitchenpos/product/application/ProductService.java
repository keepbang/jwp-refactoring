package kitchenpos.product.application;

import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;
import kitchenpos.product.dto.ProductRequest;
import kitchenpos.product.dto.ProductResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductResponse create(final ProductRequest productRequest) {
        return ProductResponse.of(
                productRepository.save(productRequest.toProduct())
        );
    }

    public List<ProductResponse> list() {
        return ProductResponse.ofList(productRepository.findAll());
    }

    public List<Product> findAllByIdIn(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }
}

package am.mikan.mikan.mapper;

import am.mikan.mikan.dto.request.ProductCrudRequest;
import am.mikan.mikan.dto.response.ProductCrudResponse;
import am.mikan.mikan.entity.Product;
import am.mikan.mikan.mapper.base.BaseMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCrudMapper implements BaseMapper<Product, ProductCrudRequest, ProductCrudResponse> {

    private final ModelMapper modelMapper;

    @Override
    public Product toEntity(ProductCrudRequest productCrudRequest) {
        return modelMapper.map(productCrudRequest, Product.class);
    }

    @Override
    public ProductCrudResponse toResponse(Product product) {
        return modelMapper.map(product, ProductCrudResponse.class);
    }
}

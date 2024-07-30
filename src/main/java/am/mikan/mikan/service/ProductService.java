package am.mikan.mikan.service;

import am.mikan.mikan.dto.request.ProductCrudRequest;
import am.mikan.mikan.dto.response.ProductResponse;
import am.mikan.mikan.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    List<Product> findAllProduct();

    void save(ProductCrudRequest productRequest);

//    TODO kanchac che
    void update(int id, ProductCrudRequest productRequest);

    void deleteById(int id);

    List<ProductResponse> findAllByIdAndProductByLanguage(String language);

    byte[] getImage(String fileName);

    String saveImage(MultipartFile file) throws IOException;

    Product findById(int id);
}

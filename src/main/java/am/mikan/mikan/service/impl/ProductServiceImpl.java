package am.mikan.mikan.service.impl;

import am.mikan.mikan.Exception.EntityNotFoundException;
import am.mikan.mikan.dto.request.ProductCrudRequest;
import am.mikan.mikan.dto.response.ProductResponse;
import am.mikan.mikan.entity.Product;
import am.mikan.mikan.mapper.ProductCrudMapper;
import am.mikan.mikan.repository.ProductRepository;
import am.mikan.mikan.service.ProductService;
import am.mikan.mikan.util.IOUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCrudMapper productCrudMapper;
    private final IOUtil ioUtil;
    @Value("${project.images.product}")
    private String folderPath;

    @Override
    public List<Product> findAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public void save(ProductCrudRequest productRequest) {
        Product product = productCrudMapper.toEntity(productRequest);
        productRepository.save(product);
    }

    @Override
    public void update(int id, ProductCrudRequest productRequest) {
        boolean existsById = productRepository.existsById(id);
        if (existsById) {
            Product product = productCrudMapper.toEntity(productRequest);
            productRepository.save(product);
        }

    }

    @Override
    public void deleteById(int id) {
        productRepository.deleteById(id);
    }

//    @Cacheable(value = "productsByLanguage", key = "#language")
    @Override
    public List<ProductResponse> findAllByIdAndProductByLanguage(String language) {
        List<Product> productList = productRepository.findAll();
        return productList.stream().map(val -> productConvertToResponse(val, language.toLowerCase())).collect(Collectors.toList());
    }

//    @Cacheable(value = "images", key = "#fileName")
    @Override
    public byte[] getImage(String fileName) {
        return ioUtil.getAllBytesByUrl(folderPath + File.separator + fileName);
    }

    @Override
    public String saveImage(MultipartFile file) throws IOException {
        BufferedImage image = ImageIO.read(file.getInputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

// Compress the image
        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
//        3mb
        if (file.getSize() > 3145728) {
            param.setCompressionQuality(0.5f); // Quality of compression
        }
        else{
            param.setCompressionQuality(0.95f); // Quality of compression
        }

        writer.write(null, new IIOImage(image, null, null), param);
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
// Save the compressed image to a local file
        byte[] imageInByte = baos.toByteArray();
        FileOutputStream fos = new FileOutputStream(folderPath + File.separator + fileName);
        fos.write(imageInByte);
        fos.close();
        baos.close();
        return fileName;
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException("Product with id: " + id + " NOT FOUND");
        });
    }

    private ProductResponse productConvertToResponse(Product product, String language) {
        switch (language) {
            case "hy":
                return ProductResponse.builder()
                        .title(product.getTitleHy())
                        .description(product.getDescriptionHy())
                        .image(product.getImage())
                        .build();
            case "ru":
                return ProductResponse.builder()
                        .title(product.getTitleRu())
                        .description(product.getDescriptionRu())
                        .image(product.getImage())
                        .build();
            default:
                return ProductResponse.builder()
                        .title(product.getTitleUs())
                        .description(product.getDescriptionUs())
                        .image(product.getImage())
                        .build();
        }
    }
}

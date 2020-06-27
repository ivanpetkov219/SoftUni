package exam.services;

import exam.models.service.ProductServiceModel;
import exam.models.view.ProductViewModel;

import java.util.List;

public interface ProductService {

    void addProduct(ProductServiceModel productServiceModel);

    List<ProductViewModel> findAllItems();


    void delete(String id);

    void deleteAll(String id);

}

package parking.mock.server.mapper;

import org.springframework.stereotype.Service;
import parking.mock.server.model.Product;
import parking.mock.server.model.Shop;
import parking.mock.server.model.Trade;

@Service
public class ProductMapper extends BaseMapper {

    public ProductMapper() {
        getMapperFactory().classMap(Product.class, Trade.class)
                .field("name", "product")
                .byDefault()
                .register();
        getMapperFactory().classMap(Product.class, Shop.class)
                .byDefault()
                .register();
    }

    public Product map(Trade trade, Shop shop) {
        Product product = new Product();
        return mapLeft(Product.class, product, trade, shop);
    }
}

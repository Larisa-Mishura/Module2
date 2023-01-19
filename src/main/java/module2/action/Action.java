package module2.action;

import module2.repository.InvoiceRepository;
import module2.service.ShopService;

public interface Action {

    ShopService SHOP_SERVICE = ShopService.getInstance();

    void execute();
}

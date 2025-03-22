package soomin.park.sajamart.domain.product;

import soomin.park.sajamart.global.error.ErrorCode;
import soomin.park.sajamart.global.error.excpetion.NotFoundException;

public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException() {
        super(ErrorCode.PRODUCT_NOT_FOUND);
    }
}

package kitchenpos.order.dto;

import kitchenpos.order.domain.OrderLineItem;

public class OrderLineItemRequest {
    private Long menuId;
    private Long quantity;

    protected OrderLineItemRequest() {
    }

    public OrderLineItemRequest(Long menuId, Long quantity) {
        this.menuId = menuId;
        this.quantity = quantity;
    }

    public Long getMenuId() {
        return menuId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public OrderLineItem toOrderLineItem() {
        return new OrderLineItem(menuId, quantity);
    }
}

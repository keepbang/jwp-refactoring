package kitchenpos.table.dto;

import kitchenpos.table.domain.OrderTables;
import kitchenpos.table.domain.TableGroup;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TableGroupResponse {
    private Long id;
    private LocalDateTime createdDate;
    private List<OrderTableResponse> orderTables;

    protected TableGroupResponse() {
    }

    public TableGroupResponse(Long id, LocalDateTime createdDate, List<OrderTableResponse> orderTables) {
        this.id = id;
        this.createdDate = createdDate;
        this.orderTables = orderTables;
    }

    public static TableGroupResponse of(TableGroup tableGroup, OrderTables orderTables) {
        List<OrderTableResponse> orderTableResponses = orderTables.getOrderTables()
                .stream()
                .map(OrderTableResponse::of)
                .collect(Collectors.toList());
        return new TableGroupResponse(tableGroup.getId(), tableGroup.getCreatedDate(), orderTableResponses);
    }

    public Long getId() {
        return id;
    }

    public List<OrderTableResponse> getOrderTables() {
        return orderTables;
    }
}

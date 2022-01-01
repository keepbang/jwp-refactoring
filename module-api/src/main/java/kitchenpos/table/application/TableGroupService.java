package kitchenpos.table.application;

import kitchenpos.table.domain.OrderTableRepository;
import kitchenpos.table.domain.OrderTables;
import kitchenpos.table.domain.TableGroup;
import kitchenpos.table.domain.TableGroupRepository;
import kitchenpos.table.dto.TableGroupRequest;
import kitchenpos.table.dto.TableGroupResponse;
import kitchenpos.table.event.TableEventHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TableGroupService {
    private final OrderTableRepository orderTableRepository;
    private final TableGroupRepository tableGroupRepository;
    private final TableEventHandler tableEventHandler;

    public TableGroupService(
            final OrderTableRepository orderTableRepository,
            final TableGroupRepository tableGroupRepository,
            final TableEventHandler tableEventHandler) {
        this.orderTableRepository = orderTableRepository;
        this.tableGroupRepository = tableGroupRepository;
        this.tableEventHandler = tableEventHandler;
    }


    @Transactional
    public TableGroupResponse create(final TableGroupRequest tableGroupRequest) {
        final List<Long> orderTableIds = tableGroupRequest.getOrderTableIds();
        final OrderTables savedOrderTables = OrderTables.ofCreate(orderTableRepository.findAllByIdIn(orderTableIds));
        TableGroup savedTableGroup = tableGroupRepository.save(new TableGroup());
        savedOrderTables.initTableGroup(savedTableGroup.getId(), orderTableIds);
        return TableGroupResponse.of(savedTableGroup, savedOrderTables);
    }

    @Transactional
    public void ungroup(final Long tableGroupId) {
        tableEventHandler.ungrouped(tableGroupId);
        tableGroupRepository.deleteById(tableGroupId);
    }

    public int countById(final Long tableGroupId) {
        return tableGroupRepository.countById(tableGroupId);
    }
}

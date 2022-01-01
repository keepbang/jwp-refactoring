package kitchenpos.table.event;

import kitchenpos.table.application.TableService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class TableEventHandler{

    private final TableService tableService;

    public TableEventHandler(final TableService tableService) {
        this.tableService = tableService;
    }

    @EventListener
    public void ungrouped(Long tableGroupId) {
        tableService.ungroup(tableGroupId);
    }
}

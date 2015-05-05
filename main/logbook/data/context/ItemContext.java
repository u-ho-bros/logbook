package logbook.data.context;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import logbook.dto.ItemDto;

/**
 * 装備を管理します
 */
public class ItemContext {

    /** 装備Map */
    private static Map<Long, ItemDto> itemMap = new ConcurrentSkipListMap<Long, ItemDto>();

    /**
     * @return 装備Map
     */
    public static Map<Long, ItemDto> get() {
        return itemMap;
    }
}

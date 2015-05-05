package logbook.data.context;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

import logbook.dto.ShipDto;

/**
 * 艦娘を管理します
 */
public class ShipContext {

    /** 艦娘Map */
    private static Map<Long, ShipDto> shipMap = new ConcurrentSkipListMap<Long, ShipDto>();

    /**
     * @return 艦娘Map
     */
    public static Map<Long, ShipDto> get() {
        return shipMap;
    }
}

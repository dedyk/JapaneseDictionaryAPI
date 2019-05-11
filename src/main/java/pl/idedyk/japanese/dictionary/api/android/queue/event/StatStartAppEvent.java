package pl.idedyk.japanese.dictionary.api.android.queue.event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StatStartAppEvent extends QueueEventCommon {

    public StatStartAppEvent() {
        super();
    }
    
    public StatStartAppEvent(String uuid, Date createDate, Map<String, String> paramsMap) {    	
        super(uuid, createDate);
    }

    @Override
    public QueueEventOperation getQueryEventOperation() {
        return QueueEventOperation.STAT_START_APP_EVENT;
    }

    @Override
    public Map<String, String> getParams() {

        Map<String, String> result = new HashMap<>();

        return result;
    }
}

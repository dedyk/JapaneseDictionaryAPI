package pl.idedyk.japanese.dictionary.api.android.queue.event;

import java.util.Date;
import java.util.Map;

public class StatStartAppEvent extends QueueEventCommon {

    public StatStartAppEvent(String userId) {
        super(userId);
    }
    
    public StatStartAppEvent(Long id, String userId, Date createDate, Map<String, String> paramsMap) {    	
        super(id, userId, createDate, paramsMap);
    }

    @Override
    public QueueEventOperation getQueryEventOperation() {
        return QueueEventOperation.STAT_START_APP_EVENT;
    }

    @Override
    public Map<String, String> getParams() {

        Map<String, String> result = super.getParams();

        return result;
    }
}

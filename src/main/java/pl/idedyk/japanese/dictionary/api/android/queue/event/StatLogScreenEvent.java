package pl.idedyk.japanese.dictionary.api.android.queue.event;

import java.util.Date;
import java.util.Map;

public class StatLogScreenEvent extends QueueEventCommon {

    private String screenName;

    public StatLogScreenEvent(String userId, String screenName) {

        super(userId);

        this.screenName = screenName;
    }
    
    public StatLogScreenEvent(Long id, String userId, Date createDate, Map<String, String> paramsMap) {
    	
        super(id, userId, createDate, paramsMap);

        this.screenName = paramsMap.get("screenName");
    }

    @Override
    public QueueEventOperation getQueryEventOperation() {
        return QueueEventOperation.STAT_LOG_SCREEN_EVENT;
    }

    @Override
    public Map<String, String> getParams() {

    	Map<String, String> result = super.getParams();

        result.put("screenName", screenName);

        return result;
    }
}

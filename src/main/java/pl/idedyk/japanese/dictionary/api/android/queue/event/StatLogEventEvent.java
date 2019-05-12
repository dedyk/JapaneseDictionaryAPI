package pl.idedyk.japanese.dictionary.api.android.queue.event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class StatLogEventEvent extends QueueEventCommon {

    private String screenName;
    private String actionName;
    private String label;

    public StatLogEventEvent(String userId, String screenName, String actionName, String label) {

        super(userId);

        this.screenName = screenName;
        this.actionName = actionName;
        this.label = label;
    }

    public StatLogEventEvent(Long id, String userId, Date createDate, Map<String, String> paramsMap) {

        super(id, userId, createDate);

        this.screenName = paramsMap.get("screenName");
        this.actionName = paramsMap.get("actionName");
        this.label = paramsMap.get("label");
    }

    @Override
    public QueueEventOperation getQueryEventOperation() {
        return QueueEventOperation.STAT_LOG_EVENT_EVENT;
    }

    @Override
    public Map<String, String> getParams() {

        Map<String, String> result = new HashMap<>();

        result.put("screenName", screenName);
        result.put("actionName", actionName);
        result.put("label", label);

        return result;
    }
}
package pl.idedyk.japanese.dictionary.api.android.queue.event;

import java.util.Date;
import java.util.Map;

public interface IQueueEvent {

    public static String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public Long getId();
    public void setId(Long id);
    
    public String getUserId();

    public QueueEventOperation getQueryEventOperation();

    public Date getCreateDate();

    public String getCreateDateAsString();

    public Map<String, String> getParams();

    public String getParamsAsString();
}

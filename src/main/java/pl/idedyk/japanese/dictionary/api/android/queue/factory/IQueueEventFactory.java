package pl.idedyk.japanese.dictionary.api.android.queue.factory;

import pl.idedyk.japanese.dictionary.api.android.queue.event.IQueueEvent;
import pl.idedyk.japanese.dictionary.api.android.queue.event.QueueEventWrapper;

public interface IQueueEventFactory {
	
    public IQueueEvent createQueueEvent(Long id, String userId, String operation, String createDate, String params);
    
    public IQueueEvent createQueueEvent(QueueEventWrapper queueEventWrapper);
}

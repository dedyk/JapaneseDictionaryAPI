package pl.idedyk.japanese.dictionary.api.android.queue.factory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import pl.idedyk.japanese.dictionary.api.android.queue.event.IQueueEvent;
import pl.idedyk.japanese.dictionary.api.android.queue.event.QueueEventCommon;
import pl.idedyk.japanese.dictionary.api.android.queue.event.QueueEventOperation;
import pl.idedyk.japanese.dictionary.api.android.queue.event.QueueEventWrapper;
import pl.idedyk.japanese.dictionary.api.android.queue.event.StatLogEventEvent;
import pl.idedyk.japanese.dictionary.api.android.queue.event.StatLogScreenEvent;
import pl.idedyk.japanese.dictionary.api.android.queue.event.StatStartAppEvent;
import pl.idedyk.japanese.dictionary.api.android.queue.event.WordDictionaryMissingWordEvent;

public class QueueEventFactory implements IQueueEventFactory {

    @Override
    public IQueueEvent createQueueEvent(Long id, String userId, String operation, String createDateString, String params) {

        if (operation == null || createDateString == null || userId == null) {
            return null;
        }

        QueueEventOperation queueEventOperation = null;

        try {
            queueEventOperation = QueueEventOperation.valueOf(operation);

        } catch (IllegalArgumentException e) {
            return null;
        }
        
        //
        
        QueueEventWrapper queueEventWrapper = new QueueEventWrapper();
        
        queueEventWrapper.setId(id);
        queueEventWrapper.setUserId(userId);
        queueEventWrapper.setOperation(queueEventOperation);
        queueEventWrapper.setCreateDate(createDateString);
        queueEventWrapper.setParams(QueueEventCommon.getParamsFromString(params));

        return createQueueEvent(queueEventWrapper);
    }

	@Override
	public IQueueEvent createQueueEvent(QueueEventWrapper queueEventWrapper) {
		
		Long id = queueEventWrapper.getId();
		String userId = queueEventWrapper.getUserId();
		String createDateString = queueEventWrapper.getCreateDate();
		QueueEventOperation queueEventOperation = queueEventWrapper.getOperation();
		Map<String, String> params = queueEventWrapper.getParams();
		
        //
		
        SimpleDateFormat sdf = new SimpleDateFormat(IQueueEvent.dateFormat);

        Date createDateDate = null;

        try {
            createDateDate = sdf.parse(createDateString);

        } catch (ParseException e) {
            return null;
        }

        //

        IQueueEvent queueEvent = null;

        switch (queueEventOperation) {
        	
        	case STAT_START_APP_EVENT:
        		
        		queueEvent = new StatStartAppEvent(id, userId, createDateDate, params);
        		
        		break;

            case STAT_LOG_SCREEN_EVENT:

                queueEvent = new StatLogScreenEvent(id, userId, createDateDate, params);

                break;

            case STAT_LOG_EVENT_EVENT:

                queueEvent = new StatLogEventEvent(id, userId, createDateDate, params);

                break;

            case WORD_DICTIONARY_MISSING_WORD_EVENT:

                queueEvent = new WordDictionaryMissingWordEvent(id, userId, createDateDate, params);

                break;

            default:
                return null;
        }

        return queueEvent;
	}
}

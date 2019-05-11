package pl.idedyk.japanese.dictionary.api.android.queue.event;

import java.util.Map;

public class QueueEventWrapper {
	
	private String uuid;
	
	private QueueEventOperation operation;
	
	private String createDate;
	
	private Map<String, String> params;

	public String getUuid() {
		return uuid;
	}

	public QueueEventOperation getOperation() {
		return operation;
	}

	public String getCreateDate() {
		return createDate;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setOperation(QueueEventOperation operation) {
		this.operation = operation;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
}

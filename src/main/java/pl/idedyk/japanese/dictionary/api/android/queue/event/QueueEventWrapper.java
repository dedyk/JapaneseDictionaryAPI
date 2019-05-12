package pl.idedyk.japanese.dictionary.api.android.queue.event;

import java.util.Map;

public class QueueEventWrapper {
	
	private Long id;
	
	private String userId;
	
	private QueueEventOperation operation;
	
	private String createDate;
	
	private Map<String, String> params;
	
	public QueueEventWrapper() {
	}

	public QueueEventWrapper(Long id, String userId, QueueEventOperation operation, String createDate,
			Map<String, String> params) {
		
		this.id = id;
		this.userId = userId;
		this.operation = operation;
		this.createDate = createDate;
		this.params = params;
	}
	
	public Long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
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
	
	public void setOperation(QueueEventOperation operation) {
		this.operation = operation;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}

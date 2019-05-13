package pl.idedyk.japanese.dictionary.api.android.queue.event;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class QueueEventCommon implements IQueueEvent {

    //

    protected Long id;
    
    protected String userId;

    protected Date createDate;

    protected QueueEventCommon(String userId) {
    	this.userId = userId;
    	this.createDate = new Date();
    }

    protected QueueEventCommon(Long id, String userId, Date createDate) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
    }

    @Override
    public Long getId() {
        return id;
    }

	@Override
    public String getUserId() {
		return userId;
	}

	@Override
    public Date getCreateDate() {
        return createDate;
    }

    @Override
    public String getCreateDateAsString() {

        if (createDate == null) {
            return null;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(IQueueEvent.dateFormat);

        return sdf.format(createDate);
    }
    
    @Override
    public String getParamsAsString() {

        Map<String, String> params = getParams();

        if (params == null) {
            return null;
        }

        JSONObject paramsJSONObject = new JSONObject();

        //

        Iterator<String> paramsKeyIterator = params.keySet().iterator();

        while (paramsKeyIterator.hasNext() == true) {

            String key = paramsKeyIterator.next();
            String value = params.get(key);

            //

            try {
                paramsJSONObject.put(key, value);

            } catch (JSONException e) {
                throw new RuntimeException((e));
            }
        }

        return paramsJSONObject.toString();
    }

    public static Map<String, String> getParamsFromString(String params) {

        Map<String, String> result = new HashMap<>();

        if (params == null) {
            return result;
        }

        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(params);

            Iterator<String> jsonObjectKeyIterator = jsonObject.keys();

            while (jsonObjectKeyIterator.hasNext() == true) {

                String key = jsonObjectKeyIterator.next();
                String value = jsonObject.getString(key);

                //

                result.put(key, value);
            }

        } catch (JSONException e) {
            return result;
        }

        return result;
    }

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
    @Override
    public void setId(Long id) {
		this.id = id;
	}
}

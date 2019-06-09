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
    
    //
    
    protected String androidDeviceManufacturer;
    protected String androidDeviceModel;
    
    protected String androidVersion;
    
    protected String localeCountry;
    protected String localeLanguage;

    protected QueueEventCommon(String userId) {
    	this.userId = userId;
    	this.createDate = new Date();
    }

    protected QueueEventCommon(Long id, String userId, Date createDate, Map<String, String> params) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
        
        //
        
        this.androidDeviceManufacturer = params.get("androidDeviceManufacturer");
        this.androidDeviceModel = params.get("androidDeviceModel");
        
        this.androidVersion = params.get("androidVersion");
        
        this.localeCountry = params.get("localeCountry");
        this.localeLanguage = params.get("localeLanguage");
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
    public void setAndroidDeviceManufacturer(String androidDeviceManufacturer) {
		this.androidDeviceManufacturer = androidDeviceManufacturer;
	}

    @Override
	public void setAndroidDeviceModel(String androidDeviceModel) {
		this.androidDeviceModel = androidDeviceModel;
	}
    
    @Override
	public String getAndroidDeviceManufacturer() {
		return androidDeviceManufacturer;
	}

	@Override
	public String getAndroidDeviceModel() {
		return androidDeviceModel;
	}

	@Override
    public String getAndroidVersion() {
		return androidVersion;
	}

	@Override
    public String getLocaleCountry() {
		return localeCountry;
	}

    @Override
	public String getLocaleLanguage() {
		return localeLanguage;
	}
    
    @Override
    public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}

	@Override
	public void setLocaleCountry(String localeCountry) {
		this.localeCountry = localeCountry;
	}

    @Override
	public void setLocaleLanguage(String localeLanguage) {
		this.localeLanguage = localeLanguage;
	}
    
    @Override
    public Map<String, String> getParams() {
    	
    	Map<String, String> params = new HashMap<>();
    	
    	if (androidDeviceManufacturer != null) {
    		params.put("androidDeviceManufacturer", androidDeviceManufacturer);
    	}
    	
    	if (androidDeviceModel != null) {
    		params.put("androidDeviceModel", androidDeviceModel);
    	}
    	
    	if (androidVersion != null) {
    		params.put("androidVersion", androidVersion);
    	}
    	
        if (localeCountry != null) {
        	params.put("localeCountry", localeCountry);
        }
        
        if (localeLanguage != null) {
        	params.put("localeLanguage", localeLanguage);
        }        

    	return params;
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

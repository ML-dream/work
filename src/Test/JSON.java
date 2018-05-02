package Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flexjson.*;
import flexjson.transformer.*;

public class JSON {
	public static String Encode(Object obj) {
		if(obj == null || obj.toString().equals("null")) return null;
		if(obj != null && obj.getClass() == String.class){
			return obj.toString();
		}
		JSONSerializer serializer = new JSONSerializer();
		serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Date.class);
		serializer.transform(new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"), Timestamp.class);
		return serializer.deepSerialize(obj);
	}
	public static Object Decode(String json) {
		System.out.println("����decode�����壡����");
		Object obj= new Object();
		try {
			if (StringUtil.isNullOrEmpty(json)) return "";
			JSONDeserializer deserializer = new JSONDeserializer();
			deserializer.use(String.class, new DateTransformer("yyyy-MM-dd'T'HH:mm:ss"));		
			obj = deserializer.deserialize(json);
			if(obj != null && obj.getClass() == String.class){
				System.out.println("obj.toString()="+obj.toString());
				return Decode(obj.toString());
			}		
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("JSON��Test����ʱ�����쳣��������");
		}
		return obj;
		
		
	}
}


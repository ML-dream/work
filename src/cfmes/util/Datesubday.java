package cfmes.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;


public class Datesubday {
  ///���ڼ�ȥ�����õ����ڣ�������ַ�����ʽ������ת�����������ַ�����ת��
	public String dsd(String date1,int daynum){
		String date2="";
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
	   try{
		   date = format.parse(date1);
		   Calendar calendar = Calendar.getInstance();
		   calendar.setTime(date);
		   calendar.add(calendar.DATE,daynum);
		   date2 = format.format(calendar.getTime());
	   }catch(Exception e){
		   e.printStackTrace();
	   }
		
		return date2;
	}
}

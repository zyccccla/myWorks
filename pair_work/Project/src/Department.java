import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Department {
	private String d_no;
	private int numMax;
	private String[] d_tag;
	private String[] eventSchedules;
	private int[][] time_change;
	private int numFree;
	
	Department(int tag_sz,int sch_sz){
		d_tag = new String[tag_sz];
		eventSchedules = new String[sch_sz];
		time_change = new int[sch_sz][2];
	}
	
	public void setDateTime(){
		for(int i=0;i<time_change.length;i++){
			time_change[i] = dealEvent(eventSchedules[i]);
		}
		sort(time_change,0);
		merge();
		sort(time_change,0);
	}
	
	private void merge() {
		int tmp = 0;
		for(int i = 1; i < time_change.length; i++){
			if(time_change[i][0] <= time_change[tmp][1]){
				if(time_change[i][1] > time_change[tmp][1]){
					time_change[tmp][1] = time_change[i][1];
				}
				time_change[i][0] = 0;
				time_change[i][1] = 0;
			}
			else{
				tmp = i;
			}
		}
	}
	
	public  static void print(int[][] sample2) {
		// TODO Auto-generated method stub
		for(int i = 0; i< sample2.length; i++){
			System.out.println(sample2[i][0]+" "+sample2[i][1]);
		}
	}
	
	private void sort(int[][] time_change2, int i) {
		// TODO Auto-generated method stub
		List<int[]> helpList = Arrays.asList(time_change2);
		Collections.sort(helpList, new Comparator<int[]>(){
			@Override
		    public int compare(int[] arg0, int[] arg1) {
				if(arg0[0] >= arg1[0]){
				    return 1;
			    }
			    else{
				    return -1;
			    }
		    }			
		});
				
		time_change = (int[][])helpList.toArray();
	}
	
	
	//将时间转换为数字
	private int[] dealEvent(String string) {
		// TODO Auto-generated method stub
		int[] tmp = new int[2];
		int point_loc = string.indexOf('.');
		int wave_loc = string.indexOf('~');
		int fcolon_loc = string.indexOf(':');
		int scolon_loc = string.lastIndexOf(':');
		
		String begin_hour = string.substring(point_loc+1,fcolon_loc);
		String begin_minute = string.substring(fcolon_loc+1, wave_loc);
		String over_hour = string.substring(wave_loc+1,scolon_loc);
		String over_minute = string.substring(scolon_loc+1);
		
		tmp[0] = Integer.valueOf(begin_hour)*60+Integer.valueOf(begin_minute);
		tmp[1] = Integer.valueOf(over_hour)*60+Integer.valueOf(over_minute);
		
		String week = string.substring(0,point_loc);
		int weeks = 0;
		switch(week){
		case "Mon":
			weeks = 0;
			break;
		case "Tues":
			weeks = 1;
			break;
		case "Wed":
			weeks = 2;
			break;
		case "Thur":
			weeks = 3;
			break;
		case "Fri":
			weeks = 4;
			break;
		case "Sat":
			weeks = 5;
			break;
		case "Sun":
			weeks = 6;
			break;
		}
		
		tmp[0]+=weeks*24*60;
		tmp[1]+=weeks*24*60;
		
		return tmp;
	}
	
	public String getNo() {
		return d_no;
	}

	public void setNo(String no) {
		this.d_no = no;
	}

	public int getMemberMax() {
		return numMax;
	}

	public void setMemberMax(int MemberMax) {
		this.numMax = MemberMax;
		this.numFree = MemberMax;
	}

	public int getNumFree() {
		return numFree;
	}

	public void setNumFree(int numFree) {
		this.numFree = numFree;
	}

	public String[] getTags() {
		return d_tag;
	}

	public void setTags(int num,String str) {
		this.d_tag[num] = str;
	}

	public String[] getEventSchedules() {
		return eventSchedules;
	}

	public void setEventSchedules(int num,String str) {
		this.eventSchedules[num] = str;
	}

	public int[][] getTime() {
		return time_change;
	}
}

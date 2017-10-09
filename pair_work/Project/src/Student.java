import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Student {
	private String s_no;
	private String[] s_free;
	private String[] s_cho;
	private String[] s_tag;
	int[][] time_change;
	int num;              //记录每个学生允许加入的部门数
	
	Student(int dept_sz,int tag_sz,int free_sz) {
		s_tag = new String[tag_sz];
		s_cho = new String[dept_sz];
		s_free = new String[free_sz];
		time_change = new int[free_sz][2];
		num = 0;
	}
	
	public void setTime(){
		for(int i=0;i<time_change.length;i++){
			time_change[i] = dealFreeTime(s_free[i]);
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
	private int[] dealFreeTime(String string) {
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
	
	public  static void print(int[][] sample2) {
		// TODO Auto-generated method stub
		for(int i = 0; i< sample2.length; i++){
			System.out.println(sample2[i][0]+" "+sample2[i][1]);
		}
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int[][] getTime() {
		return time_change;
	}
	
	public String getNo() {
		return s_no;
	}

	public void setNo(String no) {
		this.s_no = no;
	}

	public String[] getTags() {
		return s_tag;
	}

	public void setTags(int num,String str) {
		this.s_tag[num] = str;
	}

	public String[] getChoice() {
		return s_cho;
	}

	public void setChoice(int num,String str) {
		this.s_cho[num] = str;
	}

	public String[] getFreeTime() {
		return s_free;
	}

	public void setFreeTime(int num,String str) {
		this.s_free[num] = str;
	}
}

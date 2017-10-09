import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Select {
	public static Map<String, ArrayList<String>> result;
	private static ArrayList<String>[] resultList;
	private static Map<String, ArrayList<String>> map;
    private static ArrayList<String>[] list;
    private static Map<String, Integer> stuMap;
    Select(){
    	result = new HashMap<String, ArrayList<String>>();
    	resultList= new ArrayList[20];
    	map = new HashMap<String, ArrayList<String>>();
	    list= new ArrayList[20];
	    stuMap = new HashMap<String, Integer>();
    }
    
    //学生部门匹配
    public void sdFit() throws Exception{
	    for(int i = 0; i < Input.stu.length; i++) {
	    	String no = Input.stu[i].getNo();
	    	stuMap.put(no, i);
	    }
	    
	    for(int i=0; i < Input.depart.length; i++) {
	    	list[i] = new ArrayList<String>();
	    	resultList[i] = new ArrayList<String>();
	    	String no = Input.depart[i].getNo();
	    	map.put(no, list[i]);
	    	result.put(no, resultList[i]);
	    }
	    
	    for(int i = 0; i < 5; i++) {
	    	fitProcess(i);
            clearNote();
	    }
	    
	    for(int i = 0; i < Input.depart.length; i++){
	    	String dno = Input.depart[i].getNo();
	    	if(result.get(dno).size() == 0){
	    		result.remove(dno);
	    	}
	    }
    }
    
    private void clearNote() {
		// TODO Auto-generated method stub
		for(int j = 0; j < Input.depart.length; j++) {
    		list[j].clear();
    	}
	}
	    
    private void fitProcess(int index) {
		
		for(int j = 0; j < Input.stu.length; j++) {
			
    		if(Input.stu[j].getChoice().length-1 >= index) {
    			String dept = Input.stu[j].getChoice()[index];
	    	    String no = Input.stu[j].getNo();
	    	    if(isFreeTimeConflict(index,dept) == false){
	    			map.get(dept).add(no);
	    	    }
    		}
    		
    	}
		
		
		for(int i = 0; i < Input.depart.length; i++) {
			String dno = Input.depart[i].getNo();
			int num = Input.depart[i].getNumFree();
			ArrayList<String> tmp = map.get(dno);
			int newNum = 0;
			
			if(num >= 0){
				if(num >= tmp.size()) {
					newNum = num - tmp.size();
					result.get(dno).addAll(tmp);
					Input.depart[i].setNumFree(newNum);
					dealNumAdmit(tmp);
				}
				else {
					tmp = filter(i,tmp,num);
					newNum = num - tmp.size();
					result.get(dno).addAll(tmp);
					Input.depart[i].setNumFree(newNum);
					dealNumAdmit(tmp);
				}
			}
			
		}
		
//		System.out.println(map);
//		System.out.println(result);
	
    }

	
	private ArrayList<String> filter(int index, ArrayList<String> tmp, int num) {
		
		String[] stuNo = (String[])tmp.toArray(new String[tmp.size()]);
		double[] rate = new double[tmp.size()];
		String[] depaTags = Input.depart[index].getTags();
		
		for(int i = 0 ; i < stuNo.length; i++){
			String sno = stuNo[i];
			int indexx = stuMap.get(sno);
			String[] stuTags = Input.stu[indexx].getTags();
			
			rate[i] = getRate(depaTags,stuTags);
		}
		stuNo = mySort(rate,stuNo);
		
		ArrayList<String> arrayList = new ArrayList<String>(Arrays.asList(stuNo));
		num = arrayList.size() - num;
		for(int i = 0; i < num; i++){
			int a = arrayList.size() - 1;
			arrayList.remove(a);
		}
		return arrayList;
	}
	
	private String[] mySort(double[] rate, String[] stuNo) {
		
		for(int i = 0; i < rate.length-1; i++){
			int k = i;
			for(int j = i+1; j<rate.length; j++){
				if(rate[j] > rate[k]) k=j;
			}
			if(k!=i){
				double temp1;
				temp1=rate[k];
				rate[k]=rate[i];
				rate[i]=temp1;
				
				String temp2;
				temp2=stuNo[k];
				stuNo[k]=stuNo[i];
				stuNo[i]=temp2;
				
			}
			
		}
		
		return stuNo;
	}
	
	private double getRate(String[] depaTags, String[] stuTags) {
		// TODO Auto-generated method stub
		int num1 = depaTags.length;
		int num2 = stuTags.length;
		int num = 0;
		for(int i = 0; i < num1; i++) {
			for(int j = 0; j < num2; j++) {
				if(depaTags[i].equals(stuTags[j])){
					num++;
				}
			}
		}
		double rate = (double)num/num2;
		return rate;
	}
	
	private void dealNumAdmit(ArrayList<String> tmp) {
		for(int i = 0; i < tmp.size(); i++){
			String sno = tmp.get(i);
			int index = stuMap.get(sno);
			int num = Input.stu[index].getNum();
			Input.stu[index].setNum(num + 1);
		}
		
	}

	private boolean isFreeTimeConflict(int index, String string) {
	    
		int[][] stuFreeTime = Input.stu[index].getTime();
		int[][] depFreeTime = null;
		
		for(int i = 0; i<Input.depart.length; i++) {
			
			if(string.equals(Input.depart[i].getNo())) {
				depFreeTime = Input.depart[i].getTime();
				break;
			}
		}
		
		for(int i = 0; i<= Input.depart[i].getTime().length; i++) {
			boolean isInclude = false;
			for(int j=0; j<stuFreeTime.length; j++) {
				if(stuFreeTime[j][1] != 0){
					if(stuFreeTime[j][0] <= depFreeTime[i][0]&&stuFreeTime[j][1] >= depFreeTime[i][1]){
					    isInclude = true; 
					    break;
				    }
				}
				if(isInclude == false){
					return false;
				}
			}
		}
		
		return true;
	}
	
    
}

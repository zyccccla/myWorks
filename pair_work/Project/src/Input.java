import java.io.File;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Input {
	static final String PATH = "input_data.txt";
	static final String STUDENTS = "students";
	static final int SNUM = 300;
	static final String DEPARTMENTS = "departments";
	static final int DNUM = 20;
	static Student[] stu = new Student[SNUM];
	static Department[] depart = new Department[DNUM];
	
	public void readInput() throws Exception {
		
		//读文件，构造JSONObject
		FileReader reader = new FileReader(new File(PATH));
		JSONTokener jsonTokener = new JSONTokener(reader);
		JSONObject jsonObject = new JSONObject(jsonTokener);
		
		JSONArray students = jsonObject.getJSONArray(STUDENTS);
		readStudents(students);
		JSONArray departments = jsonObject.getJSONArray(DEPARTMENTS);
		readDepartments(departments);
		
	}
	
	private void readStudents(JSONArray students2) {

		final String SNO = "student_no";
		final String STAGS = "tags";
		final String STIME = "free_time";
		final String SDEPT = "applications_department";
		
		for(int i=0;i<SNUM;i++){
	    	
	    	JSONObject student = students2.getJSONObject(i);
	    	String sno = student.getString(SNO);
	    	JSONArray sdept = student.getJSONArray(SDEPT);
	    	JSONArray stags = student.getJSONArray(STAGS);
	    	JSONArray stime = student.getJSONArray(STIME);
	    	
	    	if (stu[i] == null) {
	    		int dept_sz = sdept.length();
	    		int tag_sz = stags.length();
	    		int free_sz = stime.length();
	    		stu[i] = new Student(dept_sz,tag_sz,free_sz);
	    	}
	    	//填充学生信息
	    	stu[i].setNo(sno);
	    	for(int j=0;j<sdept.length();j++){
	    		stu[i].setChoice(j, sdept.getString(j));
	    	}
	    	
	    	
	    	for(int j=0;j<stags.length();j++){
	    		stu[i].setTags(j, stags.getString(j));
	    	}
	    	
	    
	    	for(int j=0;j<stime.length();j++){
	    		stu[i].setFreeTime(j, stime.getString(j));

	    	}
	    	stu[i].setTime();
	    }
		
	}
	
	private void readDepartments(JSONArray departments) {
		
		final String DNO = "department_no";
		final String DMEM = "member_limit";
		final String DTAGS = "tags";
		final String DSCH = "event_schedules";
		
		for(int i = 0; i < DNUM; i++) {
			JSONObject department = departments.getJSONObject(i);
			String D_no = department.getString(DNO);
	    	int D_mem = department.getInt(DMEM);
	    	JSONArray D_tag = department.getJSONArray(DTAGS);
	    	JSONArray D_sch = department.getJSONArray(DSCH);
	    	
	    	if(depart[i] == null){
	    		int tag_sz = D_tag.length();
	    		int sch_sz = D_sch.length();
	    		depart[i] = new Department(tag_sz,sch_sz);
	    	}
	    	
	    	depart[i].setNo(D_no);
	    	depart[i].setMemberMax(D_mem);
	    	for(int j=0;j<D_tag.length();j++){
	    		depart[i].setTags(j, D_tag.getString(j));
	    	}
	    	for(int j=0;j<D_sch.length();j++){
	    		depart[i].setEventSchedules(j, D_sch.getString(j));
	    	}
	    	
	    	depart[i].setDateTime();
		}
	}
}

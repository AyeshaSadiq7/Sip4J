package working_examples;


public class MTTS {

 private TaskData data ;
 
 public MTTS (){
	 data = new TaskData();
 }
 public void setData (TaskData d){
   data = d;
 }
 public TaskData getData (){
 return data ;
 }
 public void execute (TaskData d){
	 data  = d;
  }
 public TaskData getTaskStatus (){
	 return data;
  }
}

class TaskData{
	
	public static void main(String args){	
		TaskData d = new TaskData();
		MTTS t = new MTTS();
		t.setData(d);
		t.getData();
		t.execute(d);
		t.getTaskStatus();
	}
}
package model;

public class Employee {
	private String name;
	private String id;
	private String department;
	private String attendance;
	private String date;
	
	//コンストラクタ
	public Employee(String name, String id, String department) {
		this.name = name;
		this.id = id;
		this.department = department;
	}
	
	public Employee(String name, String id, String department, String attendance) {
		this(name, id, department);
		this.attendance = attendance;
	}
	
	public Employee(String name, String id, String department, String attendance, String date) {
		this(name, id, department, attendance);
		this.date = date;
	}

	
	//setterとgetter
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}
	
	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}
	
	public String getDepartment() {
		return department;
	}

	public String getAttendance() {
		return attendance;
	}
	
	public String getDate() {
		return date;
	}

}

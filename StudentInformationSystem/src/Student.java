import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Student extends studentData{
	public static String path = ".\\src\\Data\\studentData.txt";
	static List<String> rawData = new ArrayList<String>();
	
	Student() throws FileNotFoundException {
		getStudentData();
	}
	
	//Print all of the students from txt file
	//without any format (raw)
	public void getStudentData() throws FileNotFoundException {
		rawData.removeAll(rawData);
		File file = new File(path);
		Scanner sc = new Scanner(file);
		
		String line;
		
		//While txt file has lines
		//Chinecheck neto each line yung text file
		while (sc.hasNextLine()) {
			line = sc.nextLine().strip();
			rawData.add(line);
		}
		//Callign setter in studentData class
		setAllStudent(rawData.toArray(new String[rawData.size()]));
	}
	
	//Adding a student in the txt file
	public boolean addStudent(String name, String studentNum, String section,
			 String course, String guardian, String guarduanNum) throws IOException {
		
		File file = new File(path);
		Scanner sc = new Scanner(file);

		FileWriter writer = new FileWriter(path, true);
		String line;
		
		//For each students
		for (int i = 0; i < getAllStudent().length; i++) 
		{
			line = getAllStudent()[i];
			//Checking if the name already exist
			if(line.toUpperCase().contains(name.toUpperCase())) 
			{
				System.out.println(" Name already exist");
				return false;
			}

			//Checking if student number already exist
			if(line.contains(studentNum)) 
			{
				System.out.println(" Student number already exist");
				return false;
			}
		}

		//Saving the string into the txt file
		writer.write("\nName: " + name
				+ ", Student Number: " + studentNum
				+ ", Section: " + section.toUpperCase()
				+ ", Course: " + course.toUpperCase()
				+ ", Guardian: " + guardian
				+ ", Guardian Number: " + guarduanNum
				+ ", Present: 0, Absent: 0");
		writer.flush();
		return true;
		
	}
	
	//Removing student by getting the student number
	public void removeStudent(String studentNum) throws IOException
	{
		Scanner sc = new Scanner(System.in);
		String line;
		StringBuffer tempData = new StringBuffer();
		String[] std = new String[7];
		boolean isCorrect = true;

		for (int i = 0; i < getAllStudent().length; i++) 
		{
			if(studentNum.length() < 7)
			{
				isCorrect = false;
				System.out.println(" Enter the correct student number");
				break;
			}
			line = getAllStudent()[i];
			
			//remove the student if the student number is in the txt file
			if(line.contains(studentNum)) 
			{
				std = line.split(",");
				line = "";
			} 
			else 
			{
				tempData.append("\n");
			}
			tempData.append(line);
		}

		if(isCorrect == true)
		{
			//Printing student information the user wants to delete
			System.out.printf("%n %s %n%s %n%s %n%s %n%s %n%s %n%s %n%s%n%n", 
					std[0], std[1], std[2], std[3], std[4], std[5], std[6], std[7]);
			
			
			System.out.println(" Are you sure you want to remove this student? [Y] [N]");
			
			//If the user input is Y, constinue deleting the student 
			if(sc.nextLine().equalsIgnoreCase("Y"))
			{
				System.out.println(" Successfully removed the student you entered");
				FileWriter writer = new FileWriter(path);
				writer.write(tempData.toString().trim());
				writer.flush();
			}
			else
			{
				System.out.println(" You canceled the removal");
			}
		}
	}
	
	//Searching all student with the same course and section
	public void searchStudent(String course, String section) throws FileNotFoundException 
	{	
		String line;
		String[] std = new String[7];
		List<String> lineTemp = new ArrayList<String>();
		List<String> sameSectionStudents = new ArrayList<String>();

		for (int i = 0; i < getAllStudent().length; i++) 
		{
			line = getAllStudent()[i];
			
			if(line.contains(section.toUpperCase())) {
				if(line.contains(course.toUpperCase())) {
					sameSectionStudents.add(line);
				}
			}
		}
		System.out.printf("%30s %20s %20s %20s %20s %30s %20s %20s", 
				"NAME", "STUDENT NUMBER", "SECTION", "COURSE", "GUARDIAN", "GUARDIAN NUM", "PRESENT", "ABSENT");
		System.out.println("\n ================================================================================================================="
				+ "===================================================================================");
		
		//Getting the data (nireremove muna yung category such as name, section, etc)
		//Kumbaga value nalang meron wala ng variable
		for (int i = 0; i < sameSectionStudents.size(); i++) {
			std = sameSectionStudents.get(i).split(",");
			System.out.printf("%n%30s %20s %20s %20s %20s %30s %20s %20s", 
					std[0].replaceAll("Name: ", ""),
					std[1].replaceAll(" Student Number: ", ""),
					std[2].replaceAll(" Section: ", ""),
					std[3].replaceAll(" Course: ", ""),
					std[4].replaceAll(" Guardian: ", ""), 
					std[5].replaceAll(" Guardian Number: ", ""),
					std[6].replaceAll(" Present: ", ""), 
					std[7].replaceAll(" Absent: ", ""));
		}
		System.out.println("\n\n ================================================================================================================="
				+ "===================================================================================\n");
	}
	
	//Searching student with student num
	public void searchStudent(String studentNum) throws FileNotFoundException 
	{
		String line;

		String[] std = new String[7];
		boolean isContain = false;
		boolean isCorrect = true;
		
		//For each students on the list
		for (int i = 0; i < getAllStudent().length; i++) 
		{
			line = getAllStudent()[i];
			
			//if student number length is less the 7 redo the loop
			if(studentNum.length() < 7)
			{
				isCorrect = false;
				System.out.println(" Enter the correct student number");
				break;
			}
			
			if(line.contains(studentNum)) 
			{
				std = line.split(",");
				isContain = true;
				break;
			}
		}

		if(isContain) 
		{
			//print the student info if the student number is correct
			System.out.printf("%n %s %n%s %n%s %n%s %n%s %n%s %n%s %n%s%n%n", 
					std[0], std[1], std[2], std[3], std[4], std[5], std[6], std[7]);
		}
		else
		{
			if(isCorrect == true)
			System.out.println(" Student Number does not exist! ");
		}
	}
	
	public void attendance(String section, String course, Scanner scan) throws IOException 
	{
		StringBuffer data = new StringBuffer();
		String[] tempData = new String[7];
		String[] present = new String[1];
		String[] absent = new String[1];
		String[] std = new String[6];
		String line;
		
		//To make sure the String buffer is empty
		data.delete(0, data.length());
		boolean isAppend = true;
		String choice = "";
		int j = 0;
		
		System.out.println("\n Enter cancel to stop the attendance ");
		for (int i = 0; i < getAllStudent().length; i++)
		{
			isAppend = true;
			line = getAllStudent()[i];
			if(section.equalsIgnoreCase("exit") || course.equalsIgnoreCase("exit"))
			{
				break;
			}
			
			if(line.contains(section.toUpperCase())) 
			{
				if(line.contains(course.toUpperCase()))
				{
					j++;
					std = line.trim().split(",");
					System.out.printf("%n %s %n%s %n%s %n%s %n%s %n%s%n%n", 
							std[0], std[1], std[4], std[5], std[6], std[7]);
					
					System.out.print(" Press [1] Present   [2] Absent"
							+ "\n Enter: ");
					choice = scan.nextLine();
					System.out.println("---------------------------------");
					
					//If user input is exit, stop the attendance program
					if(choice.equalsIgnoreCase("exit")) 
					{
						break;
					}
					tempData = line.split(",");

					present = tempData[6].strip().split(":");
					absent = tempData[7].strip().split(":");

					int valueA = (Integer.valueOf(absent[1].strip()) + 1);
					int valueP = (Integer.valueOf(present[1].strip()) + 1);
					if(choice.equals("1")) 
					{
						//present
						//Kinukuha data tapos nilalagay sa string buffer
						data.append("\n"+tempData[0].strip()
								+", " + tempData[1].strip()
								+", " + tempData[2].strip()
								+", " + tempData[3].strip()
								+", " + tempData[4].strip()
								+", " + tempData[5].strip()
								+", " + present[0] + ": ".stripLeading() + String.valueOf(valueP).stripLeading()
								+", " + absent[0] + ": ".stripLeading() + absent[1].stripLeading());
						isAppend = false;
					}
					else if(choice.equals("2"))
					{
						//Absent
						//Kinukuha data tapos nilalagay sa string buffer
						data.append("\n"+tempData[0].strip()
								+", " + tempData[1].strip()
								+", " + tempData[2].strip()
								+", " + tempData[3].strip()
								+", " + tempData[4].strip()
								+", " + tempData[5].strip()
								+", " + present[0] + ": ".stripLeading() + present[1].stripLeading()
								+", " + absent[0] + ": ".stripLeading() + String.valueOf(valueA).stripLeading());
						isAppend = false;
					} 
					else 
					{
						//Balik sa past student if input is incorrect
						i--;
						continue;
					}
				}
			}
			
			//append next line
			if(isAppend == true) 
			{
				data.append("\n"+line);
			}
			
		}
		
		if(j <= 0) 
		{
			System.out.println("\n The section and year you called have no student. Please add new student");
		} 
		else if(!choice.equalsIgnoreCase("exit"))
		{
			FileWriter writer = new FileWriter(path);
			writer.write(data.toString());
			writer.flush();
			System.out.println(" All of the student had beed called. \n\n");
		}
		else if(choice.equalsIgnoreCase("exit"))
		{
			System.out.println(" Attendance have been canceled");
		}
	}
	
	//Removing whitespaces before exit
	public void exitSystem() throws IOException 
	{
		StringBuffer tempData = new StringBuffer();

		for (int i = 0; i < getAllStudent().length; i++) 
		{
			if(!getAllStudent()[i].equals(""))
				if(i < (getAllStudent().length-1))
				{
					tempData.append(getAllStudent()[i] + "\n");
				}
				else
				{
					tempData.append(getAllStudent()[i]);
				}
		}
		
		FileWriter writer = new FileWriter(path);
		writer.write(tempData.toString().trim());
		writer.flush();
	}
}

//Added encapsulation for all student 
//for global variable
class studentData {
	private String[] allStudent;

	public String[] getAllStudent() {
		return allStudent;
	}

	public void setAllStudent(String[] allStudent) {
		this.allStudent = allStudent;
	}
	
	
}

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class runStudent {
	public static void main(String[] args) throws IOException {
		
		//WARNING
		//WAG MAG TERMINATE NG PROGRAM
		//MAWAWALA LANG YUNG DATA NYAN
		
		//EXIT THE PROGRAM GAMIT YUNG MENU
		
		//IN CASE NA NA WALA YUNG DATA AY MAY BACK UP TXT FILE AKONG GINAWA
		//PWEDE NYO ICOPY-PASTE YON OR BACKUP NYO NALANG YUNG TXT FILE NYO IF EVER
		//GUSTYO NYO NA MGA PANGALAN NG SCHOOL MATES ILAGAY
		
		
		//INPUT GUIDE
		//Name: 5 Minimum - 30 Max characters -  numbers and special character are not allowed
		//Student Num: 5 Minimum - 20 Max Numbers - Letters and characters are not allowed
		//Section: 1 latter and 1 number only (example) - 2A 3B 4A 1D
		//Course: courses lng pwede 
		//Guardian Name:  5 Minimum - 30 Max characters -  numbers and special character are not allowed
		//Guardian Number: 11 Numbers and numbers only
		String courseInput, sectionInput, studentNumInput, studentNameInput, guardian, guardianNum, userChoice;
		String username, password;
		Admin admin = new Admin();
		Scanner scan = new Scanner(System.in);
	
		System.out.println(" WELCOME TO STUDENT INFORMATION SYSTEM");
		do 
		{
			System.out.print(" Enter username: ");
			username = scan.nextLine();
			System.out.print(" Enter password: ");
			password = scan.nextLine();
			if(admin.isLogin(username, password)) 
			{
				break;
			}
		} while(true);

		System.out.println(" (!)Login successfully(!)");
		
		do 
		{
			System.out.println(" ============================================="
					+ "===================================");
			Student student = new Student();
			System.out.print("\n [1] Attendance  [2] Add Student  [3] Remove Student  [4] Search Student  [5] Exit");
			System.out.print("\n Enter: ");
			userChoice = scan.nextLine();
			
			switch (userChoice)
			{
			//attendance
			case "1":
				if(student.getAllStudent().length <= 0)
				{
					System.out.println(" The database is empty. Please add new student");
					break;
				}
				System.out.println(" Enter exit to every input to go back\n");
				do 
				{
					System.out.print(" Section: ");
					sectionInput = scan.nextLine();
					System.out.print(" Course: ");
					courseInput = scan.nextLine();
					
					if("exit".matches(sectionInput+"|"+courseInput)) {
						break;
					}
					
					if(isValidInput(sectionInput, courseInput) ) 
					{
						student.attendance(sectionInput, courseInput, scan);
						break;
					}
				} while (true);
				break;
			//Add student
			case "2":
				System.out.println(" Enter exit to every input to go back");
				do 
				{
					System.out.print("\n Student Name: ");
					studentNameInput = scan.nextLine();
					System.out.print(" Student Number: ");
					studentNumInput = scan.nextLine();
					System.out.print(" Student Year and Section: ");
					sectionInput = scan.nextLine();
					System.out.print(" Student Course: ");
					courseInput = scan.nextLine();
					System.out.print(" Guardian: ");
					guardian = scan.nextLine();
					System.out.print(" Guardian Number: ");
					guardianNum = scan.nextLine();
					
					if("exit".matches(studentNameInput+"|"+studentNumInput+"|"+sectionInput+"|"+courseInput)) {
						break;
					}
					if(isValidInput(studentNameInput, studentNumInput, sectionInput, courseInput, guardian, guardianNum) ) 
					{
						student.addStudent(studentNameInput, studentNumInput, sectionInput, courseInput, guardian, guardianNum);
						break;
					}
				} while (true);
				
				break;
			//Remove student
			case "3":
				if(student.getAllStudent().length <= 0)
				{
					System.out.println(" The database is empty. Please add new student");
					break;
				}
				else
				{
					System.out.print(" Enter student number: ");
					student.removeStudent(scan.nextLine());
				}
				
				break;
			case "4":
				System.out.println("\n ==================================="
						+ "=============================================");
				if(student.getAllStudent().length <= 0)
				{
					System.out.println(" The database is empty. Please add new student");
					break;
				}
				System.out.print("\n [1] Search all student in section  [2] Search student with student number  [3]  Exit");
				System.out.print("\n Enter: ");
				userChoice = scan.nextLine();
				
				if(userChoice.equals("1")) 
				{
					System.out.println(" Enter exit to every input to go back");
					do 
					{
						System.out.print(" Student Section: ");
						sectionInput = scan.nextLine();
						System.out.print(" Student Course: ");
						courseInput = scan.nextLine();
						if("exit".matches(sectionInput+"|"+courseInput)) {
							break;
						}
						
						if(isValidInput(sectionInput, courseInput)) 
						{
							System.out.println();
							student.searchStudent(courseInput, sectionInput);
							break;
						}
					} while (true);
					
				} 
				else if(userChoice.equals("2")) 
				{
					System.out.println(" Enter exit to go back\n");
					do 
					{
						System.out.print(" Student Number: ");
						studentNumInput = scan.nextLine();
						
						if("exit".matches(studentNumInput)) {
							break;
						}
						
						if(Pattern.matches("[0-9]*",studentNumInput)) 
						{
							student.searchStudent(studentNumInput);
							break;
						}
					} while(true);
				}
				break;
			//exit
			case "5":
				System.out.println("\n System have been closed");
				student.exitSystem();
				System.exit(0);
				break;
			default:
				break;
			}

		} while(admin.isLogin(username, password));
	}
	
	public static boolean isValidInput(String name, String studentNum, String section, String course, String guardian, String guardianNum) 
	{

		if(Pattern.matches("^[a-z A-Z]{5,30}$", name)) 
		{
			if(Pattern.matches("^[0-9]{7,20}$", studentNum)) 
			{
				if(section.toUpperCase().matches("1A|1B|1C|1D|2A|2B|2C|2D|3A|3B|3C|3D|4A|4B|4C|4D")) 
				{
					if(course.toUpperCase().matches("BSIT|BSCS|BSIS|IT|ACT|BSBA|BSA|BSAIS|BSMA|BSRTCS|ART"
							+ "|BSHM|BSCM|HRA|HRS|BSTM|TEM|BSCPE|BMMA|BACOMM"))
					{
						if(Pattern.matches("^[a-z A-Z]{5,30}$", guardian)) 
						{
							if(Pattern.matches("[0-9]{11}", guardianNum)) 
							{
								return true;
							}
							else
							{
								System.out.println(" Enter the correct phone number");
							}
						}
						else
						{
							System.out.println(" Special characters and numbers are not allowed in names");
						}
						
					}
					else 
					{
						System.out.println(" Please enter the correct course. ");
					}
				}
				else 
				{
					System.out.println(" Please enter the correct section");
				}
			}
			else
			{
				System.out.println(" Enter the correct student number");
			}
		}
		else
		{
			System.out.println(" Special characters and numbers are not allowed in names");
		}
		
		return false;
	}
	
	public static boolean isValidInput(String section, String course) {
		if(section.toUpperCase().matches("1A|1B|1C|1D|2A|2B|2C|2D|3A|3B|3C|3D|4A|4B|4C|4D")) 
		{
			if(course.toUpperCase().matches("BSIT|BSCS|BSIS|IT|ACT|BSBA|BSA|BSAIS|BSMA|BSRTCS|ART"
					+ "|BSHM|BSCM|HRA|HRS|BSTM|TEM|BSCPE|BMMA|BACOMM"))
			{
				return true;
				
			}
			else 
			{
				System.out.println(" Please enter the correct course. ");
			}
		}
		else 
		{
			System.out.println(" Please enter the correct section");
		}
		return false;
	}
}

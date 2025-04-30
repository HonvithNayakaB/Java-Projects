import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
class createEntry{
    void addStudent(Scanner scan){
        try{
            FileWriter fw=new FileWriter("Student_Database.txt",true);
            scan.nextLine();
            System.out.print("please enter students name = ");
            String name =scan.nextLine();
            System.out.print("please enter stduends register no = ");
            String regno =scan.nextLine();
            System.out.print("please enter the student age = ");
            int age=scan.nextInt();
            scan.nextLine();
            System.out.print("please enter students program = ");
            String program=scan.nextLine();
            fw.write(name+","+regno+","+age+","+program+"\n");
            System.out.println("\nsuccessfully entred the student entry\n");
            fw.close();
        }    
        catch(IOException e){
            System.out.println("an IOException occured..");
        }
    }
}

class editEntry{
   List<String> students = new ArrayList<>();

   editEntry(){
    try{
        BufferedReader reader = new BufferedReader(new FileReader("Student_Database.txt"));
        String line;

        while((line = reader.readLine()) != null){
            students.add(line);
        }
        reader.close();
    }
    catch (IOException e ){
        System.out.println("error reading the file");
    }
   }

    void editStudent(Scanner scan){
        students.clear();  // Clear old data
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Student_Database.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("error reading the file");
        }
        scan.nextLine();
        System.out.print("please the student regno number = ");
        String targetRegno = scan.nextLine();

        boolean found = false;
        int index = -1;
        for (int i = 0; i <students.size(); i++){
            String [] parts = students.get(i).split(",");
            String regno = parts[1];
             if(regno.equals(targetRegno)){
                found=true;
                index=i;
                break;
             }
         }

        if(!found){
            System.out.println("Student with Regno "+ targetRegno+" not found.");
            return;
         }
        String[] oldParts = students.get(index).split(",");
        String name = oldParts[0];
        String regno = oldParts[1];
        int age = Integer.parseInt(oldParts[2]);
        String program = oldParts[3];

        System.out.println("What do you want to change ? ");
        System.out.println("1. NAME");
        System.out.println("2. Regno");
        System.out.println("3. Age");
        System.out.println("4. Program");
        System.out.print("Please enter your choice = ");
        int choice = scan.nextInt();
        scan.nextLine();
        switch (choice){
            case 1:
            System.out.print("Enter the name = ");
            name=scan.nextLine();
            break;
            case 2:
            System.out.print("Enter new Regno = ");
            regno = scan.nextLine();
            break;
            case 3:
            System.out.print("Enter the new age = ");
            age = scan.nextInt();
            break;
            case 4:
            System.out.print("Enter the new program = ");
            program =scan.nextLine();
            break;
            default:
            System.out.println("invalid choice ");
            return;
        }
        String updatedStudent=name+","+regno+","+age+","+program;

        students.set(index,updatedStudent);

        try{
            BufferedWriter writer=new BufferedWriter(new FileWriter("Student_Database.txt"));
            for (String s :students){
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("Error writing file");
        }
        System.out.println("Student details updated successfully!");
   }
}


class deleteEntry{
    List  <String> students = new ArrayList<>();
    deleteEntry(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader ("Student_Database.txt"));
            String line;
            while((line=reader.readLine())!=null){
                students.add(line);
            }
            reader.close();
        }
        catch (IOException e){
            System.out.println("error rading the file");
        }
    }

    void deleteStudent(Scanner scan){
        scan.nextLine();
        System.out.print("please enter the registeration number of the student = ");
        String targetRegno = scan.nextLine();

        boolean found = false;
        int index = -1;
        for (int i = 0; i < students.size(); i++){
            String [] parts = students.get(i).split(",");
            String regno = parts[1];
             if(regno.equals(targetRegno)){
                found=true;
                index=i;
                break;
             }
         }

        if(!found){
            System.out.println("Student with Regno "+ targetRegno+" not found.");
            return;
         }
        students.remove(index);
        System.out.println("removed student");

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter ("Student_Database.txt"));
            for (String s: students){
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("error writing the file");
        }

        }

    }

class viewEntry{
    List<String> students = new ArrayList<>();
    viewEntry(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader ("Student_Database.txt"));
            String line;
            while((line=reader.readLine())!=null){
                students.add(line);
            }
            reader.close();
        }
        catch (IOException e){
            System.out.println("error rading the file");
        }
    }


    void viewStudent(Scanner scan){
        students.clear();  // Clear old data
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Student_Database.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(line);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("error reading the file");
        }
        scan.nextLine();
        System.out.print("please enter the program = ");
        String targetprogram = scan.nextLine();

        boolean found = false;
        boolean heading = true;
        for (String s : students){
            String [] parts = s.split(",");
            String  program= parts[3];
             if(program.equals(targetprogram)){
                found = true;
                if(heading != false){
                    System.out.printf("%-15s %-10s %-5s %-10s%n", "Student Name", "Reg No", "Age", "Program\n");
                    heading=false;
                }
                System.out.printf("%-15s %-10s %-5s %-10s%n", parts[0], parts[1], parts[2], parts[3]);
             }
         }

        if(!found){
            System.out.println("no students are enroled with the specified program name "+ targetprogram );
            return;
        }
    }
}

public class Student_Database {
    static Scanner scan = new Scanner(System.in);
    public static void main(String args[]){
        createEntry ce = new createEntry();
        editEntry ee = new editEntry();
        viewEntry ve = new viewEntry();
        deleteEntry de = new deleteEntry();
        System.out.println("__________MENU__________");
        System.out.println("1. ADD STUDENT DATA");
        System.out.println("2. EDIT STUDENT DATA");
        System.out.println("3. DELETE STUDENT DATA");
        System.out.println("4. VIEW ENTRIES");
        System.out.println("5. EXIT");


        while (true) { 
            System.out.print("please enter your option = ");
            int opt= scan.nextInt();
            switch (opt){
                case 1:
                ce.addStudent(scan);
                break;
                case 2:
                ee.editStudent(scan);
                break;
                case 3:
                de.deleteStudent(scan);
                break;
                case 4:
                ve.viewStudent(scan);
                break;
                case 5:
                System.out.println("ending seccion..");
                break;
                default :
                System.out.println("please enter a valid input from the menu..");
            }
        }
    }
}

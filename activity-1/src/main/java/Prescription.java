import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private String examinationDate;
    private String optometrist;
    private String[] remarkTypes;
    private ArrayList<String> postRemarks;

    //constructor with parameters for Prescription class
    public Prescription(int prescID, String firstName, String lastName, String address, float sphere, float axis, float cylinder, String examinationDate, String optometrist, String[] remarkTypes, ArrayList<String> postRemarks) {
        this.prescID = prescID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.axis = axis;
        this.cylinder = cylinder;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
        this.remarkTypes = remarkTypes;
        this.postRemarks = postRemarks;
    }

    //method to add Prescription
    public boolean addPrescription() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\files\\prescription.txt", true));
        try {
            if (checkName(firstName) && checkName(lastName) && checkAddress(address) && checkDate(examinationDate) && checkPowerValues(sphere, axis, cylinder) && checkOptometristName(optometrist)) {
                writer.write("First Name: " + firstName + "\tLast Name: " + lastName + "\tAddress: " + address + "\nExamination Date: " + examinationDate.toString() + "\nSphere:" + sphere + "\tAxis: " + axis + "\tCylinder: " + cylinder + "\nOptometrist: Dr. " + optometrist + "\n\n");
                writer.close();
                System.out.println("successfully added " + firstName);
                return true;
            }
        }
        catch (Exception e)
        {
            System.out.println("error" + e.getMessage());
        }
        return false;
    }

    //method to add Remarks to list
    public boolean addRemark() throws Exception
    {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src\\main\\files\\remarks.txt", true));
        if (checkRemarksList(postRemarks) && checkRemarkTypes(remarkTypes))
        {
            for (int i = 0; i < postRemarks.size(); i++)
            {
                writer.append("Remark " + (i+1) + ": " + postRemarks.get(i) + "\n\n");
            }
            writer.close();
            return true;
        }
        return false;
    }

    //method to check Name size is between 4 and 15 inclusive
    private boolean checkName(String name)
    {
        return name.length() >= 4 && name.length() <= 15;
    }

    private boolean checkAddress(String address)
    {
        return address.length() >= 20;
    }

    private boolean checkDate(String date)
    {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yy");
        formatter.setLenient(false);
        try {
            formatter.parse(date);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    //method to check Sphere, Axis and Cylinder values is between [-20 and 20], [0 and 180] and [-4 and 4] respectively
    private boolean checkPowerValues(float sphere, float axis, float cylinder)
    {
        return (sphere >= -20 && sphere <= 20) && (cylinder >= -4 && cylinder <= 4) && (axis >= 0 && axis <= 180);
    }

    //method to check optometrist name length is between 8 and 25 inclusive
    private boolean checkOptometristName(String name)
    {
        return name.length() >= 8 && name.length() <= 25;
    }

    private boolean checkRemarkTypes(String[] remarkTypes)
    {
        boolean flag = false;
        for (String remark : remarkTypes)
        {
            flag = (remark.equalsIgnoreCase("client") || remark.equalsIgnoreCase("optometrist"));
            if (!flag)
            {
                return false;
            }
        }
        return flag;
    }

    //method to check no. of remarks is less than 3, no. of words in each remark is between 6 and 20 inclusive, and first letter of a remark is in UpperCase
    private boolean checkRemarksList(ArrayList<String> postRemarks)
    {
        boolean flag = false;
        if (!(postRemarks.size() > 2))
        {
            for (String remark : postRemarks)
            {
                String[] words = remark.split(" ");
                if (words.length >= 6 && words.length <= 20)
                {
                    flag = Character.isUpperCase(words[0].charAt(0));
                }
                if (!flag)
                {
                    return false;
                }
            }
        }
        return flag;
    }



}

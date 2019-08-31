//Event.java

import java.util.Scanner;
import java.io.Serializable;

//abstract Event class that extends a database node class
public class Event implements Permission,Serializable {
    private Date date = new Date();
    private Address address = new Address();
    private Contact contact = new Contact();
    private String name;
    private int uid;

    private LinkedList<Integer> coordinators = new LinkedList<Integer>();
    private LinkedList<Integer> facilitators = new LinkedList<Integer>();
    private LinkedList<Integer> participants = new LinkedList<Integer>();

    //constructor
    Event( int _uid ) {
        this.addCoordinator(_uid);
        this.uid = _uid;

        //User names the event
        this.setName();
        
        //User input for the Address of the Event
        this.setAddress();
        
        //User sets the date of the event
        this.setDate();

        //User sets the contact info for the event
        this.setContact();
    }

    //check for permission with the set user id (uid)
    public void setUId(int _uid) {
        this.uid = _uid;
    }

    public PL checkPermission() {
        if (this.coordinators.getHead() != null) {
            if (this.coordinators.search(this.uid))
                return PL.COORDINATOR;
        } else if (this.facilitators.getHead() != null) {
            if (this.facilitators.search(this.uid))
                return PL.FACILITATOR;
        } else if (this.participants.getHead() != null) {
            if (this.participants.search(this.uid))
                return PL.PARTICIPANT;
        } else {
            return PL.DENIED;
        }
        return PL.DENIED;
    }

    //print event info
    public void printInfo() {
        System.out.println(this.getName() + "\n" + 
            this.getDate() + "\n" + 
            this.getAddress() + "\n" + 
            this.getContact() + "\n");
    }

    //get the event data contact,address,date
    public String getDate() {
        if (this.checkPermission() == PL.DENIED) 
            return "PERMISSION DENIED";
        return "Event Date: " + this.date.getDate();
    }

    public String getAddress() {
        if (checkPermission() == PL.DENIED)
            return "PERMISSION DENIED";
        return "Address: " + this.address.getAddress();
    }

    public String getContact() {
        if (checkPermission() == PL.DENIED)
            return "PERMISSION DENIED";        
        return "Phone: " + this.contact.getPhone() + "\nEmail: " + this.contact.getEmail();
    }

    //set the private variables

    //setting the date with appropriate variable types
    public void setDate() {
        if (this.checkPermission() != PL.COORDINATOR)
            return;
        Scanner input = new Scanner(System.in);
        System.out.println("What would you like the new date of " + this.getName() + " to be? XX/XX/XXXX format.");

        System.out.print("Day: ");
        int day = input.nextInt();
        System.out.print("Month: ");
        int month = input.nextInt();
        System.out.print("Year: ");
        int year = input.nextInt();
        this.date.setDay(day);
        this.date.setMonth(month);
        this.date.setYear(year);
    }

    //setting the address of the event
    public void setAddress() {
        if (this.checkPermission() != PL.COORDINATOR)
            return;

        Scanner input = new Scanner(System.in);
        System.out.println("What would you like the new Address of " + this.getName() + " to be?");

        System.out.print("Street: ");
        String street = input.nextLine();
        System.out.print("Apartment Number: ");
        String apartment = input.nextLine();
        System.out.print("City: ");
        String city = input.nextLine();
        System.out.print("State: ");
        String state = input.nextLine();
        System.out.print("Zip Code: ");
        String zipCode = input.nextLine();

        this.address.setStreet(street);
        this.address.setApartment(apartment);
        this.address.setCity(city);
        this.address.setState(state);
        this.address.setZipCode(zipCode);
    }

    //setting the contact information of the event
    public void setContact() {
        int choice;
        String phone, email;
        if (this.checkPermission() != PL.COORDINATOR)
            return;

        Scanner input = new Scanner(System.in);
        System.out.println("Would you like to change the cellular (0) or email contact info (1)? ");
        do {
            choice = input.nextInt();
            input.nextLine();
        } while (choice >= 2 || choice < 0);
        
        if (choice == 0) {
            do {
                System.out.print("Phone: ");
                phone = input.nextLine();
                if (phone.length() != 10) {
                    System.out.println("Please enter a valid phone number.");
                }
            } while (phone.length() != 10);
            this.contact.setPhone(phone);
        } else {
            System.out.print("Email: ");
            email = input.nextLine();
            this.contact.setEmail(email);
        }
    }

    //set the name of the Event
    public void setName() {
        if (this.checkPermission() != PL.COORDINATOR)
            return;
        System.out.println("What would you like the name of the Event to be? ");

        Scanner input = new Scanner(System.in);
        
        String name = input.nextLine();

        this.name = name;
    }

    //get the name
    public String getName() {
        return this.name;
    }

    //send out/set permission of users
    public void addCoordinator (int _uid) {
        this.coordinators.insert(new LLNode<Integer>(_uid));
    }

    public void addFacilitator (int _uid) {
        this.facilitators.insert(new LLNode<Integer>(_uid));
    }

    public void addParticipant (int _uid) {
        this.participants.insert(new LLNode<Integer>(_uid));
    }

    public static void main(String[] args) {
        Event e = new Event(0);
        System.out.println(e.getName());
        System.out.println(e.getAddress());
        System.out.println(e.getDate());
        System.out.println(e.getContact());
        e.addFacilitator(3);
        e.addParticipant(1);

        System.out.println("Adjusted Permissions");

    }
}






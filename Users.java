//Users.java

import java.io.Serializable;
import java.util.Scanner;
import java.io.*;

//Users class
public class Users implements Login,Serializable {
    private LinkedList<User> userRoot = new LinkedList<User>();
    private LinkedList<Event> eventRoot = new LinkedList<Event>();
    private int c_uid; 

    public LLNode findEvent(int _id) {
        LLNode<Event> finding = this.eventRoot.getHead();
        if (finding == null) {
            return null;
        }
        do {
            if (_id == finding.getId()) {
                return finding;
            }
            finding = finding.getAfter();
        } while(finding != null && _id != finding.getId());
        if (finding == null) {
            System.out.println("item not found");
        }
        return null;
    }

    public LLNode findUser(String _username) {
        LLNode<User> current = this.userRoot.getHead();
        if(current == null) {
            System.out.println("Nothing in List");
            return null;
        }
        while (current != null) {
            if ((current.getValue().getUsername()).equals(_username)) {
                System.out.println("User Found: " + current.getValue().getUsername());
                this.c_uid = current.getId();
                return current;
            } 
            current = current.getAfter();
        }
        System.out.println("User not found");
        return null;
    }

    //Event Object Saving
    public void saveEvents(String filename) {
        try {
            //Saving of LinkedList to File
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream outFile = new ObjectOutputStream(file);
            
            outFile.writeObject(this.eventRoot);

            outFile.close();
            file.close();

            System.out.println("Linked List Saved");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void loadEvents(String filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream inFile = new ObjectInputStream(file);

            this.eventRoot = (LinkedList<Event>)inFile.readObject();



            inFile.close();
            file.close();

            System.out.println("Linked List loaded");
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
 

    //User Object saving
    public void saveUsers(String filename) {
        try {
            //Saving of LinkedList to File
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream outFile = new ObjectOutputStream(file);

            outFile.writeObject(this.userRoot);

            outFile.close();
            file.close();

            System.out.println("Linked List Saved");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void loadUsers(String filename) {
        try {
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream inFile = new ObjectInputStream(file);

            this.userRoot = (LinkedList<User>)inFile.readObject();

            inFile.close();
            file.close();

            System.out.println("Linked List loaded");
        } catch (IOException e) {
            System.out.println(e);
        } catch (ClassNotFoundException e) {
            System.out.println(e);
        }
    }
 
    //User Interface Tools
    
    //User Login Screen. Access App
    public User login() {
        int selection;
        User user;
        String username;
        String password;
        LLNode<User> node;
        boolean keepGoing = false;

        System.out.println("Welcome to EventManager. Please Login or Sign-up.");
        System.out.println("1: Login");
        System.out.println("2: Sign-up");
        do {
            Scanner input = new Scanner(System.in);
            selection = input.nextInt();
        } while (selection >= 2 && selection < 0);
        if (selection == 1) {
            do {
                keepGoing = false;
                System.out.print("Username: ");
                Scanner s_username = new Scanner(System.in);
                username = s_username.nextLine();
                node = this.findUser(username);
                if (this.findUser(username) == null) {
                    selection = -1;
                    keepGoing = true;
                }
            } while (keepGoing);
            user = node.getValue();
            if (user != null) {
                do {
                    System.out.println("Password: ");
                    Scanner s_password = new Scanner(System.in);
                    password = s_password.nextLine();
                    if (!(password.equals(user.getPassword()))) {
                        System.out.println("Incorrect Password. Try Again");
                    }
                } while (!(password.equals(user.getPassword())));
                this.c_uid = node.getId();
                System.out.println("Logged in");
            } else {
                return null;
            }
        } else {
            user = new User();
            this.userRoot.insert(new LLNode<User>(user));
            this.c_uid = this.userRoot.getHead().getId();
            System.out.println("New Account Created");
        }
        return user;
    }

    //Managing Events per User
    public void listEvents() {
        System.out.println("Event Listing \n");
        
        int counter = 0;
        LLNode<Event> current = this.eventRoot.getHead();
        while (current != null) {
            current.getValue().setUId(this.c_uid);
            if (current.getValue().checkPermission() != PL.DENIED) {
                System.out.println(
                    current.getValue().getName() + 
                    "\nEvent ID: " + current.getId() +
                    "\nRole: " + current.getValue().checkPermission() + 
                    "\nAddress: " + current.getValue().getAddress() + 
                    "\nDate: " + current.getValue().getDate() +
                    "\nAddress: " + current.getValue().getAddress() +
                    "\n\n"
                );
                counter++;
            }
            current = current.getAfter();
        }
        if (counter == 0) {
            System.out.println("No Events to show :(");
        }
    }

    //Managing Events per User
    public void listEventsByPermission(PL permission) {
        System.out.println("Event Listing \n");
        
        int counter = 0;
        LLNode<Event> current = this.eventRoot.getHead();
        while (current != null) {
            current.getValue().setUId(this.c_uid);
            //if (current.getValue().checkPermission() == permission) {
                System.out.println(
                    current.getValue().getName() + 
                    "\nEvent ID: " + current.getId() +
                    "\nRole: " + current.getValue().checkPermission() + 
                    "\nAddress: " + current.getValue().getAddress() + 
                    "\nDate: " + current.getValue().getDate() +
                    "\nContact: " + current.getValue().getContact() +
                    "\n\n"
                );
                counter++;
            //}
            current = current.getAfter();
        }
        if (counter == 0) {
            System.out.println("No Events to show :(");
        }
    }

    public void createEvent() {
        Event event = new Event( this.c_uid);
        event.addCoordinator(this.c_uid);
        this.eventRoot.insert(new LLNode<Event>(event));
    }

    public void editEvent() {
        Scanner input = new Scanner(System.in);
        int id,choice;
        System.out.println("Event Listing:\n");

        LLNode<Event> event;
    
        //list events available to edit
        this.listEventsByPermission(PL.COORDINATOR);

        System.out.println("Choose one of your Events to edit by id: ");
        do {
            id = input.nextInt();
            event = this.findEvent(id);
        } while (this.findEvent(id) == null);
        Event changed = event.getValue();
        System.out.println("What would you like to change?");
        System.out.println("1: Address");
        System.out.println("2: Date");
        System.out.println("3: Contact");
        do {
            choice = input.nextInt();
        } while (choice > 3 || choice < 1);

        switch (choice) {
            case 1: 
                changed.setAddress();
                break;
            case 2:
                changed.setDate();
                break;
            case 3:
                changed.setContact();
                break;
        }
    }

    public void deleteEvent() {
        this.listEventsByPermission(PL.COORDINATOR);
        Scanner input = new Scanner(System.in);
        int id;

        System.out.println("Choose the Event you would like to delete\nby entering its id");
        System.out.println("Enter -1 to not delete");
        do {
            id = input.nextInt();
            if (id == -1)
                return;
        } while (this.findEvent(id) == null);

        LLNode<Event> node = this.findEvent(id);
        System.out.println("Event " + node.getValue().getName() + " deleted.");
        this.eventRoot.deleteNode(node);
    }

    public void inviteToEvent() {
        Scanner input = new Scanner(System.in);
        int role;
        int _id;
        
        this.listEventsByPermission(PL.COORDINATOR);
        this.listEventsByPermission(PL.FACILITATOR);

        System.out.println("Choose the Event you would like to send an invite from\nby entering its id");
        do {
            System.out.print("Id: ");
            _id = input.nextInt();
        } while (this.findEvent(_id) == null);

        LLNode<Event> event = this.findEvent(_id);
        System.out.println("Found Event");
        event.getValue().setUId(this.c_uid);
        System.out.println("Set ID to " + _id);
        PL permissionLevel = event.getValue().checkPermission();
        System.out.println("Set Permission");
        if (permissionLevel == PL.COORDINATOR || permissionLevel == PL.FACILITATOR) {
            System.out.println("What User?");
            System.out.print("Username: ");
            String username = input.nextLine();
            LLNode<User> sendTo = this.findUser(username);
            if (sendTo == null) {
                return;
            }
            System.out.println("Found user");
            switch (permissionLevel) {
                case COORDINATOR:
                    System.out.println("What role would you like to give this user?");
                    System.out.println("0: Coordinator ");
                    System.out.println("1: Facilitator");
                    System.out.println("2: Participant");
                    do {
                        role = input.nextInt();
                    } while (role > 2 || role < 0);

                    if (role == 0) {
                        event.getValue().addCoordinator(sendTo.getId());
                    } else if (role == 1) {
                        event.getValue().addFacilitator(sendTo.getId());
                    } else if (role == 2) {
                        event.getValue().addParticipant(sendTo.getId());
                    }

                    break;
                case FACILITATOR:
                    System.out.println("What role would you like to give this user?");
                    System.out.println("1: Facilitator");
                    System.out.println("2: Participant");

                    do {
                        role = input.nextInt();
                    } while (role > 2 || role < 1);

                    if (role == 0) {
                        //would be for coordinator but not allowed
                    } else if (role == 1) {
                        event.getValue().addFacilitator(sendTo.getId());
                    } else if (role == 2) {
                        event.getValue().addParticipant(sendTo.getId());
                    }

                    break;                
            }
            String info = ("Invite for " + event.getValue().getName() + "\n");
            sendTo.getValue().newInvite(info);
        } else {
            System.out.println("You do not have permission to do this.");
        }
    }

    public void viewInvites(String _username) {
        LLNode<User> user = this.findUser(_username);
        user.getValue().viewInvites();
    }

    //print User Menu
    public int printMenu() {
        int choice;
        Scanner input = new Scanner(System.in);

        System.out.println("Choose from the following options.");
        System.out.println("1: Create a new event"); 
        System.out.println("2: Edit an existing event"); 
        System.out.println("3: Invite another user to one of your events"); 
        System.out.println("4: View your events"); 
        System.out.println("5: Delete an event");
        System.out.println("6: Quit");
        do {
            choice = input.nextInt();    
        } while (choice < 1 || choice > 6);
        return choice;
    }

    public static void main (String[] args) {
        Users p = new Users();
        int choice;
        boolean keepGoing = true;

        //load user and event listings
        p.loadEvents("Events.ser");
        p.loadUsers("Users.ser");

        //user logs in
        do {
            User user = p.login();
            keepGoing = true;
            
            do {
                choice = p.printMenu();
                switch (choice) {
                    case 1:
                        p.createEvent();
                        break;
                    case 2:
                        p.editEvent();
                        break;
                    case 3:
                        p.inviteToEvent();
                        break;
                    case 4:
                        p.listEvents();
                        break;
                    case 5:
                        p.deleteEvent();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                }
            } while (keepGoing);
            System.out.println("Would you like to exit the program? q to quit");
            Scanner input = new Scanner(System.in);
            String quit = input.nextLine();
            if (quit.equals("q")) {
                break;
            }
        } while (true);
        

        //save user and event listings
        p.saveUsers("Users.ser");
        p.saveEvents("Events.ser");
    }
}











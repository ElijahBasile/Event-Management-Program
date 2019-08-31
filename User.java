//User.java

import java.util.Scanner;
import java.io.*;
import java.util.Date;

public class User implements UsernamePassword, Serializable {
    private String password;
    private String username;
    private Contact contact;
    private String inviteInfo = "";

    User() {
        //set user id from the time of construction

        do {
            System.out.println("Enter a unique username: ");
            Scanner s_username = new Scanner(System.in);
            this.username = s_username.nextLine();
        } while (this.checkUniqueUsername() == false);
        do {
            System.out.println("Now a password of atleast eight characters: ");
            Scanner s_password = new Scanner(System.in);
            this.password = s_password.nextLine();
        } while (this.checkPasswordLength(this.password.length())==false);
        
        System.out.println("Account Created Under: " + this.getUsername());
        
        //write username to file
        try {
            FileWriter outFile = new FileWriter("usernameList.txt", true);
            BufferedWriter output = new BufferedWriter(outFile);
            
            output.write(username);
            output.newLine();
            output.flush();
            
            outFile.close();
            
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public boolean checkPasswordLength (int _length) {
        if (_length >= 8) {
            System.out.println("Valid Password");
            return true;
        } else {
            System.out.println("Invalid Password");
            return false;
        }
    }

    public boolean checkUniqueUsername() {
        try {
            BufferedReader usernameList = new BufferedReader( new FileReader("usernameList.txt"));
            String line = usernameList.readLine();
            boolean unique = true;
            while(line != null) {
                if(line.equals(this.username)) {
                    unique = false;
                    break;
                }
                //move on
                line = usernameList.readLine();
            }
            usernameList.close();
            if (unique == true)
                System.out.println("Unique Username");
            else 
                System.out.println("Username Taken");
            return unique;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } //end try
        return false;
    }

    //Invite Management
    public void newInvite(String info) {
        this.inviteInfo += info;
    }

    public void viewInvites() {
        System.out.println(this.inviteInfo);
    } //end Invite Management

    public void setContact(String _phone, String _email) {
        this.contact.setPhone(_phone);
        this.contact.setEmail(_email);
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public void setUsername(String _username) {
        this.username = _username;
    }

    public String getPassword() {
        return this.password;
    }
    
    public String getUsername() {
        return this.username;
    }

    //public static void main(String[] args) {
    //    LinkedList<User> userRoot = new LinkedList<User>();
    //    User u = new User();
    //    System.out.println("User Created");
    //    userRoot.insert(new LLNode<User>(u));
    //    System.out.println("Success");
    //}
}

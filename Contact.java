//Contact.java

import java.io.Serializable;

public class Contact implements Serializable {
    private String phone;
    private String email;
    
    Contact() {
        this.setPhone("0000000000");
        this.setEmail("notentered");
    }

    public void setPhone(String _number) {
        this.phone = _number;
    }

    public void setEmail(String _email) {
        this.email = _email;
    }
    
    //getters

    public String getPhone() {
        return this.phone;
    }
    public String getEmail() {
        return this.email;
    }

    //public static void main(String[] args) {
    //    Contact c = new Contact();
    //    
    //   c.setPhone("3178508520");
    //   c.setEmail("ejbasile@iu.edu");
    //
    //    System.out.println(c.getPhone());
    //    System.out.println(c.getEmail());
    //}
}

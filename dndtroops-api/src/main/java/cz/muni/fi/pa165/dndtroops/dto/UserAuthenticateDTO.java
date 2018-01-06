
package cz.muni.fi.pa165.dndtroops.dto;

/**
 *
 * @author Martin Sestak
 */
public class UserAuthenticateDTO {
    private String name;
    
    private String password;

    public UserAuthenticateDTO() {
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPasswordHash(String password) {
        this.password = password;
    }
    
    
}

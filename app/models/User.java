package models;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;
import play.data.validation.Constraints;
import javax.persistence.*;



/**
 * Created by Jean Kahigiso on 4/7/14.
 */
@Entity
@Table(name="mashi_user")
public class User extends Timer {

    @Id
    @Constraints.Email
    public String email;
    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;
    @Constraints.Required
    public String password;

    public User(String firstName,String lastName, String email, String password){
        this.firstName= firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    @Transient
    public String getName(){
        return firstName+" "+lastName;
    }

    public static Finder<String,User> find = new Finder<String, User>(String.class, User.class);

    public static User authenticate(String email, String password){
        return find.where()
                .eq("email",email)
                .eq("password",password)
                .findUnique();
    }

    public static Page<User> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .or(
                                Expr.like("firstName", "%" + filter + "%"),
                                Expr.like("lastName", "%" + filter + "%")
                        )
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }
}

package models;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;
import play.data.validation.Constraints;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jean Kahigiso on 3/20/14.
 */

@Entity
@Table(name="mashi_type_word") //Nature de mot ex: adjactive, verbe,.....
public class Type extends Timer {

    @Id
    public Long id;
    @Constraints.Required
    @Column (unique = true)
    public String name;
    @Constraints.Required
    @Column (unique = true)
    public String abbreviation;
    @OneToMany(mappedBy = "type")
    public Set<Word> words = new HashSet<Word>();

    public static Finder<Long,Type> find = new Finder<Long,Type>(Long.class, Type.class);

    public static Page<Type> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .or(
                                Expr.like("name", "%" + filter + "%"),
                                Expr.like("abbreviation", "%" + filter + "%")
                        )
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}

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
@Table(name = "mashi_origin_word")
public class Origin extends Timer {

    @Id
    public Long id;
    @Constraints.Required
    @Column(unique = true)
    public String name;
    @Constraints.Required
    @Column(unique = true)
    public String abbreviation;
    @OneToMany(mappedBy = "origin")
    public Set<Word> words = new HashSet<Word>();

    public Origin(String name, String abbreviation){
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public static Finder<Long,Origin> find = new Finder<Long,Origin>(Long.class, Origin.class);

    public static Page<Origin> page(int page, int pageSize, String sortBy, String order, String filter) {
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
        return "Origin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}

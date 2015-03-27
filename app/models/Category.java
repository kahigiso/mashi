package models;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;
import play.data.validation.Constraints;
import javax.persistence.*;


/**
 * Created by Jean Kahigiso on 3/20/14.
 */

@Entity
@Table(name = "mashi_category_word") //Domaine ex: Botanique, Habitation, Justice ...
public class Category extends Timer {

    private static final long serialVersionUID = 1L;

    @Id
    public Long id;
    @Constraints.Required
    @Column(unique = true)
    public String name;
    @Constraints.Required
    @Column(unique = true)
    public String abbreviation;

    public static Finder<Long,Category> find = new Finder<Long,Category>(Long.class, Category.class);

    public static Page<Category> page(int page, int pageSize, String sortBy, String order, String filter) {
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
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}

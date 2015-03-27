package models;


import javax.persistence.*;

import com.avaje.ebean.Page;
import play.data.validation.Constraints;


/**
 * Created by Jean Kahigiso on 3/20/14.
 */
@Entity
@Table(name = "mashi_example_word")
public class Example extends Timer {

    @Id
    public Long id;
    @ManyToOne
    public Word word;
    @Constraints.Required
    @Column(unique = true)
    public String description;

    public Example(Word word, String description){
        this.word= word;
        this.description = description;
    }

    public static Finder<Long,Example> find = new Finder<Long,Example>(Long.class, Example.class);

    public static Page<Example> page(int page, int pageSize, String sortBy, String order, String description) {
        return
                find.where()
                        .ilike("name", "%" + description + "%")
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }

    @Override
    public String toString() {
        return "Example{" +
                "id=" + id +'\'' +
                ", description='" + description +
                '}';
    }
}

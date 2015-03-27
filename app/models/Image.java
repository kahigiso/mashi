package models;

import com.avaje.ebean.Page;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.*;

/**
 * Created by Jean Kahigiso on 3/20/14.
 */
@Entity
@Table(name="mashi_image_word")
public class Image extends Timer {

    @Id
    public Long id;
    @Constraints.Required
    @Column(unique = true)
    public String url;
    @Constraints.Required
    public String caption;

    @ManyToMany(mappedBy = "images")
    public Set<Word> associatedWords = new HashSet<Word>();

    public Image(String url, String caption){
        this.url = url;
        this.caption = caption;
    }

    public Image(String url, String caption, Set<Word> words){
        this.url = url;
        this.caption = caption;
        this.associatedWords = words;
    }

    public static Finder<Long,Image> find = new Finder<Long,Image>(Long.class, Image.class);

    public static Page<Image> page(int page, int pageSize, String sortBy, String order, String caption) {
        return
                find.where()
                        .ilike("name", "%" + caption + "%")
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }


    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}

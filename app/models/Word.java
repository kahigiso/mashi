package models;

import javax.persistence.*;
import java.util.HashSet;

import java.util.Set;

/**
 * Created by Jean Kahigiso on 3/20/14.
 */
@Entity
@Table(name = "mashi_abstract_word")
public abstract class Word extends Timer {

    @Id
    public Long id;
    public String precision;
    public String plural;
    @ManyToOne
    public Origin origin;
    @ManyToOne
    public Type type;
    @OneToMany(mappedBy = "word")
    public Set<Example> examples = new HashSet<Example>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "word_categories",
               joinColumns = {@JoinColumn(name="word_id")},
                inverseJoinColumns = {@JoinColumn(name="category_id")})
    public Set<Category> categories = new HashSet<Category>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "word_images",
            joinColumns = {@JoinColumn(name="word_id")},
            inverseJoinColumns = {@JoinColumn(name="image_id")})
    public Set<Image> images = new HashSet<Image>();

    @Override
    public String toString() {
        return "Word{" +
                "id= " + id +
                ", plural= " + plural +'\'' +
                ", precision= " + precision +'\'' +
                "}";
    }
}

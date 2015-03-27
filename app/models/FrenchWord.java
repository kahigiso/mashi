package models;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jean Kahigiso on 4/1/14.
 */
@Entity
@Table(name = "mashi_french_word")
public class FrenchWord extends Word {

    @Column(name = "french_word", nullable = false)
    public String frenchWord;

    public FrenchWord(String frenchWord, String plural, String precision,Origin origin, Type type){
        this.frenchWord = frenchWord;
        this.plural = plural;
        this.precision = precision;
        this.origin = origin;
        this.type = type;
    }

    public Set<Word> mashiTranslations = new HashSet<Word>();

    public static Finder<Long,FrenchWord> find = new Finder<Long,FrenchWord>(Long.class, FrenchWord.class);

    public static Page<FrenchWord> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .or(
                                Expr.like("frenchWord", "%" + filter + "%"),
                                Expr.like("plural", "%" + filter + "%")
                        )
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }


    @Override
    public String toString() {
        return "FrenchWord{" +
                "id= " + id +
                ", frenchWord= " + frenchWord +'\'' +
                ", plural= " + plural +'\'' +
                ", precision= " + precision + '\'' +
                "}";
    }

}

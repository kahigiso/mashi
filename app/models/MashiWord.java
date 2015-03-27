package models;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Page;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jean Kahigiso on 4/1/14.
 */
@Entity
@Table(name = "mashi_mashi_word")
public class MashiWord extends Word {

    @Column(name = "mashi_word", nullable = false)
    public String mashiWord;

    public Set<Word> frenchTranslations = new HashSet<Word>();

    public MashiWord(String mashiWord, String plural, String precision,Origin origin, Type type){
        this.mashiWord = mashiWord;
        this.plural = plural;
        this.precision = precision;
        this.origin = origin;
        this.type = type;
    }

    public static Finder<Long,MashiWord> find = new Finder<Long,MashiWord>(Long.class, MashiWord.class);

    public static Page<MashiWord> page(int page, int pageSize, String sortBy, String order, String filter) {
        return
                find.where()
                        .or(
                                Expr.like("mashiWord", "%" + filter + "%"),
                                Expr.like("plural", "%" + filter + "%")
                        )
                        .orderBy(sortBy + " " + order)
                        .findPagingList(pageSize)
                        .setFetchAhead(false)
                        .getPage(page);
    }

    @Override
    public String toString() {
        return "MashiWord{" +
                "id= " + id +
                ", mashiWord= " + mashiWord +'\'' +
                ", plural= " + plural +'\'' +
                ", precision= " + precision +'\'' +
                "}";
    }




}

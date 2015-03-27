package models;

import play.data.format.Formats;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Jean Kahigiso on 4/7/14.
 */
@Entity
@Table(name = "mashi_abstract_timer")
public abstract class Timer extends Model {
    @Formats.DateTime(pattern="dd-MM-yyyy h:m:s")
    public Date addedOn = new Date();
    @Formats.DateTime(pattern="dd-MM-yyyy h:m:s")
    public Date lastModifiedOn = new Date();
}

import com.avaje.ebean.Ebean;
import models.User;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;

import java.util.List;

/**
 * Created by Jean Kahigiso on 4/7/14.
 */
public class Global extends GlobalSettings {
    @Override
    public void onStart(Application app){
        if(User.find.findRowCount() ==0){
            Ebean.save((List) Yaml.load("initial-data.yml"));
        }
    }
}
package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;
import views.html.users.*;
import views.html.helper.form;


/**
 * Created by Jean Kahigiso on 4/7/14.
 */
public class Users extends Controller {

    public static Result GO_HOME = redirect(
            routes.Users.list(0, "firstName", "asc", "")
    );

    public static Result index() { return GO_HOME;}

    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
                list.render(
                        User.page(page, 10, sortBy, order, filter),
                        sortBy, order, filter
                )
        );
    }

    public static Result edit(String email) {
        Form<User> user = form(User.class).fill(User.find.byId(email));
        return ok(editForm.render(email, user));
    }

    public static Result update(String email) {
        Form<User> user = form(User.class).bindFromRequest();
        if(user.hasErrors()) {
            return badRequest(editForm.render(email, user));
        }
        user.get().update(email);
        flash("success", "User " + user.get().getName() + " a ete modifie");
        return GO_HOME;
    }

    public static Result create() {
        Form<User> user = form(User.class);
        return ok(createForm.render(user));
    }


    public static Result save() {
        Form<User> user = form(User.class).bindFromRequest();
        if(user.hasErrors()) {
            return badRequest(createForm.render(user));
        }
        user.get().save();
        flash("success", "Utilisateur " + user.get().getName() + " a ete cree");
        return GO_HOME;
    }


    public static Result delete(String email) {
        User user = User.find.ref(email);
        String names = user.getName();
        user.delete();
        flash("success", "Utilisateur "+names+" a ete effacer");
        return GO_HOME;
    }

}

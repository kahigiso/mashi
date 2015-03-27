package controllers;

import models.Type;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import static play.data.Form.form;
import views.html.types.*;
import views.html.helper.form;

/**
 * Created by Jean Kahigiso on 4/2/14.
 */
public class Types extends Controller{


    public static Result GO_HOME = redirect(
            routes.Types.list(0, "name", "asc", "")
    );

    public static Result index() { return GO_HOME;}

    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
                list.render(
                        Type.page(page, 10, sortBy, order, filter),
                        sortBy, order, filter
                )
        );
    }

    public static Result edit(Long id) {
        Form<Type> type = form(Type.class).fill(Type.find.byId(id));
        return ok(editForm.render(id, type));
    }

    public static Result update(Long id) {
        Form<Type> type = form(Type.class).bindFromRequest();
        if(type.hasErrors()) {
            return badRequest(editForm.render(id, type));
        }
        type.get().update(id);
        flash("success", "Une nature " + type.get().name + " a ete modifiee");
        return GO_HOME;
    }

    public static Result create() {
        Form<Type> type = form(Type.class);
        return ok(createForm.render(type));
    }


    public static Result save() {
        Form<Type> type = form(Type.class).bindFromRequest();
        if(type.hasErrors()) {
            return badRequest(createForm.render(type));
        }
        type.get().save();
        flash("success", "Une nature " + type.get().name + " a ete creee");
        return GO_HOME;
    }


    public static Result delete(Long id) {
        Type.find.ref(id).delete();
        flash("success", "Une nature a ete efface");
        return GO_HOME;
    }



}

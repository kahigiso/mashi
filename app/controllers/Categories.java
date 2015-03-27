package controllers;

import models.*;

import play.data.*;
import static play.data.Form.*;
import play.mvc.*;
import views.html.categories.*;
import views.html.helper.form;




/**
 * Created by Jean Kahigiso on 4/7/14.
 */
public class Categories extends Controller {


    public static Result GO_HOME = redirect(
            routes.Categories.list(0, "name", "asc", "")
    );

    public static Result index() { return GO_HOME;}

    public static Result list(int page, String sortBy, String order, String filter) {
        return ok(
                list.render(
                        Category.page(page, 10, sortBy, order, filter),
                        sortBy, order, filter
                )
        );
    }

    public static Result edit(Long id) {
        Form<Category> category = form(Category.class).fill(Category.find.byId(id));
        return ok(editForm.render(id, category));
    }

    public static Result update(Long id) {
        Form<Category> category = form(Category.class).bindFromRequest();
        if(category.hasErrors()) {
            return badRequest(editForm.render(id, category));
        }
        category.get().update(id);
        flash("success", "Category " + category.get().name + " has been updated");
        return GO_HOME;
    }

    public static Result create() {
        Form<Category> category = form(Category.class);
        return ok(createForm.render(category));
    }


    public static Result save() {
        Form<Category> category = form(Category.class).bindFromRequest();
        if(category.hasErrors()) {
            return badRequest(createForm.render(category));
        }
        category.get().save();
        flash("success", "Category " + category.get().name + " has been created");
        return GO_HOME;
    }


    public static Result delete(Long id) {
        Category.find.ref(id).delete();
        flash("success", "Category has been deleted");
        return GO_HOME;
    }



}

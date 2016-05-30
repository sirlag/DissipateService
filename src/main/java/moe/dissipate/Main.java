package moe.dissipate;

import moe.dissipate.ItemLevelsLibrary.ILLCharacter;
import moe.dissipate.ItemLevelsLibrary.PlayerSearch;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.redirect;

public class Main {
    public static void main(String[] args){
        Map<String, Object> model = new HashMap<>();
        model.put("message", "Hello World!");

        get("/", (request, response) -> new ModelAndView(model, "index"), new JadeTemplateEngine());

        get("/hello", ((request, response) -> "Hello World"));

        get("/hello/:name", (req,res) -> "Hello " + req.params(":name"));

        get("/search", (request, response) -> {
            HashMap<String, Object> temp = new HashMap<>();
            temp.put("characters", PlayerSearch.Search(request.queryParams("Character"), ""));
            return new ModelAndView(temp, "search");
        }, new JadeTemplateEngine());

        get("/character/:slug", (request, response) -> {
            HashMap<String, Object> temp = new HashMap<>();
            ILLCharacter chara = new ILLCharacter(request.params(":slug"));
            temp.put("character", chara);
            return new ModelAndView(temp, "character");
        }, new JadeTemplateEngine());
    }


}

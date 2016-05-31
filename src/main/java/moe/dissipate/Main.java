package moe.dissipate;

import moe.dissipate.ItemLevelsLibrary.ILLCharacter;
import moe.dissipate.ItemLevelsLibrary.Player;
import moe.dissipate.ItemLevelsLibrary.PlayerSearch;
import spark.ModelAndView;
import spark.template.jade.JadeTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;
import static spark.debug.DebugScreen.enableDebugScreen;

public class Main {
    public static void main(String[] args){
        //enableDebugScreen();
        port(80);
        staticFiles.location("/public");
        Map<String, Object> model = new HashMap<>();
        model.put("message", "empty");

        get("/", (request, response) -> new ModelAndView(model, "index"), new JadeTemplateEngine());

        get("/search", (request, response) -> {
            HashMap<String, Object> temp = new HashMap<>();
            List<Player> players = PlayerSearch.Search(request.queryParams("Character"), "");
            if(players!= null && players.size() == 1)
                response.redirect(players.get(0).getURL());
            temp.put("isEmpty", players.isEmpty());
            temp.put("characters", players);
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

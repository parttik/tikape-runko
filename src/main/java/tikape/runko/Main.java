package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.KoiraDao;
import tikape.runko.database.KenneliDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:koirat.db");
        database.init();

        KoiraDao koiraDao = new KoiraDao(database);
        KenneliDao kentsu=new KenneliDao(database);

        /*if (System.getenv("PORT") != null) {
            Spark.port(Integer.valueOf(System.getenv("PORT")));
        }*/
        
        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viesti", "tervetuloa");
            map.put("kennelit", kentsu.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/kenneli/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("koirat", koiraDao.findAll());
            map.put("kenneli", kentsu.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "kenneli");
        }, new ThymeleafTemplateEngine());

        get("/kenneli/:id/:koira_id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("koira", koiraDao.findOne(Integer.parseInt(req.params("koira_id"))));

            return new ModelAndView(map, "koira");
        }, new ThymeleafTemplateEngine());
    }
}

package tikape.runko;

import java.util.HashMap;
import spark.ModelAndView;
import spark.Spark;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import tikape.runko.database.Database;
import tikape.runko.database.KoiraDao;
import tikape.runko.database.KenneliDao;
import tikape.runko.domain.*;

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
        
         post("/", (req, res) -> {
            String nimi = req.queryParams("nimi");
            kentsu.save(new Kenneli(nimi));
            res.redirect("/");
            return "";
        });

        get("/kenneli/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("koirat", koiraDao.findAll(Integer.parseInt(req.params("id"))));
            map.put("kenneli", kentsu.findOne(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "kenneli");
        }, new ThymeleafTemplateEngine());
        
        post("/kenneli/:id", (req, res) -> {
            String nimi = req.queryParams("nimi");
            String rotu = req.queryParams("rotu");
            String omistaja = req.queryParams("omistaja");
            String syntymaaika = req.queryParams("syntymaaika");
            Koira hauva=new Koira(nimi);
            hauva.setRotu(rotu);
            hauva.setOmistaja(omistaja);
            hauva.setSyntymaaika(syntymaaika);
            hauva.setKenneli_id(Integer.parseInt(req.params("id")));
            koiraDao.save(hauva);
            res.redirect("/kenneli/" + Integer.parseInt(req.params("id")));
            return "";
        });

        get("/kenneli/:id/:koira_id", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("kenneli", kentsu.findOne(Integer.parseInt(req.params("id"))));
            map.put("koira", koiraDao.findOne(Integer.parseInt(req.params("koira_id"))));
            map.put("nayttelyt", koiraDao.getNayttelyt(Integer.parseInt(req.params("koira_id"))));
            return new ModelAndView(map, "koira");
        }, new ThymeleafTemplateEngine());
        
        post("/kenneli/:id/:koira_id", (req, res) -> {
            String pvm=req.queryParams("paivamaara");
            String sijainti=req.queryParams("sijainti");
            Integer sijoitus=Integer.parseInt(req.queryParams("sijoitus"));
            koiraDao.insertNayttely(Integer.parseInt(req.params("koira_id")), pvm, sijainti, sijoitus);
            
            res.redirect("/kenneli/" + Integer.parseInt(req.params("id")) + '/' + Integer.parseInt(req.params("koira_id")));
            return "";
        });
    }
}

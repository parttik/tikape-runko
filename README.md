# tikape-sovellus

Sovellus on koiratietokanta, jossa voi tarkastella ja lisätä kenneleitä, niiden koiria ja koiranäyttelyitä. Etusivulla on listattuna kennelit linkkeinä. Kun yhdestä painaa, pääsee sen kennelin kaikkien koirien listaukseen. Koiralinkistä pääsee taas koiran omalle sivulle, jossa näkyvät sen tiedot ja koiranäyttelysijoitukset.

Koiran ja koiranäyttelyn välillä on monesta moneen suhde, koska koirat voivat käydä monissa näyttelyissä ja niissä on monta koiraa. Koirien ja Näyttelyiden välille tuli siis Näyttelykoira-taulu, jossa on viiteavainten lisäksi tieto kyseisen koiran sijoituksesta kyseisessä näyttelyssä. Tieto koiran näyttelysijoituksista näytetään koiran omalla sivulla. 

Tällä hetkellä lisäys-toiminnallisuudessa ei ole mitään tarkistuksia tiedon oikeudesta vaan koiran syntymäajaksi voi esimerkiksi laittaa jonkun päivän tulevaisuudesta tai saman tiedon voi lisätä monta kertaa. Tämä on erityisen ikävää, koska en ehtinyt tehdä poistotoimintoa käyttöliittymään. Jouduin päivämäärät laittamaan tietokantaan merkkijonoina sillä date ei toiminut. 

Postgresql-taulut:
CREATE TABLE Kenneli (
id SERIAL PRIMARY KEY,
nimi varchar (255),
kasvattaja varchar (255)
);

CREATE TABLE Koiranayttely (
id SERIAL PRIMARY KEY,
paivamaara varchar(10),
sijainti varchar(255)
);

CREATE TABLE Koira (
id SERIAL PRIMARY KEY,
kenneli_id integer,
nimi varchar(255),
omistaja varchar(255),
syntymaaika varchar(10),
rotu varchar(255),
FOREIGN KEY (kenneli_id) REFERENCES Kenneli(id)
);

CREATE TABLE Nayttelykoira (
id SERIAL PRIMARY KEY,
koiranayttely_id integer,
koira_id integer,
sijoitus integer,
FOREIGN KEY (koiranayttely_id) REFERENCES Koiranayttely(id),
FOREIGN KEY (koira_id) REFERENCES Koira(id)
);

(Jos minulla olisi ollut aikaa, olisin lisännyt myös siis delete-ominaisuuden ja mahdollisuuden hakea eri tietoja tietokannasta. Valitettavasti en kuitenkaan kerinnyt toteuttaa näitä ominaisuuksia.)



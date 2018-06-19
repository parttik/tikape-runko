# tikape-sovellus

Sovellus on koiratietokanta, jossa voi tarkastella ja lisätä kenneleitä, niiden koiria ja koiranäyttelyitä. Koiran ja koiranäyttelyn välillä on monesta moneen suhde, koska koirat voivat käydä monissa näyttelyissä ja niissä on monta koiraa. Koirien ja Näyttelyiden välille tuli siis Näyttelykoira-taulu, jossa on viiteavainten lisäksi tieto kyseisen koiran sijoituksesta kyseisessä näyttelyssä. Tieto koiran näyttelysijoituksista näytetään koiran omalla sivulla. Tällä hetkellä lisäys-toiminnallisuudessa ei ole mitään tarkistuksia tiedon oikeudesta vaan koiran syntymäajaksi voi esimerkiksi laittaa jonkun päivän tulevaisuudesta. 

Postgresql-taulut:
CREATE TABLE Kenneli (
id SERIAL PRIMARY KEY,
nimi varchar (255),
kasvattaja varchar (255)
);

CREATE TABLE Koiranayttely (
id SERIAL PRIMARY KEY,
paivamaara date,
sijainti varchar(255)
);

CREATE TABLE Koira (
id SERIAL PRIMARY KEY,
kenneli_id integer,
nimi varchar(255),
omistaja varchar(255),
syntymaaika date,
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

(Jos minulla olisi ollut aikaa, olisin lisännyt myös delete-ominaisuuden ja mahdollisuuden hakea eri tietoja tietokannasta. Valitettavasti en kuitenkaan kerinnyt toteuttaa näitä ominaisuuksia.)



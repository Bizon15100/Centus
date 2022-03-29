Create DATABASE centus;
Use centus;
CREATE TABLE centus (
    id INT,
    haslo VARCHAR(45),
    nazwa VARCHAR(45),
   PRIMARY KEY(id)
);

CREATE TABLE dane (
    iddane INT,
    budzet INT,
    wydatek INT,
    id INT,
    data DATE,
   PRIMARY KEY(iddane)
);

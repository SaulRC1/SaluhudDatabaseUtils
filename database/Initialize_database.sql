CREATE TABLE INGREDIENT (
    id bigint PRIMARY KEY,
    name text UNIQUE NOT NULL,
    kilocalories smallint NOT NULL,
    protein_amount smallint NOT NULL,
    carbohydrates_amount smallint NOT NULL,
    fat_amount smallint NOT NULL
);

CREATE TABLE RECIPE (
    name text,
    description text,
    
)
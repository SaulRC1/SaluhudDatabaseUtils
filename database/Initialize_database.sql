CREATE TABLE INGREDIENT (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name text UNIQUE NOT NULL,
    kilocalories smallint NOT NULL,
    protein_amount smallint NOT NULL,
    carbohydrates_amount smallint NOT NULL,
    fat_amount smallint NOT NULL
);

CREATE TABLE ALLERGENIC (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name text NOT NULL UNIQUE
);

CREATE TABLE RECIPE (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name text NOT NULL,
    description text,
    ingredients_description text
);

CREATE TABLE INGREDIENT_ALLERGENIC (
    id_ingredient bigint,
    id_allergenic bigint,
    PRIMARY KEY (id_ingredient, id_allergenic),
    FOREIGN KEY (id_ingredient) references INGREDIENT,
    FOREIGN KEY (id_allergenic) references ALLERGENIC
);

CREATE TABLE RECIPE_INGREDIENT (
    id_ingredient bigint,
    id_recipe bigint,
    PRIMARY KEY (id_ingredient, id_recipe),
    FOREIGN KEY (id_ingredient) references INGREDIENT,
    FOREIGN KEY (id_recipe) references RECIPE
);

CREATE TABLE RECIPE_ALLERGENIC (
    id_recipe bigint,
    id_allergenic bigint,
    PRIMARY KEY (id_recipe, id_allergenic),
    FOREIGN KEY (id_recipe) references RECIPE,
    FOREIGN KEY (id_allergenic) references ALLERGENIC
);

CREATE TABLE RECIPE_ELABORATION_STEP (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    step_number smallint NOT NULL,
    step_description text NOT NULL,
    id_recipe bigint NOT NULL,
    FOREIGN KEY (id_recipe) references RECIPE
);

INSERT INTO ALLERGENIC(name) VALUES ('GLUTEN');
INSERT INTO ALLERGENIC(name) VALUES ('CRUSTACEAN');
INSERT INTO ALLERGENIC(name) VALUES ('EGG');
INSERT INTO ALLERGENIC(name) VALUES ('FISH');
INSERT INTO ALLERGENIC(name) VALUES ('PEANUT');
INSERT INTO ALLERGENIC(name) VALUES ('SOYA_BEAN');
INSERT INTO ALLERGENIC(name) VALUES ('CELERY');
INSERT INTO ALLERGENIC(name) VALUES ('MUSTARD');
INSERT INTO ALLERGENIC(name) VALUES ('SESAME_SEED');
INSERT INTO ALLERGENIC(name) VALUES ('SULFUR_DIOXIDE_AND_SULPHITES');
INSERT INTO ALLERGENIC(name) VALUES ('LUPIN_BEAN');
INSERT INTO ALLERGENIC(name) VALUES ('NUTS');
INSERT INTO ALLERGENIC(name) VALUES ('MOLLUSK');
INSERT INTO ALLERGENIC(name) VALUES ('LACTOSE');

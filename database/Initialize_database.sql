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
    FOREIGN KEY (id_ingredient) references INGREDIENT(id),
    FOREIGN KEY (id_allergenic) references ALLERGENIC(id)
);

CREATE TABLE RECIPE_INGREDIENT (
    id_ingredient bigint,
    id_recipe bigint,
    PRIMARY KEY (id_ingredient, id_recipe),
    FOREIGN KEY (id_ingredient) references INGREDIENT(id),
    FOREIGN KEY (id_recipe) references RECIPE(id)
);

CREATE TABLE RECIPE_ALLERGENIC (
    id_recipe bigint,
    id_allergenic bigint,
    PRIMARY KEY (id_recipe, id_allergenic),
    FOREIGN KEY (id_recipe) references RECIPE(id),
    FOREIGN KEY (id_allergenic) references ALLERGENIC(id)
);

CREATE TABLE RECIPE_ELABORATION_STEP (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    step_number smallint NOT NULL,
    step_description text NOT NULL,
    id_recipe bigint NOT NULL,
    FOREIGN KEY (id_recipe) references RECIPE(id)
);

-- Crea la tabla de datos de usuario de fitness
CREATE TABLE SALUHUD_USER_FITNESS_DATA (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    weight real,
    height real,
    biological_sex BIOLOGICAL_SEX,
    age smallint,
    body_composition text,
    recommended_daily_water_liters smallint,
    recommended_sleep_hours smallint,
    recommended_daily_steps smallint,
    daily_kilocalories_objective smallint,
    body_mass_index text
);

-- Crea la tabla de datos personales del usuario
CREATE TABLE SALUHUD_USER_PERSONAL_DATA (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_fitness_data_id bigint NOT NULL,
    name text NOT NULL,
    surname text,
    phone_number text UNIQUE,
    FOREIGN KEY (user_fitness_data_id) REFERENCES SALUHUD_USER_FITNESS_DATA(id)
);

-- Crea la tabla del historial de sueño
CREATE TABLE SLEEP_HISTORICAL (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES SALUHUD_USER_PERSONAL_DATA(id)
);

-- Crea la tabla de entrada del historial de sueño
CREATE TABLE SLEEP_HISTORICAL_ENTRY (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    sleep_historical_id bigint NOT NULL,
    date date NOT NULL,
    hours_slept smallint NOT NULL,
    minutes_slept smallint NOT NULL,
    sleep_evaluation HISTORICAL_EVALUATION NOT NULL,
    FOREIGN KEY (sleep_historical_id) REFERENCES SLEEP_HISTORICAL(id)
);

-- Crea la tabla del historial de pasos diarios
CREATE TABLE DAILY_STEPS_HISTORICAL (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES SALUHUD_USER_PERSONAL_DATA(id)
);

-- Crea la tabla de entrada del historial de pasos diarios
CREATE TABLE DAILY_STEPS_HISTORICAL_ENTRY (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    daily_steps_historical_id bigint NOT NULL,
    date date NOT NULL,
    done_steps smallint NOT NULL,
    kilo_calories_burned real NOT NULL,
    steps_evaluation HISTORICAL_EVALUATION NOT NULL,
    FOREIGN KEY (daily_steps_historical_id) REFERENCES DAILY_STEPS_HISTORICAL(id)
);

-- Crea la tabla del historial de peso
CREATE TABLE WEIGHT_HISTORICAL (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES SALUHUD_USER_PERSONAL_DATA(id)
);

-- Crea la tabla de entrada del historial de peso
CREATE TABLE WEIGHT_HISTORICAL_ENTRY ( 
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    weight_historical_id bigint NOT NULL,
    date date NOT NULL,
    weight_entry real NOT NULL,
    height_entry real NOT NULL,
    kilo_calories_objective_entry real NOT NULL,
    FOREIGN KEY (weight_historical_id) REFERENCES WEIGHT_HISTORICAL(id)
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
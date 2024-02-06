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
    step_description text NOT NULL
);

CREATE TABLE RECIPE_RECIPE_ELABORATION_STEP (
    id_elaboration_step bigint,
    id_recipe bigint,
    PRIMARY KEY (id_elaboration_step, id_recipe),
    FOREIGN KEY (id_elaboration_step) references RECIPE_ELABORATION_STEP(id),
    FOREIGN KEY (id_recipe) references RECIPE(id)
)

CREATE TABLE FORUM (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name text NOT NULL,
    description text NOT NULL
);

CREATE TABLE FORUM_THREAD (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_forum bigint NOT NULL,
    title text NOT NULL,
    author text NOT NULL,
    created_date timestamp NOT NULL,
    FOREIGN KEY (id_forum) references FORUM(id)
);

CREATE TABLE FORUM_MESSAGE (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_forum_thread bigint NOT NULL,
    author text NOT NULL,
    message_date timestamp NOT NULL,
    message_content text NOT NULL,
    FOREIGN KEY (id_forum_thread) references FORUM_THREAD(id)
);

CREATE TABLE SALUHUD_USER_FITNESS_DATA (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    weight real,
    height real,
    biological_sex text,
    age smallint,
    body_composition text,
    recommended_daily_water_liters smallint,
    recommended_sleep_hours smallint,
    recommended_daily_steps smallint,
    daily_kilocalories_objective smallint,
    body_mass_index text
);

CREATE TABLE SALUHUD_USER_PERSONAL_DATA (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name text NOT NULL,
    surname text,
    phone_number text UNIQUE
);

CREATE TABLE SALUHUD_USER (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username text NOT NULL,
    password text NOT NULL,
    email text NOT NULL UNIQUE
);

CREATE TABLE SALUHUD_USER_PERSONAL_DATA_SALUHUD_USER (
    id_saluhud_user bigint,
    id_user_personal_data bigint NOT NULL UNIQUE,
    PRIMARY KEY (id_saluhud_user),
    FOREIGN KEY (id_user_personal_data) references SALUHUD_USER_PERSONAL_DATA(id),
    FOREIGN KEY (id_saluhud_user) references SALUHUD_USER(id)
);

CREATE TABLE SALUHUD_USER_SALUHUD_USER_FITNESS_DATA (
    id_saluhud_user bigint,
    id_user_fitness_data bigint NOT NULL UNIQUE,
    PRIMARY KEY (id_saluhud_user),
    FOREIGN KEY (id_user_fitness_data) references SALUHUD_USER_FITNESS_DATA(id),
    FOREIGN KEY (id_saluhud_user) references SALUHUD_USER(id)
);

-- Crea la tabla del historial de sueño
CREATE TABLE SLEEP_HISTORICAL (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES SALUHUD_USER(id)
);

-- Crea la tabla de entrada del historial de sueño
CREATE TABLE SLEEP_HISTORICAL_ENTRY (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    sleep_historical_id bigint NOT NULL,
    entry_date date NOT NULL,
    hours_slept smallint NOT NULL,
    minutes_slept smallint NOT NULL,
    sleep_evaluation HISTORICAL_EVALUATION NOT NULL,
    FOREIGN KEY (sleep_historical_id) REFERENCES SLEEP_HISTORICAL(id)
);

-- Crea la tabla del historial de pasos diarios
CREATE TABLE DAILY_STEPS_HISTORICAL (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES SALUHUD_USER(id)
);

-- Crea la tabla de entrada del historial de pasos diarios
CREATE TABLE DAILY_STEPS_HISTORICAL_ENTRY (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    daily_steps_historical_id bigint NOT NULL,
    entry_date date NOT NULL,
    done_steps smallint NOT NULL,
    kilo_calories_burned real NOT NULL,
    steps_evaluation HISTORICAL_EVALUATION NOT NULL,
    FOREIGN KEY (daily_steps_historical_id) REFERENCES DAILY_STEPS_HISTORICAL(id)
);

-- Crea la tabla del historial de peso
CREATE TABLE WEIGHT_HISTORICAL (
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id bigint NOT NULL,
    FOREIGN KEY (user_id) REFERENCES SALUHUD_USER(id)
);

-- Crea la tabla de entrada del historial de peso
CREATE TABLE WEIGHT_HISTORICAL_ENTRY ( 
    id bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    weight_historical_id bigint NOT NULL,
    entry_date date NOT NULL,
    weight_entry real NOT NULL,
    height_entry real NOT NULL,
    kilo_calories_objective_entry real NOT NULL,
    FOREIGN KEY (weight_historical_id) REFERENCES WEIGHT_HISTORICAL(id)
);

CREATE TYPE HISTORICAL_EVALUATION AS
ENUM('EXCELLENT','WELL', 'MINIMUM', 'FAILED');

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
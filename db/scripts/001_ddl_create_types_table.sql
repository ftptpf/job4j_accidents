CREATE TABLE IF NOT EXISTS types (
    id SERIAL PRIMARY KEY ,
    name TEXT UNIQUE NOT NULL
);

COMMENT ON TABLE types IS 'Типы инцидентов';
COMMENT ON COLUMN types.id IS 'Идентификатор типа';
COMMENT ON COLUMN types.name IS 'Наименование типа';
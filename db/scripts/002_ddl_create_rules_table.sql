CREATE TABLE IF NOT EXISTS rules (
    id SERIAL PRIMARY KEY ,
    name TEXT UNIQUE NOT NULL
);

COMMENT ON TABLE rules IS 'Стати нарушений';
COMMENT ON COLUMN rules.id IS 'Идентификатор статьи';
COMMENT ON COLUMN rules.name IS 'Наименование статьи';
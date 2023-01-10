CREATE TABLE IF NOT EXISTS accidents (
    id SERIAL PRIMARY KEY ,
    name TEXT ,
    description TEXT ,
    address TEXT ,
    type_id INTEGER REFERENCES types (id)
);

COMMENT ON TABLE accidents IS 'Автомобильные инциденты';
COMMENT ON COLUMN accidents.id IS 'Идентификатор инцидента';
COMMENT ON COLUMN accidents.name IS 'Наименование инцидента';
COMMENT ON COLUMN accidents.description IS 'Описание инцидента';
COMMENT ON COLUMN accidents.address IS 'Адрес инцидента';
COMMENT ON COLUMN accidents.type_id IS 'Идентификатор инцидента. Many-to-One';

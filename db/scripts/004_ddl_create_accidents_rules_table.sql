CREATE TABLE accidents_rules (
    accident_id INTEGER REFERENCES accidents (id) ON DELETE CASCADE ,
    rule_id INTEGER REFERENCES rules (id) ,
    PRIMARY KEY (accident_id, rule_id)
);

COMMENT ON TABLE accidents_rules IS 'Связь Many-to-Many таблицы Автомобильных инцидентов и Статей нарушений';
COMMENT ON COLUMN accidents_rules.accident_id IS 'Идентификатор таблицы автомобильных инцидентов';
COMMENT ON COLUMN accidents_rules.rule_id IS 'Идентификаторы таблицы статей нарушения';
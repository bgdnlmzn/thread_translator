CREATE TABLE translation_requests
(
    id              UUID PRIMARY KEY,
    ip_address      VARCHAR(45),
    source_lang     VARCHAR(255),
    target_lang     VARCHAR(255),
    input_text      VARCHAR(255),
    translated_text VARCHAR(255)
);


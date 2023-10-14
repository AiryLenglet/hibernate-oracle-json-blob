CREATE TABLE client (
  id NUMBER(19, 0) NOT NULL PRIMARY KEY,
  business_id VARCHAR2(8 CHAR) NOT NULL,
  properties BLOB
  CONSTRAINT ENSURE_JSON CHECK (properties IS JSON)
)
LOB (properties) STORE AS (CACHE);

INSERT INTO client (
    id,
    business_id,
    properties
) VALUES (
    1,
    '09709564',
    '{
        "name": "Morrison",
        "firstname": "Jim"
    }'
);
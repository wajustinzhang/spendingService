-- Table: spending.receipt

DROP TABLE spending.receipt;

CREATE TABLE spending.receipt
(
  "ID" uuid, -- the receipt id
  "OWNER_UUID" uuid, -- the owner of this receipt
  "CATEGORY_ID" integer, -- spend category
  "S_LOCATION" text, -- Where the money spent
  "S_TIME" date, -- spend time
  "S_REASON" text, -- reason for the spending
  "S_AMOUNT" numeric(12,2), -- The amout spend
  "S_TIP" numeric(5,2),
  "P_METHOD" integer -- paid by cash or credit card
)
WITH (
  OIDS=FALSE
);
ALTER TABLE spending.receipt
  OWNER TO dev;
COMMENT ON COLUMN spending.receipt."ID" IS 'the receipt id';
COMMENT ON COLUMN spending.receipt."OWNER_UUID" IS 'the owner of this receipt';
COMMENT ON COLUMN spending.receipt."CATEGORY_ID" IS 'spend category';
COMMENT ON COLUMN spending.receipt."S_LOCATION" IS 'Where the money spent';
COMMENT ON COLUMN spending.receipt."S_TIME" IS 'spend time';
COMMENT ON COLUMN spending.receipt."S_REASON" IS 'reason for the spending';
COMMENT ON COLUMN spending.receipt."S_AMOUNT" IS 'The amout spend';
COMMENT ON COLUMN spending.receipt."P_METHOD" IS 'paid by cash or credit card';

-- Table: spending.receipt_category

DROP TABLE spending.receipt_category;

CREATE TABLE spending.category_lookup
(
  "ID" integer, -- the id of the receipt category
  "NAME" text -- Category name
)
WITH (
  OIDS=FALSE
);
ALTER TABLE spending.category_lookup
  OWNER TO postgres;
COMMENT ON COLUMN spending.category_lookup."ID" IS 'the id of the receipt category';
COMMENT ON COLUMN spending.category_lookup."NAME" IS 'Category name';

DROP TABLE spending.paymethod_lookup;
CREATE TABLE spending.paymethod_lookup
(
  "ID" integer, -- the id of the receipt category
  "NAME" text -- Category name
)
WITH (
  OIDS=FALSE
);
ALTER TABLE spending.paymethod_lookup
  OWNER TO postgres;
COMMENT ON COLUMN spending.paymethod_lookup."ID" IS 'the id of the receipt category';
COMMENT ON COLUMN spending.paymethod_lookup."NAME" IS 'Category name';

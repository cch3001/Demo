drop TABLE CURRENCY ;

CREATE TABLE CURRENCY (
ID BIGINT NOT NULL,
CODE VARCHAR(6),
SYMBOL VARCHAR(10),
RATE VARCHAR(20),
DESCRIPTION VARCHAR(100),
RATE_FLOAT NUMERIC(20,4),
CHINESE_NAME VARCHAR(50),
UPD_DATE TIMESTAMP,
 CONSTRAINT PK_Person PRIMARY KEY (ID),
 UNIQUE (CODE)
);

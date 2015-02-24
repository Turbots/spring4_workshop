create table beer (
  id BIGINT NOT NULL IDENTITY,
  name VARCHAR(20) NOT NULL,
  description VARCHAR(200) NOT NULL,
  alcoholPercentage DECIMAL(10,2) NOT NULL,
  modifiedTimestamp TIMESTAMP NOT NULL
);
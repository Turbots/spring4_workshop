create table beer (
  id BIGINT NOT NULL IDENTITY PRIMARY KEY,
  name VARCHAR(20),
  description VARCHAR(200),
  alcoholPercentage DOUBLE
);
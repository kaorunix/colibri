CREATE TABLE LOGIN_USER
(login_id int8 NOT NULL CONSTRAINT pkey PRIMARY KEY,
 login_nm varchar(16) NOT NULL,
 organization_nm varchar(16) NOT NULL,
 logout_dt timestamptz,
 password varchar(16),
 lang_id int8,
 CONSTRAINT fkey_login_user FOREIGN KEY (organization_nm) REFERENCES organization(organization_nm)
);

CREATE INDEX idx_login_user
ON LOGIN_USER(login_nm,organization_nm);
CREATE TABLE ORGANIZATION
(organization_nm varchar(16) NOT NULL CONSTRAINT pkey_organization PRIMARY KEY,
 organization_full_nm varchar(64),
 status int,
 delete_fg int
);

CREATE INDEX idx_organization
ON ORGANIZATION(organization_nm);


CREATE TABLE OPERATION
(operation_nm varchar(64) NOT NULL,
 status int NOT NULL,
 organization_nm varchar(16) NOT NULL,
 user_nm varchar(16) NOT NULL,
 modify_dt int,
 description varchar(1024),
 CONSTRAINT pkey_operation PRIMARY KEY (operation_nm, organization_nm),
 CONSTRAINT fkey FOREIGN KEY (user_nm) REFERENCES login_user(user_nm),
 CONSTRAINT fkey FOREIGN KEY (organization_nm) REFERENCES organization(organization_nm)
 );

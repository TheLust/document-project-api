INSERT INTO role(name)
VALUES
    ('Amministratore'),
    ('Operatore Cedacri'),
    ('Operatore Bancare');
-----------------------------------------------------------------------------------------
INSERT INTO document_type (code, name, description, macro_id, is_date_grouped)
VALUES
    ('RPSRV', 'Report di Servizio', 'Report sul servizio per periodo', null,true),
    ('RPSLA', 'Report SLA', 'Report SLA per periodo',null,true),
    ('RPPRG','Progettazione', 'Documentazione per progetti',null,false),
    ('NETWK','Network', 'Network',1,false),
    ('SICRZ','Sicurezza', 'Sicurezza',1,false),
    ('CHNGE','Change', 'Change',1,false),
    ('BCKUP','Backup', 'Backup',1,false),
    ('ANLIS','Analisi', 'Analisi',3,false),
    ('TRNSZ','Transizione', 'Transizione',3,false),
    ('PRDZN','Produzione', 'Produzione',3,false),
    ('TEST','Test', 'Test',3,false),
    ('MNTRG','Monitoraggio', 'Monitoraggio',3,false);
-------------------------------------------------------------------------------------------
-- INSERT DEFAULT ADMIN !!! TO DISABLE ACCOUNT AFTER !!!
-- DEFAULT Account - user: 'admin' password: 'Admin' WITHOUT '
INSERT INTO _user (institution_id, username, password, email, is_enabled, name, surname, patronymic)
VALUES
    (NULL, 'admin','e3afed0047b08059d0fada10f400c1e5','admin@admin.admin',true,'Admin','Admin','Admin');
---------------
INSERT INTO _user_roles
VALUES
    (
        (SELECT MAX(Id) FROM _user),
        (SELECT Id FROM role WHERE Name = 'Amministratore')
    );
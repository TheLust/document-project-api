INSERT INTO role(name)
VALUES
    ('Amministratore'),
    ('Operatore Cedacri'),
    ('Operatore Bancare');
-----------------------------------------------------------------------------------------
INSERT INTO document_type (code, name, description, is_macro, is_date_grouped)
VALUES
    ('RPSRV', 'Report di Servizio', 'Report sul servizio per periodo', true,true),
    ('RPSLA', 'Report SLA', 'Report SLA per periodo',true,true),
    ('RPPRG','Progettazione', 'Documentazione per progetti',true,false),
    ('NETWK','Network', 'Network',false,false),
    ('SICRZ','Sicurezza', 'Sicurezza',false,false),
    ('CHNGE','Change', 'Change',false,false),
    ('BCKUP','Backup', 'Backup',false,false),
    ('ANLIS','Analisi', 'Analisi',false,false),
    ('TRNSZ','Transizione', 'Transizione',false,false),
    ('PRDZN','Produzione', 'Produzione',false,false),
    ('TEST','Test', 'Test',false,false),
    ('MNTRG','Monitoraggio', 'Monitoraggio',false,false);
-----------------------------------------------------------------------------------------
INSERT INTO document_type_hierarchy(macro_id, micro_id) VALUES
                                       (
                                           (SELECT id FROM document_type WHERE code = 'RPSRV'),
                                           (SELECT id FROM document_type WHERE code = 'NETWK')
                                       ),
                                       (
                                           (SELECT id FROM document_type WHERE code = 'RPSRV'),
                                           (SELECT id FROM document_type WHERE code = 'SICRZ')
                                       ),
                                       (
                                           (SELECT id FROM document_type WHERE code = 'RPSRV'),
                                           (SELECT id FROM document_type WHERE code = 'CHNGE')
                                       ),
                                       (
                                           (SELECT id FROM document_type WHERE code = 'RPSRV'),
                                           (SELECT id FROM document_type WHERE code = 'BCKUP')
                                       ),
                                       (
                                           (SELECT id FROM document_type WHERE code = 'RPPRG'),
                                           (SELECT id FROM document_type WHERE code = 'ANLIS')
                                       ),
                                       (
                                           (SELECT id FROM document_type WHERE code = 'RPPRG'),
                                           (SELECT id FROM document_type WHERE code = 'TRNSZ')
                                       ),
                                       (
                                           (SELECT id FROM document_type WHERE code = 'RPPRG'),
                                           (SELECT id FROM document_type WHERE code = 'PRDZN')
                                       ),
                                       (
                                           (SELECT id FROM document_type WHERE code = 'RPPRG'),
                                           (SELECT id FROM document_type WHERE code = 'TEST')
                                       ),
                                       (
                                           (SELECT id FROM document_type WHERE code = 'RPPRG'),
                                           (SELECT id FROM document_type WHERE code = 'MNTRG')
                                       );
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
insert into service(service_id, name, description) values(1, 'foo', 'Foo Application');
insert into service(service_id, name, description) values(2, 'bar', 'Bar Aplication');
insert into service(service_id, name, description) values(3, 'sample', 'Sample Aplication');

insert into profile(profile_id, name, description) values(1, 'default', 'Default Profile');
insert into profile(profile_id, name, description) values(2, 'cloud', 'Cloud Profile');

insert into service_instance(instance_id, service_id, name, host, port, admin_path) values(1, 1, 'localhost', '127.0.0.1', 8888, '/admin');


insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(1, 1, 'map', null, 1, 'test', null);
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(2, 1, 'prop', 1, 1, 'test1', 'value1');
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(3, 1, 'prop', 1, 1, 'test2', 'value2');
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(4, 1, 'map', null, 3, 'server', null);
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(5, 1, 'prop', 4, 3, 'port', '8889');
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(6, 1, 'map', null, 3, 'management', null);
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(7, 1, 'prop', 6, 3, 'context-path', '/manage');
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(8, 1, 'map', null, 3, 'rest', null);
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(9, 1, 'map', 8, 3, 'client', null);
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(10, 1, 'prop', 9, 3, 'uri', 'http://localhost:8888/api');
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(11, 1, 'prop', 9, 3, 'registrationInterval', '30000');
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(12, 2, 'map', null, 3, 'spring', null);
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(13, 2, 'map', 12, 3, 'cloud', null);
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(14, 2, 'prop', 13, 3, 'serviceUrl', 'http://localhost:8888/api/services');
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(15, 2, 'prop', 13, 3, 'registerInterval', '60');

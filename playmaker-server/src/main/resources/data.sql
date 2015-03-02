insert into service(service_id, name, description) values(1, 'foo', 'Foo Application');
insert into service(service_id, name, description) values(2, 'bar', 'Bar Aplication');

insert into profile(profile_id, name, description) values(1, 'default', 'Default Profile');
insert into profile(profile_id, name, description) values(2, 'dev', 'Development Profile');

insert into service_instance(instance_id, service_id, name, host, port, admin_path) values(1, 1, 'localhost', '127.0.0.1', 8888, '/admin');

insert into service_property(service_id, property, value) values(1, 'test1', 'value1');
insert into service_property(service_id, property, value) values(1, 'test2', 'value2');
insert into service_property(service_id, property, value) values(2, 'test1', 'value3');
insert into service_property(service_id, property, value) values(2, 'test2', 'value4');


insert into service_profile(service_profile_id, service_id, profile_id) values(1, 1, 1);

insert into service_profile_property(service_profile_id, property, value) values(1, 'test1', 'value5');
insert into service_profile_property(service_profile_id, property, value) values(1, 'test3', 'value6');

insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(1, 1, 'map', null, 1, 'test', null);
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(2, 1, 'prop', 1, 1, 'test1', 'value1');
insert into config(config_id, profile_id, ctype, parent_id, service_id, name, value) values(3, 1, 'prop', 1, 1, 'test2', 'value2');

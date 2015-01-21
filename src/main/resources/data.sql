insert into service(service_id, name, description) values(1, 'foo', 'Foo Application');
insert into service(service_id, name, description) values(2, 'bar', 'Bar Aplication');

insert into profile(profile_id, name, description) values(1, 'dev', 'Development Profile');

insert into service_property(service_id, property, value) values(1, 'test1', 'value1');
insert into service_property(service_id, property, value) values(1, 'test2', 'value2');
insert into service_property(service_id, property, value) values(2, 'test1', 'value3');
insert into service_property(service_id, property, value) values(2, 'test2', 'value4');


insert into service_profile(service_profile_id, service_id, profile_id) values(1, 1, 1);

insert into service_profile_property(service_profile_id, property, value) values(1, 'test1', 'value5');
insert into service_profile_property(service_profile_id, property, value) values(1, 'test3', 'value6');

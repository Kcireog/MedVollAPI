alter table medicos add activo tinyint;
-- actualizar los medicos que ya se encontraban registrados
update medicos set activo=1
create trigger asignaPer_AI after insert on permiso for each row 

insert into asignaPer (Rol_id,permiso_id,leer,nuevo,modificar,eliminar)
values (1,new.id,1,1,1,1);
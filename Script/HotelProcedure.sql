

  -- Procedimientos de la tabla Rol

delimiter $$
create procedure inRol (in rNombre VARCHAR(45))
begin
insert into rol (nombre) values (rNombre);
end $$
delimiter $$

delimiter $$
create procedure moRol (in rId INT, in rNombre VARCHAR(45))
begin
update rol SET nombre=rNombre where id = rId;
end $$
delimiter $$

delimiter $$
create procedure elRol (in rId INT)
begin
delete from rol where id = rId;
end $$
delimiter $$

delimiter $$
create procedure liRol ()
begin
select id, nombre from rol;
end $$
delimiter $$

delimiter $$
create procedure coRol (in rId INT)
begin
select id, nombre from rol where id = rId;
end $$
delimiter $$

  -- Procedimientos de la tabla Usuario

delimiter $$
create procedure inUsuario (in uCedula VARCHAR(10), in uNombre VARCHAR(45), in uPass VARCHAR(255), in uRol_id INT)
begin
insert into usuario (cedula,nombre,pass,Rol_id) values (uCedula,uNombre,SHA(uPass),uRol_id);
end $$
delimiter $$

delimiter $$
create procedure moUsuario (in uCedula VARCHAR(10), in uNombre VARCHAR(45), in uPass VARCHAR(255), in uRol_id INT)
begin
update usuario SET cedula=uCedula,nombre=uNombre,pass=uPass,Rol_id=uRol_id where cedula=uCedula;
end $$
delimiter $$

delimiter $$
create procedure elUsuario (in uCedula VARCHAR(10))
begin
delete from usuario where cedula=uCedula;
end $$
delimiter $$

delimiter $$
create procedure liUsuario ()
begin
select u.cedula,u.nombre,u.Rol_id, r.nombre as rol
from usuario as u
inner join rol as r on u.Rol_id = r.id
order by u.nombre;
end $$
delimiter $$

delimiter $$
create procedure coUsuario (in uCedula VARCHAR(10))
begin
select cedula,nombre,pass,Rol_id from usuario where cedula=uCedula;
end $$
delimiter $$

  -- Procedimientos de la tabla permiso

delimiter $$
create procedure inPermiso (in pModulo VARCHAR(45), in pTabla VARCHAR(45), in pUrl VARCHAR(45))
begin
insert into permiso (modulo,tabla,url) values (pModulo,pTabla,pUrl);
end $$
delimiter $$

delimiter $$
create procedure moPermiso (in pId INT, in pModulo VARCHAR(45), in pTabla VARCHAR(45), in pUrl VARCHAR(45))
begin
update permiso SET modulo=pModulo, tabla=pTabla,url=pUrl where id=pId;
end $$
delimiter $$

delimiter $$
create procedure elPermiso (in pId INT)
begin
delete from permiso where id=pId;
end $$
delimiter $$

delimiter $$
create procedure liPermiso ()
begin
select id, modulo,tabla,url from permiso;
end $$
delimiter $$

delimiter $$
create procedure coPermiso (in pId INT)
begin
select id, modulo,tabla,url from permiso where id=pId;
end $$
delimiter $$

delimiter $$
create procedure Login (in uCedula VARCHAR(10),in uPass VARCHAR(255))
begin
select distinct u.cedula, u.nombre, u.Rol_id, r.nombre as rol
from usuario as u
inner join rol as r on u.Rol_id = r.id
where u.cedula = uCedula and u.pass=sha(uPass);
end $$
delimiter $$

delimiter $$
create procedure perMenu (in uCedula VARCHAR(10))
begin
select distinct p.modulo, p.tabla, p.url
from usuario as u
inner join rol as r on u.Rol_id = r.id
inner join asignaPer as ap on r.id = ap.Rol_id
inner join permiso as p on ap.permiso_id = p.id
where ap.leer > 0 and u.cedula = uCedula;
end $$
delimiter $$

-- Procedimientos de la tabla asignaPer 

delimiter $$
create procedure inAsignaPer (in aRol_id INT, in aPermiso_id INT, in aLeer INT, in aNuevo tinyint,in aModificar tinyint,in aEliminar tinyint)
begin
insert into asignaPer (Rol_id,permiso_id,leer,nuevo,modificar,eliminar)
values (aRol_id,aPermiso_id,aLeer,aNuevo,aModificar,aEliminar);
end $$
delimiter $$
  
delimiter $$
create procedure moAsignaPer (in aRol_id INT, in aPermiso_id INT, in aLeer tinyint, in aNuevo tinyint,in aModificar tinyint,in aEliminar tinyint)
begin
update asignaPer SET Rol_id= aRol_id, permiso_id=aPermiso_id, leer=aLeer, nuevo=aNuevo, modificar=aModificar, eliminar=aEliminar where Rol_id= aRol_id and permiso_id=aPermiso_id;
end $$
delimiter $$

delimiter $$
create procedure elAsignaPer (in aRol_id INT, in aPermiso_id INT)
begin
delete from asignaPer where Rol_id= aRol_id and permiso_id=aPermiso_id;
end $$
delimiter $$

delimiter $$
create procedure liAsignaPer (in apRol int)
begin
select  distinct r.id as Rol_id, r.nombre as rNombre, p.id as Per_id, p.tabla, ap.leer,ap.nuevo,ap.modificar,ap.eliminar 
from rol as r
inner join asignaPer as ap on r.id = ap.Rol_id 
inner join permiso as p on ap.permiso_id = p.id
where  ap.Rol_id = apRol
order by p.modulo desc, p.id asc;
end $$
delimiter $$

delimiter $$
create procedure seAsignaPer (in seCedula Varchar(11))
begin
select p.tabla,ap.leer,ap.nuevo,ap.modificar,ap.eliminar 
from permiso as p 
inner join asignaPer as ap on p.id = ap.permiso_id
where ap.Rol_id=(select Rol_id from usuario where cedula = seCedula);
end$$
delimiter $$

delimiter $$
create procedure coAsignaPer (in aRol_id INT, in aPermiso_id INT)
begin
select Rol_id,permiso_id,leer,nuevo,modificar,eliminar from asignaPer where Rol_id= aRol_id and permiso_id=aPermiso_id;
end $$
delimiter $$

-- Procedimientos de la tabla Habitacion 

delimiter $$
create procedure inHabita (in hNumero CHAR(4), in hTarifa FLOAT(10,2))
begin
insert into habita (numero,tarifa)
values (hNumero,hTarifa);
end $$
delimiter $$
  
delimiter $$
create procedure moHabita (in hNumero CHAR(4), in hTarifa FLOAT(10,2))
begin
update habita SET numero= hNumero, tarifa=hTarifa where numero = hNumero;
end $$
delimiter $$

delimiter $$
create procedure elHabita (in hNumero CHAR(4))
begin
delete from habita where numero = hNumero;
end $$
delimiter $$

delimiter $$
create procedure liHabita ()
begin
select numero, tarifa from habita;
end $$
delimiter $$

delimiter $$
create procedure coHabita (in hNumero CHAR(4))
begin
select numero, tarifa from habita where numero = hNumero;
end $$
delimiter $$

delimiter $$
CREATE PROCEDURE disponible (in fIngreso date, in fEgreso date)
begin
Select habita.numero, habita.tarifa 
from habita
where habita.numero not in(select h.numero 
from habita as h
inner join asigna as a on h.numero = a.numero_hab
inner join reserva as r on a.codigo_res = r.codigo
where (fIngreso between r.fecha_ingreso and r.fecha_egreso) or  (fEgreso between r.fecha_ingreso and r.fecha_egreso));
end
delimiter $$

delimiter $$
create procedure lisReserva()
begin
Select r.codigo, h.numero, r.fecha_ingreso, r.fecha_egreso, u.cedula, u.nombre, r.num_personas
from habita as h
inner join asigna as a on  h.numero = a.numero_hab
inner join reserva as r on a.codigo_res = r.codigo
inner join usuario as u on r.usuario_cedula = u.cedula;
end $$
delimiter $$



-- procedimientos de Servicio

delimiter $$
create procedure inServicio (in sNombre varchar(50),in sTarifa FLOAT(10,2))
begin
insert into servicio (nombre,tarifa)
values (sNombre,sTarifa);
end $$
delimiter $$

delimiter $$
create procedure moServicio (in sId INT(11), in sNombre varchar(50),in sTarifa FLOAT(10,2))
begin
update servicio SET id = sId, nombre = sNombre, tarifa = sTarifa where id = sId;
end $$
delimiter $$

delimiter $$
create procedure elServicio (in sId INT(11))
begin
delete from servicio where id = sId;
end $$
delimiter $$

delimiter $$
create procedure liServicio ()
begin
select id, nombre, tarifa from servicio;
end $$
delimiter $$

delimiter $$
create procedure coServicio (in sId INT(11))
begin
select id, nombre, tarifa from servicio where id = sId;
end $$
delimiter $$

-- procedimientos de Reserva

delimiter $$
create procedure inReserva (in rFecha_ingreso DATE, in rFecha_egreso DATE, in rUsuario_cedula INT, in rNum_personas int(3))
begin
insert into reserva(fecha_ingreso,fecha_egreso,usuario_cedula,num_personas)
values (rFecha_ingreso,rFecha_egreso,rUsuario_cedula,rNum_personas);
end $$
delimiter $$

delimiter $$
create procedure moReserva (in rCodigo INT(11), in rFecha_ingreso DATE, in rFecha_egreso DATE, in rUsuario_cedula INT, in rNum_personas int(3))
begin
update reserva SET fecha_ingreso=rFecha_ingreso, fecha_egreso=rFecha_egreso, usuario_cedula=rUsuario_cedula,num_personas=rNum_personas where codigo = rCodigo;
end $$
delimiter $$

delimiter $$
create procedure elReserva (in rCodigo INT(11))
begin
delete from reserva where codigo = rCodigo;
end $$
delimiter $$

delimiter $$
create procedure liReserva ()
begin
select r.codigo,r.fecha_ingreso,r.fecha_egreso,u.cedula,u.nombre,r.num_personas 
from usuario as u
inner join reserva as r on u.cedula = r.usuario_cedula;
end $$
delimiter $$

delimiter $$
create procedure coReserva (in rCodigo INT(11))
begin
select r.codigo,r.fecha_ingreso,r.fecha_egreso,u.cedula,u.nombre,r.num_personas 
from usuario as u
inner join reserva as r on u.cedula = r.Usuario_cedula
where codigo = rCodigo;
end $$
delimiter $$

-- procedimientos de Asigna

delimiter $$
create procedure inAsigna (in aNumero_hab char(4), in codigo_res INT(11), in num_ad int(2), in num_ni int(2))
begin
insert into asigna (numero_hab,codigo_res,num_ad,num_ni)
values (aNumero_hab,codigo_res,num_ad,num_ni);
end $$
delimiter $$

delimiter $$
create procedure moAsigna (in aNumero_hab char(4), in aCodigo_res INT(11), in aNum_ad int(2), in aNum_ni int(2))
begin
update asigna SET id = aNumero_hab, codigo_res = aCodigo_res, aNum_ad = num_ad, aNum_ni = num_ni where numero_hab = aNumero_hab;
end $$
delimiter $$

delimiter $$
create procedure elAsigna (in aCodigo_res INT(11))
begin
delete from asigna where Codigo_res = aCodigo_res;
end $$
delimiter $$

delimiter $$
create procedure liAsigna ()
begin
select numero_hab,codigo_res,num_ad,num_ni from asigna;
end $$
delimiter $$

delimiter $$
create procedure coAsigna (in aCodigo_res INT(11))
begin
select numero_hab,codigo_res,num_ad,num_ni from asigna where Codigo_res = aCodigo_res;
end $$
delimiter $$

-- procedimientos de consumo

delimiter $$
create procedure inConsumo (in cNumero_hab char(4), in cCodigo_res INT(11), in cId_servicio INT(11), in cFecha_hora datetime)
begin
insert into consumo (numero_hab,codigo_res,id_servicio,fecha_hora)
values (cNumero_hab,cCodigo_res,cId_servicio,cFecha_hora);
end $$
delimiter $$

delimiter $$
create procedure moConsumo (in cId_cons int, in cNumero_hab char(4), in cCodigo_res INT(11), in cId_servicio INT(11), in cFecha_hora datetime)
begin
update consumo SET numero_hab = cNmero_hab, codigo_res = cCodigo_res, id_servicio = cId_servicio, fecha_hora = cFecha_hora where id_cons = cId_cons;
end $$
delimiter $$

delimiter $$
create procedure elConsumo (in cId_cons int)
begin
delete from consumo where id_cons = cId_cons;
end $$
delimiter $$

delimiter $$
create procedure liConsumo ()
begin
select id_cons,numero_hab,codigo_res,id_servicio,fecha_hora from consumo;
end $$
delimiter $$

delimiter $$
create procedure liDetConsumo ()
begin
select c.id_cons,  c.codigo_res, c.numero_hab, s.nombre, s.tarifa, c.fecha_hora
from consumo as c 
inner join servicio as s on c.id_servicio = s.id
group by c.codigo_res
order by c.fecha_hora;
end $$
delimiter $$

delimiter $$
create procedure coConsumo (in cId_cons int)
begin
select id_cons,numero_hab,codigo_res,id_servicio,fecha_hora from consumo where id_cons = cId_cons;
end $$
delimiter $$

delimiter $$
create procedure totalConsumo (in cCodigo_res int)
begin
select s.nombre, sum(s.tarifa) , count(s.id)
from reserva as r 
inner join consumo as c  on r.codigo =c.codigo_res
inner join servicio as s on c.id_servicio = s.id
where r.codigo = cCodigo_res
group by s.nombre;
end $$
delimiter $$


-- procedimientos de Reserva_cierre

delimiter $$
create procedure inReserva_cierre (in rcCodigo char(4), in rcFecha_cierre DATE,in rcTipo_pago CHAR(3), in rcMonto_total float(10,2))
begin
insert into reserva_cierre(codigo,fecha_cierre,tipo_pago,monto_total)
values (rcCodigo,rcFecha_cierre,rcTipo_pago,rcMonto_total);
end $$
delimiter $$

delimiter $$
create procedure moReserva_cierre (in rcCodigo char(4), in rcFecha_cierre DATE,in rcTipo_pago CHAR(3), in rcMonto_total float(10,2))
begin
update reserva_cierre SET codigo = rcCodigo, fecha_cierre = rcFecha_cierre,tipo_pago=rcTipo_pago, monto_total=rcMonto_total where codigo = rcCodigo;
end $$
delimiter $$

delimiter $$
create procedure elReserva_cierre (in rcCodigo CHAR(4))
begin
delete from reserva_cierre where codigo = rcCodigo;
end $$
delimiter $$

delimiter $$
create procedure liReserva_cierre ()
begin
select codigo, fecha_cierre,tipo_pago, monto_total from reserva_cierre;
end $$
delimiter $$

delimiter $$
create procedure coReserva_cierre (in rcCodigo CHAR(4))
begin
select codigo, fecha_cierre,tipo_pago, monto_total from reserva_cierre where codigo = rcCodigo;
end $$
delimiter $$

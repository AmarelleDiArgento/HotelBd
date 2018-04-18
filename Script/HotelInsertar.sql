-- Inserciones de la tabla Rol

  call Hotel.inRol('Administrador');
  call Hotel.inRol('Recepcionista');
  call Hotel.inRol('Cliente');

-- Inserciones de la tabla usuario

  call Hotel.inUsuario('1070949', 'Thomas Moreno', '1234', 1);
  call Hotel.inUsuario('1013579', 'Diana Ardila', '1234', 2);
  call Hotel.inUsuario('1070383', 'Batman Antonio', '1234', 3);
  call Hotel.inUsuario('1070585', 'Maria Dolores', '1234', 3);

-- Inserciones de la tabla permiso

  call Hotel.inPermiso('Usuario','Usuario', 'usuario.jsp');
  call Hotel.inPermiso('Usuario','Mi Usuario', 'meUsuario.jsp');
  call Hotel.inPermiso('Usuario','Rol', 'rol.jsp');
  call Hotel.inPermiso('Usuario','Permiso', 'permiso.jsp');
  call Hotel.inPermiso('Reserva','Reserva', 'reserva.jsp');
  call Hotel.inPermiso('Reserva','Mi Reserva', 'meReserva.jsp');
  call Hotel.inPermiso('Reserva','Habita', 'habita.jsp');
  call Hotel.inPermiso('Reserva','Consumo', 'consumo.jsp');
  call Hotel.inPermiso('Reserva','Servicio', 'servicio.jsp');
  
-- Inserciones de la tabla asignaPer 

  call Hotel.inAsignaPer(1,1,1,1,1,1);
  call Hotel.inAsignaPer(1,2,1,1,1,1);
  call Hotel.inAsignaPer(1,3,1,1,1,1);
  call Hotel.inAsignaPer(1,4,1,1,1,1);
  call Hotel.inAsignaPer(1,5,1,1,1,1);
  call Hotel.inAsignaPer(1,6,1,1,1,1);
  call Hotel.inAsignaPer(1,7,1,1,1,1);
  call Hotel.inAsignaPer(1,8,1,1,1,1);
  call Hotel.inAsignaPer(1,9,1,1,1,1);
  
  call Hotel.inAsignaPer(2,1,1,1,1,0);
  call Hotel.inAsignaPer(2,2,1,1,1,0);  
  call Hotel.inAsignaPer(2,3,0,0,0,0);
  call Hotel.inAsignaPer(2,4,0,0,0,0);
  call Hotel.inAsignaPer(2,5,1,1,1,0);
  call Hotel.inAsignaPer(2,6,1,1,1,0);
  call Hotel.inAsignaPer(2,7,0,0,0,0);
  call Hotel.inAsignaPer(2,8,0,1,1,0);
  call Hotel.inAsignaPer(2,9,0,0,0,0);
  
  call Hotel.inAsignaPer(3,1,1,0,0,0);
  call Hotel.inAsignaPer(3,2,1,0,0,0);
  call Hotel.inAsignaPer(3,3,0,0,0,0);
  call Hotel.inAsignaPer(3,4,0,0,0,0);
  call Hotel.inAsignaPer(3,5,0,0,0,0);
  call Hotel.inAsignaPer(3,6,1,0,0,0);
  call Hotel.inAsignaPer(3,7,0,1,0,0);
  call Hotel.inAsignaPer(3,8,0,0,0,0);
  call Hotel.inAsignaPer(3,9,0,0,0,0);

-- Inserciones de la tabla Habitacion 

  call Hotel.inHabita('A001', 80000);
  call Hotel.inHabita('A002', 80000);
  call Hotel.inHabita('A003', 80000);
  call Hotel.inHabita('A004', 90000);
  call Hotel.inHabita('A005', 90000);
  call Hotel.inHabita('B001', 70000);
  call Hotel.inHabita('B002', 70000);
  call Hotel.inHabita('B003', 70000);
  call Hotel.inHabita('B004', 85000);
  call Hotel.inHabita('B005', 85000);
  call Hotel.inHabita('C001', 80000);
  call Hotel.inHabita('C002', 80000);
  call Hotel.inHabita('C003', 80000);
  call Hotel.inHabita('C004', 90000);
  call Hotel.inHabita('C005', 90000);
  call Hotel.inHabita('D001', 70000);
  call Hotel.inHabita('D002', 70000);
  call Hotel.inHabita('D003', 70000);
  call Hotel.inHabita('D004', 85000);
  call Hotel.inHabita('D005', 85000);

-- inserciones de Servicio

  call Hotel.inServicio('Desayuno',12000);
  call Hotel.inServicio('Almuerzo',15000);
  call Hotel.inServicio('Cena',10000);
  call Hotel.inServicio('Bar La Tasca',17000);
  call Hotel.inServicio('Gimnasio',8000);
  call Hotel.inServicio('Tenis',15000);

-- inserciones de reserva

  call Hotel.inReserva('2018-03-20', '2018-03-20', 1070949, 3);
  call Hotel.inReserva('2018-03-20', '2018-03-20', 1013579, 3);

-- inserciones de reserva

  call Hotel.inAsigna('A001', 1, 2, 1);
  call Hotel.inAsigna('A002', 2, 2, 1);

-- inserciones de consumo

  call Hotel.inConsumo('A001', 1, 1, '2018-03-21 17:00');

-- inserciones de cierre

  call Hotel.inReserva_cierre('1', '2018-03-21', 'Efe', 10000);


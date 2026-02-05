-- Inserción de Datos (INSERT)
BEGIN
    SP_CENTRO_OPERACION_CRUD(
        1,
        1,
        'Centro Operaciones Principal',
        'Bogotá',
        'Carlos Martínez',
        'ACTIVO',
        NULL
    );

    SP_CENTRO_OPERACION_CRUD(
        1,
        2,
        'Centro Operaciones Norte',
        'Medellín',
        'Laura Gómez',
        'ACTIVO',
        NULL
    );

    SP_CENTRO_OPERACION_CRUD(
        1,
        3,
        'Centro Operaciones Sur',
        'Cali',
        'Andrés Rodríguez',
        'INACTIVO',
        NULL
    );

    SP_CENTRO_OPERACION_CRUD(
        1,
        4,
        'Centro Operaciones Costa',
        'Barranquilla',
        'María Fernanda López',
        'ACTIVO',
        NULL
    );

    COMMIT;
END;
/

-- Actualización de Datos (UPDATE)
BEGIN
    SP_CENTRO_OPERACION_CRUD(
        2,
        3,
        'Centro Operaciones Sur',
        'Cali',
        'Andrés Rodríguez',
        'ACTIVO',
        NULL
    );

    COMMIT;
END;
/

-- Eliminación de Datos (DELETE)
BEGIN
    SP_CENTRO_OPERACION_CRUD(
        3,
        4,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL
    );

    COMMIT;
END;
/

-- Consulta de Datos (SELECT)
VAR rc REFCURSOR;

BEGIN
    SP_CENTRO_OPERACION_CRUD(
        4,
        NULL,
        NULL,
        NULL,
        NULL,
        NULL,
        :rc
    );
END;
/

PRINT rc;
-- Crear la tabla de compañías discográficas
CREATE TABLE companias (
  id_compania SERIAL PRIMARY KEY,
  nombre VARCHAR(50) NOT NULL,
  direccion VARCHAR(100) NOT NULL
);

-- Crear la tabla de grabaciones
CREATE TABLE grabaciones (
  id_grabacion SERIAL PRIMARY KEY,
  titulo VARCHAR(100) NOT NULL,
  categoria_musical VARCHAR(50) NOT NULL,
  num_temas INTEGER NOT NULL,
  descripcion TEXT,
  id_compania INTEGER REFERENCES companias(id_compania) ON DELETE CASCADE
);

-- Crear la tabla de formatos de grabación
CREATE TABLE formatos (
  id_formato SERIAL PRIMARY KEY,
  formato VARCHAR(20) NOT NULL
);

-- Crear la tabla de estado de conservación de las grabaciones
CREATE TABLE estados (
  id_estado SERIAL PRIMARY KEY,
  estado VARCHAR(10) NOT NULL
);

-- Crear la tabla de intérpretes
CREATE TABLE interpretes (
  id_interprete SERIAL PRIMARY KEY,
  nombre VARCHAR(100) NOT NULL,
  descripcion TEXT
);

-- Crear la tabla intermedia entre grabaciones e intérpretes (muchos a muchos)
CREATE TABLE grabaciones_interpretes (
  id_grabacion INTEGER REFERENCES grabaciones(id_grabacion) ON DELETE CASCADE,
  id_interprete INTEGER REFERENCES interpretes(id_interprete) ON DELETE CASCADE,
  fecha_participacion DATE NOT NULL,
  PRIMARY KEY (id_grabacion, id_interprete)
);

-- Crear la tabla intermedia entre grabaciones y formatos (muchos a muchos)
CREATE TABLE grabaciones_formatos (
  id_grabacion INTEGER REFERENCES grabaciones(id_grabacion) ON DELETE CASCADE,
  id_formato INTEGER REFERENCES formatos(id_formato) ON DELETE CASCADE,
  id_estado INTEGER REFERENCES estados(id_estado) ON DELETE CASCADE,
  PRIMARY KEY (id_grabacion, id_formato)
);


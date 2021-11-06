<?php

define('DB_HOST', 'localhost');
define('DB_USER', 'root');
define('DB_PASS', '');
define('DB_NAME', 'dbapptobesity');

$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);

if (mysqli_connect_errno()){
	echo "Fallo al conectar con MySQL: ".mysqli_connect_error();
	die();
}

$stmt = $conn->prepare("SELECT dni, nombres, apellidos, telefono, edad, talla, peso, imc, cita, tratamiento FROM pacientes;");
$stmt->execute();
$stmt->bind_result($dni, $nombres, $apellidos, $telefono, $edad, $talla, $peso, $imc, $cita, $tratamiento);
$pacients = array();

while ($stmt->fetch()){
	$temp = array();
	$temp['dni'] = $dni;
	$temp['nombres'] = $nombres;
	$temp['apellidos'] = $apellidos;
	$temp['telefono'] = $telefono;
	$temp['edad'] = $edad;
	$temp['talla'] = $talla;
	$temp['peso'] = $peso;
	$temp['imc'] = $imc;
	$temp['cita'] = $cita;
	$temp['tratamiento'] = $tratamiento;
	array_push($pacients, $temp);
}
echo json_encode($pacients);
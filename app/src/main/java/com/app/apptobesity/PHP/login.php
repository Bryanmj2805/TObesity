<?php 
$hostname = 'localhost';
$database = 'dbapptobesity';
$username = 'root';
$password = '';

$conexion = new mysqli($hostname, $username, $password, $database);
if ($conexion->connect_error){
	echo "El sitio tiene problemas de conexion";
}

$dni = $_POST['dni'];
$clave = $_POST['clave'];

$sentencia = $conexion->prepare("SELECT * FROM medicos WHERE dni=? AND clave=?");
$sentencia->bind_param('ss',$dni, $clave);
$sentencia->execute();

$resultado = $sentencia->get_result();
if ($fila = $resultado->fetch_assoc()) {
	echo json_encode($fila, JSON_UNESCAPED_UNICODE);
}
$sentencia->close();
$conexion->close();
?>
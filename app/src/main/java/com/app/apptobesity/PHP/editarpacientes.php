<?php
	if ($_SERVER['REQUEST_METHOD'] == 'POST') {

		require_once("connect.php");
	
		$dni = $_POST['dni'];
		$nombres = $_POST['nombres'];
		$apellidos = $_POST['apellidos'];
		$telefono = $_POST['telefono'];
		$edad = $_POST['edad'];
		$talla = $_POST['talla'];
		$peso = $_POST['peso'];
		$imc = $_POST['imc'];
		$cita = $_POST['cita'];
		$tratamiento = $_POST['tratamiento'];

		$query = "UPDATE pacientes SET nombres = '$nombres', apellidos = '$apellidos', telefono = '$telefono', edad = '$edad', talla = '$talla', peso = '$peso', imc = '$imc', cita = '$cita', tratamiento = '$tratamiento' WHERE dni = '$dni'";

		$result = $mysql->query($query);

		if($mysql->affected_rows > 0 ){
			if ($result === TRUE){
				echo "update successfully";
			} else {
				echo "error";
			}
		} else {
			echo "Not found any rows";
		}
		$mysql->close();
	}
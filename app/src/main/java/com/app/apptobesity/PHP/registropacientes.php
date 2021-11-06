<?php
	if ($_SERVER['REQUEST_METHOD'] == 'POST'){

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

		$query = "INSERT INTO pacientes (dni,nombres,apellidos,telefono,edad,talla,peso,imc,cita,tratamiento) VALUES ('$dni','$nombres','$apellidos','$telefono','$edad','$talla','$peso','$imc','$cita','$tratamiento')";
		$result = $mysql->query($query);

		if ($result === TRUE) {
			echo "Paciente Registrado con Exito";
		} else {
			echo "Proceso de registro errado";
		}

		$mysql->close();
	}

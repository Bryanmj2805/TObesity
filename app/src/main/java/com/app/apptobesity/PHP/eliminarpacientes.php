<?php
	if ($_SERVER['REQUEST_METHOD'] == 'POST') {

		require_once("connect.php");
		$dni = $_POST['dni'];

		$query = "DELETE FROM pacientes WHERE dni = '$dni'";
		$result = $mysql->query($query);

		if($mysql->affected_rows > 0 ){
			if ($result === TRUE) {
				echo "Registro Eliminado";
			}
		} else {
			echo "No se encontro DNI";
		}

		$mysql->close();
	}
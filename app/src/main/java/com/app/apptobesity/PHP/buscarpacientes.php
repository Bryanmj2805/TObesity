<?php

	if ($_SERVER['REQUEST_METHOD'] == 'GET'){

		require_once("connect.php");

		$dni = $_GET['dni'];


		$query = "SELECT * FROM pacientes WHERE dni = '$dni'";
		$result = $mysql->query($query);

		if ($mysql->affected_rows > 0 ){
			while ($row = $result->fetch_assoc()) {
				$array = $row;
			}
			
			echo json_encode($array);
		} else {
			echo "not found any rows";
		}
		$result->close();
		$mysql->close();
	}
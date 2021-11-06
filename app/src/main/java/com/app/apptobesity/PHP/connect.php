<?php

	$mysql = new mysqli(
		"localhost",
		"root",
		"",
		"dbAppTObesity"
	);

	if ($mysql->connect_error){
		die("Conexion Fallida" . $mysql->connect_error);
	} else {
		//echo "Conexion Exitosa";
	}
?>
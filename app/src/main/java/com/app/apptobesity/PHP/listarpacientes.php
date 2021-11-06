<?php 
	require_once("connect.php");

	$result = array();
	$result['pacientes'] = array();
	$query = "SELECT * FROM pacientes";
	$mostrar = $mysql->query($query);

	while ($row = mysqli_fetch_array($mostrar))
	{
		$index['dni'] = $row['0'];
		$index['nombres'] = $row['1'];
		$index['apellidos'] = $row['2'];
		$index['telefono'] = $row['3'];
		$index['edad'] = $row['4'];
		$index['talla'] = $row['5'];
		$index['peso'] = $row['6'];
		$index['imc'] = $row['7'];
		$index['cita'] = $row['8'];
		$index['tratamiento'] = $row['9'];

		array_push($result['pacientes'], $index);
	}
	$result["success"]="1";
	echo json_encode($result);
	$mysql->close();
?>
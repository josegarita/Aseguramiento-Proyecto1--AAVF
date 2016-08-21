<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="myapp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Angular</title>
<script type="text/javascript" src="js/angular.min.js"></script>
</head>
<body ng-controller="productController">

<table cellpadding="2" cellspacing="2" border="1">
	<tr>
		<th>Id</th>
		<th>Name</th>
		<th>Price</th>
		<th>Quantity</th>
	</tr>
	<tr ng-repeat="pr in listProduct">
		<td>{{pr.id}}</td>
		<td>{{pr.name}}</td>
	</tr>
</table>

<script type="text/javascript">
	var myapp = angular.module('myapp', []);
	myapp.controller('productController', function($scope, $http){
		$http.get('http://localhost:8080/Restful/rest/producto/enlistar').sucess(function(response){
			$scope.listProduct = response.producto;
		});
	});
</script>
</body>
</html>
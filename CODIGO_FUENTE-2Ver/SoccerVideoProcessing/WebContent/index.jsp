<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
   <head>
      <script src = "https://ajax.googleapis.com/ajax/libs/angularjs/1.5.6/angular.min.js"></script>
   	  <link href = "main.css" rel = "stylesheet" type = "text/css"/>
   </head>
   
   <body ng-app = "myApp">

      <div ng-controller = "myCtrl">
         <h1>Procesador de Video Boca</h1>
         <img src="http://www.banderas.com.ar/banderas-de-boca-juniors-futbol.jpg" alt="Bandera Boca">
         <p>
         {{Validacion}}
         <p>
         <input class="file" type = "file" file-model = "myFile"/>
         <button class="ng-btn" ng-click = "uploadFile()">upload me</button>
         <form  action="http://localhost:8080/SoccerVideoProcessing/rest/producto/download" method="get">
		      <button class="ng-btn" type= "submit">Descargar</button>
		 </form>
      </div>
      
      
      
      <script>
         var myApp = angular.module('myApp', []);
         
         myApp.directive('fileModel', ['$parse', function ($parse) {
            return {
               restrict: 'A',
               link: function(scope, element, attrs) {
                  var model = $parse(attrs.fileModel);
                  var modelSetter = model.assign;
                  
                  element.bind('change', function(){
                     scope.$apply(function(){
                        modelSetter(scope, element[0].files[0]);
                     });
                  });
               }
            };
         }]);
      
         myApp.service('fileUpload', ['$http', function ($http) {
            this.uploadFileToUrl = function(file, uploadUrl){
               var fd = new FormData();
               fd.append('file', file);
               $http.post(uploadUrl, fd, {
                  transformRequest: angular.identity,
                  headers: {'Content-Type': undefined}
               })
            
               .then(function(response){
                  console.log(response.data);
               });
               
            }
         }]);
      
         myApp.controller('myCtrl', ['$scope', 'fileUpload', function($scope, fileUpload){
        	$scope.Validacion = "Inserte Video";
        	
            $scope.uploadFile = function(){
               var file = $scope.myFile;
               console.log('file is ' );
               console.dir(file);
               
               if ($scope.myFile ==  undefined) {
                   $scope.Validacion = "No hay video a procesar";
               } else {
            	   $scope.Validacion = "Video procesado";
            	   var uploadUrl = "http://localhost:8080/SoccerVideoProcessing/rest/producto/upload";
            	   fileUpload.uploadFileToUrl(file, uploadUrl);
               }
            };
         }]);
			
      </script>
      
   </body>
</html>
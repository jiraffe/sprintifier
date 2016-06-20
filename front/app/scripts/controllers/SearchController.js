"use strict";

angular.module('sprintifier')
	.controller('SearchController', [
	
		'$scope',
		'$state',
		
		function($scope, $state) {
		
			$scope.params = angular.copy($state.params)
			console.log($scope.params);
		}
	
	]);
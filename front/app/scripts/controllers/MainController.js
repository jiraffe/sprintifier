"use strict";

angular.module('sprintifier')
	.controller('MainController', [
	
		'$scope',
		'$state',
		'ProjectFactory',
	
		function($scope, $state) {
		console.log('test Main');
		}
	
	]);
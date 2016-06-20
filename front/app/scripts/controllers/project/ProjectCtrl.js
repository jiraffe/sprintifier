"use strict";

angular.module('sprintifier')
	.controller('ProjectCtrl', [
	
		'$scope',
		'$state',
		'ProjectFactory',
	
		function($scope, $state, ProjectFactory) {
			
			$scope.projects = [];

			ProjectFactory.getAll(function(responce) {
				$scope.projects = responce.data;
			});

			$scope.add = function(project) {
				ProjectFactory.save(project, function() {
					console.log("save success");
				});
			};
		}
	
	]);
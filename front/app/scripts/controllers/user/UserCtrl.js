"use strict";

angular.module('sprintifier')
	.controller('UserCtrl', [
	
		'$scope',
		'$state',
		'UserFactory',
	
		function($scope, $state, UserFactory) {
			
			$scope.users = [];

			$scope.loadUsers = function() {
				UserFactory.getAll(function(responce) {
					$scope.users = responce.data;
				});
			};

			$scope.add = function(obj) {
				UserFactory.save(obj, function() {
					console.log("save success");
				})
				.then($scope.loadUsers);
			};

			$scope.update = function(obj) {
				UserFactory.update(obj, function() {
					console.log("user update success");
				})
				.then($scope.loadUsers);
			};

			$scope.remove = function(id) {
				UserFactory.remove(id)
					.then($scope.loadUsers);
			};

			(function init() {
				$scope.loadUsers();	
			})();
		}
	
	]);
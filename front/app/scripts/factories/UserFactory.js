"use strict";

angular.module('sprintifier')
	.factory('UserFactory', [
		
		'$http',

		function ($http) {

			var api = {};

			var url = '/users/';

			api.getAll = function(callback) {
				return $http({
					url: url,
					method: 'GET'
				}).success(callback)
				.error(callback);
			};

			api.save = function(user, callback) {
				return $http({
					url: url,
					method: 'PUT',
					data: user
				}).success(callback)
				.error(callback);
			};

			api.remove = function(id) {
				return $http({
					url: url + id,
					method: 'DELETE'
				});
			};

			api.update = function(user) {
				return $http({
					url: url,
					method: 'POST',
					data: user
				});
			};

			api.getUsersByPosition = function(id) {
				return $http({
					url: url + 'position/' + id,
					method: 'GET'
				});
			};

			return api;		
		}
		
	]);
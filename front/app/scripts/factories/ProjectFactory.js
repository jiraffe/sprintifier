"use strict";

angular.module('sprintifier')
	.factory('ProjectFactory', [
		
		'$http',

		function ($http) {

			var api = {};

			var url = '/projects/';

			api.getAll = function(callback) {
				return $http({
					url: url,
					method: 'GET'
				}).success(callback)
				.error(callback);
			};

			api.save = function(project, callback) {
				return $http({
					url: url,
					method: 'PUT',
					data: project
				}).success(callback)
				.error(callback);
			};

			api.getWithMembers = function(id) {
				return $http({
					url: url + id + '/members',
					method: 'GET'
				});
			}

			return api;		
		}
		
	]);
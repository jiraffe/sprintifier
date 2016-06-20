"use strict";

angular.module('sprintifier')
	.factory('PositionFactory', [
		
		'$http',

		function ($http) {

			var api = {};

			var url = '/positions/';

			api.getAll = function(id) {
				return $http({
					url: url,
					method: 'GET'
				});
			};

			return api;		
		}
		
	]);
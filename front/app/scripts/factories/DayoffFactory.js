"use strict";

angular.module('sprintifier')
	.factory('DayoffFactory', [
		
		'$http',

		function ($http) {

			var api = {};

			var url = '/dayoffs/';

			api.getAll = function() {
				return $http({
					url: url,
					method: 'GET'
				});
			};

			return api;		
		}
		
	]);
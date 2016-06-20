"use strict";

angular.module('sprintifier')
	.factory('TeamMemberFactory', [
		
		'$http',

		function ($http) {

			var api = {};

			var url = '/teammember/';

			api.del = function(id) {
				$http({
					method: "DELETE",
					url: url + id
				});
			};

			return api;		
		}
		
	]);
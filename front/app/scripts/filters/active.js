'use strict';

angular.module('sprintifier')
	.filter('active', function() {
		return function(arr) {
			if (!arr) { return []; }
			return arr.filter(function(item) { return item.active === true; });
		};
	});

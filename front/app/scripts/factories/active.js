angular.module('sprintifier')
	.filter('active', function() {
		'use strict';
		return function(arr) {
			if (!arr) { return []; }
			return arr.filter(function(item) { return item.active === true; });
		};
	});

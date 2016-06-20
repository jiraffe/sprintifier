"use strict";

angular.module('sprintifier')
	.directive("limitTo", [ 
	
		function() {
		
				return {
				require: 'ngModel',
				priority: 2100,
				restrict: "A",
				link: function (scope, element, attrs, modelCtrl) {
					if (!modelCtrl) {
						return;
					}
					var limit = parseInt(attrs.limitTo);

					modelCtrl.$parsers.push(function (inputValue) {
						if (inputValue && inputValue.length > limit) {
							var transformedInput = inputValue.substring(0, limit);
							modelCtrl.$setViewValue(transformedInput);
							modelCtrl.$render();
							if (attrs.limitShowError !== undefined) {
								console.log('Limit to: ' + limit);
							}
							return transformedInput;
						}
						return inputValue;
					});
				}
			};
		}
		
	]);
'use strict';

angular.module('sprintifier')
	.directive('createProjectPopup', function($uibModal) {
		return {
			restrict: 'A',
			link: function($scope, $elem, $attrs) {
				$elem.on('click', function () {

					$uibModal.open({
						templateUrl: 'views/popups/projectCreatePopup.html',
						controller: 'CreateProjectPopupCtrl',
						backdrop: 'static',
						resolve: {
							params: function () {
								return {
									saveMethod : $scope.saveMethod
								};
							}
						}
					})
				});
			},
			scope: {
				saveMethod: '&'
			}
		};
	})
	.controller('CreateProjectPopupCtrl', function ($scope, params) {

		$scope.proj = {};

		$scope.save = function () {
			params.saveMethod()($scope.proj);
			$scope.$close();
		};
	
	});
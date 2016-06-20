'use strict';

angular.module('sprintifier')
	.directive('createUserPopup', function($uibModal) {
		return {
			restrict: 'A',
			link: function($scope, $elem, $attrs) {
				$elem.on('click', function () {

					$uibModal.open({
						templateUrl: 'views/popups/userCreatePopup.html',
						controller: 'CreateUserPopupCtrl',
						backdrop: 'static',
						resolve: {
							params: function () {
								return {
									saveMethod : $scope.saveMethod,
									record: $scope.record
								};
							}
						}
					})
				});
			},
			scope: {
				saveMethod: '&',
				record: '='
			}
		};
	})
	.controller('CreateUserPopupCtrl', function ($scope, params, PositionFactory) {

		console.log(params);

		var user = params.record || {};
		$scope.user = angular.copy(user);
		$scope.positions = [];

		$scope.save = function () {
			params.saveMethod()($scope.user);
			$scope.$close();
		};

		(function(){
			PositionFactory.getAll()
				.then(function(response) {
					$scope.positions = response.data.data;
				});
		})();
				
	});
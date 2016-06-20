'use strict';

angular.module('sprintifier')
	.directive('addTeamMemberPopup', function($uibModal) {
		return {
			restrict: 'A',
			link: function($scope, $elem, $attrs) {
				$elem.on('click', function () {

					$uibModal.open({
						templateUrl: 'views/popups/projectTeamMemberPopup.html',
						controller: 'AddTeamMemberPopupCtrl',
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
	.controller('AddTeamMemberPopupCtrl', function ($scope, params, PositionFactory, UserFactory) {

		$scope.users = [];
		$scope.roles = [];
		$scope.selectedRole = {};

		$scope.save = function () {
			params.saveMethod()($scope.proj);
			$scope.$close();
		};

		var loadRoles = function() {
			PositionFactory.getAll()
				.then(function(responce) {
					$scope.roles = responce.data.data;
				});
		};

		var loadUsersByPosition = function() {
			console.log('loading...');
			UserFactory.getUsersByPosition($scope.selectedRole.id)
				.then(function(responce){
					$scope.users = responce.data.data;
				});
		};

		function init() {
			loadRoles();
		};
		
		init();
	});